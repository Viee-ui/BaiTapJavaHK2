package Ex5_2;

public class TestCylinder {
    public static void main(String[] args) {

        Cylinder c1 = new Cylinder();
        System.out.println(c1); // Cylinder[base=Circle[radius=1.0, color=red], height=1.0]
        System.out.println("Volume: " + c1.getVolume()); // Volume = π * 1^2 * 1


        Cylinder c2 = new Cylinder(2.0, "blue", 5.0);
        System.out.println(c2); // Cylinder[base=Circle[radius=2.0, color=blue], height=5.0]
        System.out.println("Volume: " + c2.getVolume()); // Volume = π * 2^2 * 5

        Circle circle = new Circle(3.0, "green");
        Cylinder c3 = new Cylinder(circle, 4.0);
        System.out.println(c3); // Cylinder[base=Circle[radius=3.0, color=green], height=4.0]
        System.out.println("Volume: " + c3.getVolume()); // Volume = π * 3^2 * 4
    }
}

