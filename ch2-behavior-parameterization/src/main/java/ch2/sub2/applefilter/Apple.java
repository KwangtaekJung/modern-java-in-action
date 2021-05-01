package ch2.sub2.applefilter;

public class Apple {

    private Color color;

    private int wight;

    public Apple(Color color, int wight) {
        this.color = color;
        this.wight = wight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color=" + color +
                ", wight=" + wight +
                '}';
    }
}
