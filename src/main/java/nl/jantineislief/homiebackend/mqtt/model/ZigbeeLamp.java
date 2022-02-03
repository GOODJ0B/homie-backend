package nl.jantineislief.homiebackend.mqtt.model;

import nl.jantineislief.homiebackend.mqtt.MqttHandler;

public class ZigbeeLamp implements Lamp {

    String topic;

    public ZigbeeLamp(String topic) {
        this.topic = topic;
    }

    @Override
    public void aan() {
        MqttHandler.getHandler().send("z2m/" + topic + "/set/state", "ON");
    }

    @Override
    public void uit() {
        MqttHandler.getHandler().send("z2m/" + topic + "/set/state", "OFF");
    }

    @Override
    public void setBrightness(int brightness) {
        MqttHandler.getHandler().send("z2m/" + topic + "/set/brightness", brightness);
    }
}
