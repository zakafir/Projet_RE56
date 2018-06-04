package sample.Model;

import java.awt.*;

public class BTS {

    private String name;
    private int positionX;
    private int positionY;
    private double gainTransmitting;
    private double powerTransmitting;
    private Long frequency;
    private int height;
    private int capacity;
    private Rectangle shape;

    public BTS() {

    }

    public BTS(String name, int positionX, int positionY, double gainTransmitting, double powerTransmitting, Long frequency, int height, int capacity) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.gainTransmitting = gainTransmitting;
        this.powerTransmitting = powerTransmitting;
        this.frequency = frequency;
        this.height = height;
        this.capacity = capacity;
    }

    public BTS(String name, int positionX, int positionY, double gainTransmitting, double powerTransmitting, Long frequency, int height, int capacity, Rectangle shape) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.gainTransmitting = gainTransmitting;
        this.powerTransmitting = powerTransmitting;
        this.frequency = frequency;
        this.height = height;
        this.capacity = capacity;
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

    public double getGainTransmitting() {
        return gainTransmitting;
    }

    public void setGainTransmitting(double gainTransmitting) {
        this.gainTransmitting = gainTransmitting;
    }

    public double getPowerTransmitting() {
        return powerTransmitting;
    }

    public void setPowerTransmitting(double powerTransmitting) {
        this.powerTransmitting = powerTransmitting;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Rectangle getShape() {
        return shape;
    }

    public void setShape(Rectangle shape) {
        this.shape = shape;
    }
}
