package Ex4_6;

public class TestMain {
    public static void main(String[] args) {
        Animal animal = new Animal("Buddy");
        System.out.println(animal);

        Mammal mammal = new Mammal("Charlie");
        System.out.println(mammal);

        Cat cat = new Cat("Kitty");
        System.out.println(cat);
        cat.greets();

        Dog dog1 = new Dog("Max");
        Dog dog2 = new Dog("Rocky");
        System.out.println(dog1);
        dog1.greets();
        dog1.greets(dog2);
    }
}
