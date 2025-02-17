package Ex6_5;

public class Text {
    public static void main(String[] args) {

        //TextCircle
        Circle circle = new Circle(5.0);

        System.out.println("Circle Radius : " + circle.getRadius());
        System.out.println("Circle Area : " + circle.getArea());
        System.out.println("Circle Perimeter : " + circle.getPerimeter());


        //TextResizableCircle
        ResizableCircle resizableCircle = new ResizableCircle(10.0);
        System.out.println("Initial Radius : " + resizableCircle.getRadius());
        System.out.println("Initial Area : " + resizableCircle.getArea());
        System.out.println("Initial Perimeter : " + resizableCircle.getPerimeter());

        //Tăng 50%
        resizableCircle.resize(50);
        System.out.println("\nSau khi tăng kích thước 50% : ");
        System.out.println("Bán kính mới: " + resizableCircle.getRadius());
        System.out.println("Diện tích mới: " + resizableCircle.getArea());
        System.out.println("Chu vi mới: " + resizableCircle.getPerimeter());

        //Giảm 20%
        resizableCircle.resize(-20);
        System.out.println("\nSau khi giảm kích thước 20% : ");
        System.out.println("Bán kính mới: " + resizableCircle.getRadius());
        System.out.println("Diện tích mới: " + resizableCircle.getArea());
        System.out.println("Chu vi mới: " + resizableCircle.getPerimeter());
    }
}
