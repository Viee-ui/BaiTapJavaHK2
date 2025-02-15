package Ex1_2;

public class Test {
    public static void main(String[] args) {
        Circle c = new Circle();
        System.out.println(c);

        c.setRadius(2.0);
        System.out.println(c);
        System.out.println("radius: " + c.getRadius());

        System.out.println("area: " + c.getArea());
        System.out.println("circumference: " + c.getCircumference());
    }
}
