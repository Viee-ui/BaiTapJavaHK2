package Ex2_8;

public class TestMain {
    public static void main(String[] args) {
        MyCircle c1 = new MyCircle();
        MyCircle c2 = new MyCircle(3, 4, 5);
        MyCircle c3 = new MyCircle(new MyPoint(6, 8), 10);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);

        System.out.println("Area of c2: " + c2.getArea());
        System.out.println("Circumference of c2: " + c2.getCircumference());
        System.out.println("Distance between c2 and c3: " + c2.distance(c3));
    }
}
