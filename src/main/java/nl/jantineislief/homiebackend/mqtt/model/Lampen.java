package nl.jantineislief.homiebackend.mqtt.model;

public class Lampen {

    public static Lamp vensterbankLinks = new ZigbeeLamp("vensterbank-links");
    public static Lamp vensterbankRechts = new ZigbeeLamp("vensterbank-rechts");
    public static Lamp tvKast = new TasmotaLamp("tvkast");
    public static Lamp spotjes = new ZigbeeLamp("spotjes");
    public static Lamp eetTafel = new ZigbeeLamp("eettafel");
    public static Lamp keuken = new ZigbeeLamp("keuken");
    public static Lamp buiten = new RFLamp(0);
    public static Lamp keukenLedStrip = new RFLamp(2);
    public static Lamp ster = new RFLamp(3);

}
