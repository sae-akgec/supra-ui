package in.saeakgec.supra.model;

public class IndividualFlags {

    private Boolean blackFlag = false;

    private Boolean blackOrangeFlag = false;

    private Boolean blueFlag = false;

    private Boolean checkerFlag = false;

    private Boolean whiteFlag = false;

    public IndividualFlags() {
    }

    public IndividualFlags(Boolean blackFlag, Boolean blackOrangeFlag, Boolean blueFlag, Boolean checkerFlag, Boolean whiteFlag) {
        this.blackFlag = blackFlag;
        this.blackOrangeFlag = blackOrangeFlag;
        this.blueFlag = blueFlag;
        this.checkerFlag = checkerFlag;
        this.whiteFlag = whiteFlag;
    }

    public Boolean getBlackFlag() {
        return blackFlag;
    }

    public void setBlackFlag(Boolean blackFlag) {
        this.blackFlag = blackFlag;
    }

    public Boolean getBlackOrangeFlag() {
        return blackOrangeFlag;
    }

    public void setBlackOrangeFlag(Boolean blackOrangeFlag) {
        this.blackOrangeFlag = blackOrangeFlag;
    }

    public Boolean getBlueFlag() {
        return blueFlag;
    }

    public void setBlueFlag(Boolean blueFlag) {
        this.blueFlag = blueFlag;
    }

    public Boolean getCheckerFlag() {
        return checkerFlag;
    }

    public void setCheckerFlag(Boolean checkerFlag) {
        this.checkerFlag = checkerFlag;
    }

    public Boolean getWhiteFlag() {
        return whiteFlag;
    }

    public void setWhiteFlag(Boolean whiteFlag) {
        this.whiteFlag = whiteFlag;
    }
}
