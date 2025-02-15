package Ex2_9;

public class TesrMain {
    public static void main(String[] args) {
        MyTriangle triangle1 = new MyTriangle(0, 0, 3, 0, 3, 4);
        System.out.println(triangle1);
        System.out.println("Perimeter: " + triangle1.getPerimeter());
        triangle1.printType();

        MyPoint p1 = new MyPoint(1, 1);
        MyPoint p2 = new MyPoint(4, 1);
        MyPoint p3 = new MyPoint(2, 3);
        MyTriangle triangle2 = new MyTriangle(p1, p2, p3);
        System.out.println(triangle2);
        System.out.println("Perimeter: " + triangle2.getPerimeter());
        triangle2.printType();
    }
}
