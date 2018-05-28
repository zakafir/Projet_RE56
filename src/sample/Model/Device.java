package sample.Model;

public class Device {


    private String name;
    private int positionX;
    private int positionY;
    private int internTmp;
    private double gainReceiving;

    public Device() {

    }

    public Device(String name, int positionX, int positionY, int internTmp, double gainReceiving) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.internTmp = internTmp;
        this.gainReceiving = gainReceiving;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getInternTmp() {
        return internTmp;
    }

    public void setInternTmp(int internTmp) {
        this.internTmp = internTmp;
    }

    public double getGainReceiving() {
        return gainReceiving;
    }

    public void setGainReceiving(double gainReceiving) {
        this.gainReceiving = gainReceiving;
    }


}

