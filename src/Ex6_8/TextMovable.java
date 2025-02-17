package Ex6_8;

public class TextMovable {
    public static void main(String[] args) {
        MovablePoint point = new MovablePoint(0, 0, 2, 2);
        System.out.println("Before moving: " + point);
        point.moveUp();
        point.moveRight();
        System.out.println("After moving: " + point);

        MovableCircle circle = new MovableCircle(5, 5, 3, 3, 10);
        System.out.println("Before moving: " + circle);
        circle.moveDown();
        circle.moveLeft();
        System.out.println("After moving: " + circle);
    }
}

