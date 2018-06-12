package in.saeakgec.supra.model;

import java.util.List;

public class GeneralFlags {

    private List<GreenFlag> greenFlags;

    private List<RedFlag> redFlags;

    private List<YellowFlag> yellowFlags;

    private List<RedYellowFlag> redYellowFlags;

    private List<BlueFlag> blueFlags;

    public GeneralFlags() {
    }

    public GeneralFlags(List<GreenFlag> greenFlags, List<RedFlag> redFlags, List<YellowFlag> yellowFlags, List<RedYellowFlag> redYellowFlags, List<BlueFlag> blueFlags) {
        this.greenFlags = greenFlags;
        this.redFlags = redFlags;
        this.yellowFlags = yellowFlags;
        this.redYellowFlags = redYellowFlags;
        this.blueFlags = blueFlags;
    }

    public List<GreenFlag> getGreenFlags() {
        return greenFlags;
    }

    public void setGreenFlags(List<GreenFlag> greenFlags) {
        this.greenFlags = greenFlags;
    }

    public List<RedFlag> getRedFlags() {
        return redFlags;
    }

    public void setRedFlags(List<RedFlag> redFlags) {
        this.redFlags = redFlags;
    }

    public List<YellowFlag> getYellowFlags() {
        return yellowFlags;
    }

    public void setYellowFlags(List<YellowFlag> yellowFlags) {
        this.yellowFlags = yellowFlags;
    }

    public List<RedYellowFlag> getRedYellowFlags() {
        return redYellowFlags;
    }

    public void setRedYellowFlags(List<RedYellowFlag> redYellowFlags) {
        this.redYellowFlags = redYellowFlags;
    }

    public List<BlueFlag> getBlueFlags() {
        return blueFlags;
    }

    public void setBlueFlags(List<BlueFlag> blueFlags) {
        this.blueFlags = blueFlags;
    }
}
