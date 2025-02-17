package Ex6_1;

public class Circle extends Shape {

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }

    private double radius;

    public Circle() {

    }
    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String toString() {
        return "Circle[Shape[color=" +getColor() + ", filled=" + isFilled() + ", radius=" + radius + "]]";

    }
}
