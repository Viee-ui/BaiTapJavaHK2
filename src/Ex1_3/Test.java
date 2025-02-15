package Ex1_3;

public class Test {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(1.2f, 3.4f);
        System.out.println(rectangle);

        rectangle.setLength(5.6f);
        rectangle.setWidth(7.8f);
        System.out.println("length: " + rectangle.getLength());
        System.out.println("width: " + rectangle.getWidth());

        System.out.println("area: " + rectangle.getArea());
        System.out.println("perimeter: " + rectangle.getPerimeter());
    }
}
