package Ex7_2;

import java.util.*;

public class TestPolyLine {
    public static void main(String[] args) {
        // Test default constructor and toString()
        PolyLine l1 = new PolyLine();
        System.out.println(l1); // {}

        // Test appendPoint()
        l1.appendPoint(new Point(1, 2));
        l1.appendPoint(3, 4);
        l1.appendPoint(5, 6);
        System.out.println(l1); // {(1,2)(3,4)(5,6)}

        // Test constructor with a list of points
        List<Point> points = new ArrayList<>();
        points.add(new Point(11, 12));
        points.add(new Point(13, 14));
        PolyLine l2 = new PolyLine(points);
        System.out.println(l2); // {(11,12)(13,14)}

        // Test getLength()
        System.out.println("Length of l1: " + l1.getLength()); // Should calculate the total length of l1
        System.out.println("Length of l2: " + l2.getLength()); // Should calculate the total length of l2
    }
}
