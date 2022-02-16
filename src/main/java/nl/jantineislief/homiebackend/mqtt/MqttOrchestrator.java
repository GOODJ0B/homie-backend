package nl.jantineislief.homiebackend.mqtt;

import nl.jantineislief.homiebackend.mqtt.model.Lampen;
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

            if (tvkastOn) {
                Lampen.vensterbankLinks.uit();
                Lampen.vensterbankRechts.uit();
                Lampen.tvKast.uit();
                Lampen.buiten.uit();
            } else {
                Lampen.vensterbankLinks.aan();
                Lampen.vensterbankRechts.aan();
                Lampen.tvKast.aan();
                Lampen.buiten.aan();
            }
        });
    }


}
