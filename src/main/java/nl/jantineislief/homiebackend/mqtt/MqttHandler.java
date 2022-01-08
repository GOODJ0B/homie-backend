package nl.jantineislief.homiebackend.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MqttHandler {

    private static MqttHandler handler;
    public static MqttHandler getHandler() throws MqttException {
        if (handler != null && handler.mqttClient.isConnected()) {
            return handler;
        }
        return new MqttHandler();
    }

    private final IMqttClient mqttClient;

    private MqttHandler() throws MqttException {
        mqttClient = new MqttClient("tcp://192.168.0.31:1883", "homie-backend");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        mqttClient.connect(options);

        send("homie-backend", "ONLINE");
    }

    public Void send(String topic, String payload) throws MqttException {
        if ( !mqttClient.isConnected()) {
            System.out.println("Niet verbonden voor topic " + topic);
            return null;
        }
        MqttMessage message = new MqttMessage(payload.getBytes(StandardCharsets.UTF_8));
        message.setQos(1);
        message.setRetained(false);
        mqttClient.publish(topic, message);
        return null;
    }

    public void listen(String topic, IMqttMessageListener listener) throws MqttException {
        mqttClient.subscribe(topic, listener);
    }
}