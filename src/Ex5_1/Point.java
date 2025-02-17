package Ex5_1;

public class Point {
    private int x; // x-coordinate
    private int y; // y-coordinate

    // Constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter and Setter methods
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // toString method
    @Override
    public String toString() {
        return "Point: (" + x + ", " + y + ")";
    }
}

