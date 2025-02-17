package Ex7_2;

import java.util.*;

public class PolyLine {
    private List<Point> points;

    public PolyLine() {
        points = new ArrayList<>(); // Implement with ArrayList
    }

    public PolyLine(List<Point> points) {
        this.points = points;
    }

    public void appendPoint(int x, int y) {
        Point newPoint = new Point(x, y);
        points.add(newPoint);
    }

    public void appendPoint(Point point) {
        points.add(point);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Point point : points) {
            sb.append(point.toString());
        }
        sb.append("}");
        return sb.toString();
    }

    public double getLength() {
        double length = 0.0;
        for (int i = 1; i < points.size(); i++) {
            length += points.get(i - 1).distance(points.get(i));
        }
        return length;
    }
}

