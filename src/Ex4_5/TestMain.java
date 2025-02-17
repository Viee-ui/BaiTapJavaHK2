package Ex4_5;

public class TestMain {
    public static void main(String[] args) {
        Shape shape = new Shape("red", false);
        System.out.println(shape);

        Circle circle = new Circle(5.5, "red", true);
        System.out.println(circle);
        System.out.println("Area: " + circle.getArea());

        Rectangle rectangle = new Rectangle(2.0, 4.0, "yellow", true);
        System.out.println(rectangle);
        System.out.println("Area: " + rectangle.getArea());
        System.out.println("Perimeter: " + rectangle.getPerimeter());

        Square square = new Square(3.0, "purple", false);
        System.out.println(square);
        System.out.println("Area: " + square.getArea());
        System.out.println("Perimeter: " + square.getPerimeter());
    }
}
