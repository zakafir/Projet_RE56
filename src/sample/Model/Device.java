package sample.Model;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sample.Controller.Calcul;

public class Device {


    private String name;
    private int positionX;
    private int positionY;
    private int internTmp;
    private double gainReceiving;
    private double calculPowerReceiving;
    private Circle shape;

    public Device() {

    }

    public Device(String name, int positionX, int positionY, int internTmp, double gainReceiving) {
        super();
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.internTmp = internTmp;
        this.gainReceiving = gainReceiving;
    }

    public Device(String name, int positionX, int positionY, int internTmp, double gainReceiving, Circle shape) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.internTmp = internTmp;
        this.gainReceiving = gainReceiving;
        this.shape = shape;
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

    public double getCalculPowerReceiving() {
        return calculPowerReceiving;
    }

    public void setCalculPowerReceiving(double calculPowerReceiving) {
        this.calculPowerReceiving = calculPowerReceiving;
    }

    public Circle getShape() {
        return shape;
    }

    public void setShape(Circle shape) {
        this.shape = shape;
    }

}

