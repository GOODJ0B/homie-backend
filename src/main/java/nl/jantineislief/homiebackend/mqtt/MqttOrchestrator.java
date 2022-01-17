package nl.jantineislief.homiebackend.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jantineislief.homiebackend.mqtt.model.TradfiPayload;
import org.apache.tomcat.util.json.JSONParser;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.nio.charset.StandardCharsets;

public class MqttOrchestrator {

    boolean vensterbankLinksOn;

    public MqttOrchestrator() throws MqttException {
        MqttHandler mqttHandler = MqttHandler.getHandler();

        mqttHandler.listen("z2m/vensterbank-links", (topic, msg) -> {
            byte[] payload = msg.getPayload();
            String payloadString = new String(payload, StandardCharsets.UTF_8);

            System.out.println(topic + ": " + new String(payload, StandardCharsets.UTF_8));

            TradfiPayload message = new ObjectMapper().readValue(payloadString, TradfiPayload.class);

            vensterbankLinksOn = message.state.equalsIgnoreCase("ON");
            System.out.println("Vensterbank links state: " + message.state);
        });

        mqttHandler.listen("z2m/extra", (topic, msg) -> {
            byte[] payload = msg.getPayload();
            String payloadString = new String(payload, StandardCharsets.UTF_8);

            System.out.println("Ontvangen: " + topic + " " + payloadString);

            mqttHandler.send("z2m/vensterbank-links/set/state", vensterbankLinksOn ? "OFF" : "ON");
            mqttHandler.send("z2m/vensterbank-rechts/set/state", vensterbankLinksOn ? "OFF" : "ON");
        });
    }


}
