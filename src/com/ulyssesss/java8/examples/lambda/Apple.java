package com.ulyssesss.java8.examples.lambda;

/**
 * Created by jiangyue on 2017/7/26.
 */
public class Apple {

    private int weight;
    private String color;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public boolean isGreen() {
        return "green".equals(color);
    }

    public boolean isHeavy() {
        return weight > 80;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
