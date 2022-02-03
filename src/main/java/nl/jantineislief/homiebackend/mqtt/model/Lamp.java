package nl.jantineislief.homiebackend.mqtt.model;

public interface Lamp {
    void aan();
    void uit();

    void setBrightness(int brightness);
}
