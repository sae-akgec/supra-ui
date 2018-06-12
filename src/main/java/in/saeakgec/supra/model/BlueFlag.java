package in.saeakgec.supra.model;

public class BlueFlag {

    public BlueFlag(String src, float lat, float lon) {
        this.src = src;
        this.lat = lat;
        this.lon = lon;
    }

    public BlueFlag() {
    }

    private String src;

    private float lat;

    private float lon;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
