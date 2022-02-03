package nl.jantineislief.homiebackend.mqtt.model;

import java.net.HttpURLConnection;
import java.net.URL;

public class RFLamp implements Lamp {
    public static String baseUrl = "http://192.168.0.40:5000/send-rf-";
    public int index;

    public RFLamp(int index) {
        this.index = index;
    }

    @Override
    public void aan() {
        sendHttpRequest("on");
    }

    @Override
    public void uit() {
        sendHttpRequest("off");
    }

    private void sendHttpRequest(String onOff) {
        try {
            URL url = new URL(baseUrl + onOff + "/" + this.index);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setBrightness(int brightness) {
    }
}
