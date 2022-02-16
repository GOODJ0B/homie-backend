package nl.jantineislief.homiebackend.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.StandardCharsets;

public class MqttHandler {

    private static MqttHandler handler;
    public static MqttHandler getHandler() {
        if (handler != null && handler.mqttClient.isConnected()) {
            return handler;
        }
        try {
            handler = new MqttHandler();
            return handler;
        } catch (MqttException e) {
            return null;
        }
    }

    private final IMqttClient mqttClient;

    private MqttHandler() throws MqttException {
        String osType = System.getProperty("os.name").split(" ")[0].toLowerCase();
        String serverIp = osType.contains("linux")
                ? "pi"
                : "pi4.jj";

        System.out.print("OS Type: ");
        System.out.println(osType);
        System.out.print("MQTT Server IP: ");
        System.out.println(serverIp);

        mqttClient = new MqttClient(
                "tcp://" + serverIp + ":1883",
                "homie-backend-" + osType);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        mqttClient.connect(options);

        send("homie-backend-" + osType, "ONLINE");
    }

    public Void send(String topic, int payload) {
        return this.send(topic, payload + "");
    }

    public Void send(String topic, String payload) {
        if ( !mqttClient.isConnected()) {
            System.out.println("Kan niet verzenden. Niet verbonden voor topic " + topic);
            return null;
        }
        System.out.println("Verzenden: " + topic + " " + payload);
        MqttMessage message = new MqttMessage(payload.getBytes(StandardCharsets.UTF_8));
        message.setQos(1);
        message.setRetained(false);
        try {
            mqttClient.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void listen(String topic, IMqttMessageListener listener) throws MqttException {
        mqttClient.subscribe(topic, listener);
    }
}
