package nl.jantineislief.homiebackend.mqtt.model;

public class TradfiPayload {

    public int brightness;
    public int linkquality;
    public String state;
    public TradfiUpdate update;
    public boolean update_available;

}

class TradfiUpdate {
    public String state;
}