package Ex4_2;

public class TestMain {
    public static void main(String[] args) {
        Student student = new Student("John Doe", "123 Main St", "Computer Science", 2, 1500.0);
        Staff staff = new Staff("Jane Smith", "456 Elm St", "ABC High School", 3000.0);

        System.out.println(student);
        System.out.println(staff);
    }
}
