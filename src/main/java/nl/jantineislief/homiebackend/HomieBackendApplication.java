package nl.jantineislief.homiebackend;

import nl.jantineislief.homiebackend.mqtt.MqttOrchestrator;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomieBackendApplication {

	public static void main(String[] args) throws MqttException, InterruptedException {
		SpringApplication.run(HomieBackendApplication.class, args);
		new MqttOrchestrator();
	}

}
