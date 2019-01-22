package com.neno.model;

/**
 * @Author: root
 * @Date: 2019/1/22 11:11
 */
public class MyRect {
    /**
     * 左上
     * 该坐标是相对于电脑的
     */
    private int x;
    private int y;
    /**
     * 矩形的宽度
     */
    private int width;
    /**
     * 矩形的高度
     */
    private int height;

    public MyRect() {

    }

    public MyRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "MyRect{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
