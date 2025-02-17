package Ex3_7;

public class TestMain {
    public static void main(String[] args) {
        Ball ball = new Ball(50, 50, 0);
        Player player1 = new Player(7, 45, 50);
        Player player2 = new Player(10, 55, 50);

        System.out.println(ball);
        System.out.println(player1);
        System.out.println(player2);

        player1.kick(ball);
        System.out.println(ball);

        player2.kick(ball);
        System.out.println(ball);
    }
}
