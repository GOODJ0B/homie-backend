package nl.jantineislief.homiebackend;

import nl.jantineislief.homiebackend.mqtt.MqttOrchestrator;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

@SpringBootApplication
public class HomieBackendApplication {

	public static void main(String[] args) throws MqttException, IOException {
		SpringApplication.run(HomieBackendApplication.class, args);

		new MqttOrchestrator();
	}

}
