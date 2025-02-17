package Ex6_1;

public class Ractangle extends Shape {

    @Override
    public double getArea() {
        return 0;

    }
    @Override
    public double getPerimeter() {
        return 0;
    }

    private double width;
    private double length;

    public Ractangle() {

    }
    public Ractangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public Ractangle(double width, double length, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }

    public String toString() {
        return "Rectangle[Shape[color=" + getColor() + ", filled=" + isFilled() + ", width=" + width + ", length=" + length + "]";

    }
}
