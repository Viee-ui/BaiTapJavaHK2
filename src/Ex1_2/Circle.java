package Ex1_2;

public class Circle {
    private double radius;

    public Circle(){
        radius = 1.0;
    }
    public Circle(double r){
        radius = r;
    }

    public double getRadius(){
        return radius;
    }
    public void setRadius(double r){
        radius = r;
    }
    public double getArea(){
        return Math.PI * radius * radius;
    }
    public double getCircumference(){
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
