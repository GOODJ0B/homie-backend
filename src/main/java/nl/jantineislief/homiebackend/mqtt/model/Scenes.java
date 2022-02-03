package nl.jantineislief.homiebackend.mqtt.model;

public class Scenes {

    public static void tvKijken() {
        Lampen.tvKast.aan();
        Lampen.vensterbankLinks.aan();
        Lampen.vensterbankRechts.aan();
        Lampen.spotjes.aan();
        Lampen.eetTafel.aan();
        Lampen.keuken.uit();

        Lampen.spotjes.setBrightness(3);
        Lampen.keuken.setBrightness(30);
    }

}
