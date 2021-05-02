package ch3.sub6.methodreference;

import ch3.sub7.Color;

public class Apple {
    private Color color;
    private Integer weight;

    public Apple(Integer weight) {
        this.color = Color.RED;
        this.weight = weight;
    }

    public Apple(Color color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
