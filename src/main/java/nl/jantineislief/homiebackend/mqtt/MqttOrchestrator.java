package nl.jantineislief.homiebackend.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jantineislief.homiebackend.mqtt.model.TradfiPayload;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.nio.charset.StandardCharsets;

public class MqttOrchestrator {

    boolean tvkastOn;

    public MqttOrchestrator() throws MqttException {
        MqttHandler mqttHandler = MqttHandler.getHandler();

        mqttHandler.listen("stat/tvkast/POWER", (topic, msg) -> {
            byte[] payload = msg.getPayload();
            String payloadString = new String(payload, StandardCharsets.UTF_8);

            System.out.println(topic + ": " + new String(payload, StandardCharsets.UTF_8));

            tvkastOn = payloadString.equalsIgnoreCase("ON");
            System.out.println("TV kast state: " + payloadString);
        });

        mqttHandler.listen("z2m/extra", (topic, msg) -> {
            byte[] payload = msg.getPayload();
            String payloadString = new String(payload, StandardCharsets.UTF_8);

            System.out.println("Ontvangen: " + topic + " " + payloadString);

            mqttHandler.send("cmnd/tvkast/POWER", tvkastOn ? "OFF" : "ON");
            mqttHandler.send("z2m/vensterbank-links/set/state", tvkastOn ? "OFF" : "ON");
            mqttHandler.send("z2m/vensterbank-rechts/set/state", tvkastOn ? "OFF" : "ON");
        });
    }


}
