package com.neno.model;

/**
 * @Author: root
 * @Date: 2019/1/22 10:52
 */
public class MyPoint {
    /**
     * 该坐标是相对于电脑的
     */
    private double x;

    private double y;

    public MyPoint() {

    }

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "MyPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
