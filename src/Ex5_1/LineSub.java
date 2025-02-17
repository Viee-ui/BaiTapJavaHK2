package Ex5_1;

public class LineSub extends Point {
    private Point end; // ending point

    // Constructors
    public LineSub(int beginX, int beginY, int endX, int endY) {
        super(beginX, beginY);
        this.end = new Point(endX, endY);
    }

    public LineSub(Point begin, Point end) {
        super(begin.getX(), begin.getY());
        this.end = end;
    }

    // Getter and Setter methods
    public Point getBegin() {
        return new Point(getX(), getY());
    }

    public Point getEnd() {
        return end;
    }

    public void setBegin(int x, int y) {
        setXY(x, y);
    }

    public void setEnd(int x, int y) {
        end.setXY(x, y);
    }

    public int getEndX() {
        return end.getX();
    }

    public int getEndY() {
        return end.getY();
    }

    public void setEndX(int x) {
        end.setX(x);
    }

    public void setEndY(int y) {
        end.setY(y);
    }

    public double getLength() {
        int xDiff = end.getX() - getX();
        int yDiff = end.getY() - getY();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    public double getGradient() {
        int xDiff = end.getX() - getX();
        int yDiff = end.getY() - getY();
        return Math.atan2(yDiff, xDiff);
    }

    @Override
    public String toString() {
        return "LineSub[begin=(" + getX() + ", " + getY() + "), end=" + end + "]";
    }
}
