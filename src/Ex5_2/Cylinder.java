package Ex5_2;

public class Cylinder {
    private Circle base; // Base circle
    private double height;

    public Cylinder() {
        this.base = new Circle(); // Default Circle
        this.height = 1.0; // Default height
    }

    public Cylinder(double radius, String color, double height) {
        this.base = new Circle(radius, color);
        this.height = height;
    }

    public Cylinder(Circle base, double height) {
        this.base = base;
        this.height = height;
    }

    public Circle getBase() {
        return base;
    }

    public void setBase(Circle base) {
        this.base = base;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVolume() {
        return base.getArea() * height;
    }

    @Override
    public String toString() {
        return "Cylinder[base=" + base + ", height=" + height + "]";
    }
}

