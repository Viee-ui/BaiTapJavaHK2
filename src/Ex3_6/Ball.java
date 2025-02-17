package Ex3_6;

public class Ball {
    private int x, y, radius;
    private int xDelta, yDelta;

    public Ball(int x, int y, int radius, int speed, int direction) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.xDelta = (int) Math.round(speed * Math.cos(Math.toRadians(direction)));
        this.yDelta = (int) Math.round(-speed * Math.sin(Math.toRadians(direction)));
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; }

    public int getXDelta() { return xDelta; }
    public void setXDelta(int xDelta) { this.xDelta = xDelta; }

    public int getYDelta() { return yDelta; }
    public void setYDelta(int yDelta) { this.yDelta = yDelta; }

    public void move() {
        x += xDelta;
        y += yDelta;
    }

    public void reflectHorizontal() {
        xDelta = -xDelta;
    }

    public void reflectVertical() {
        yDelta = -yDelta;
    }

    @Override
    public String toString() {
        return "Ball at (" + x + ", " + y + ") of velocity (" + xDelta + ", " + yDelta + ")";
    }
}
