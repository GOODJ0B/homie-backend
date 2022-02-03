package nl.jantineislief.homiebackend.mqtt.model;

import nl.jantineislief.homiebackend.mqtt.MqttHandler;

public class TasmotaLamp implements Lamp {

    String topic;

    public TasmotaLamp(String topic) {
        this.topic = topic;
    }

    @Override
    public void aan() {
        MqttHandler.getHandler().send("cmnd/" + topic + "/set/POWER", "ON");
    }

    @Override
    public void uit() {
        MqttHandler.getHandler().send("cmnd/" + topic + "/set/POWER", "OFF");
    }

    @Override
    public void setBrightness(int brightness) {
    }
}
