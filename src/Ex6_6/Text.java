package Ex6_6;

public class Text {
    public static void main(String[] args) {

     Animal cat = new Cat("Tình");
     Dog dog1 = new Dog("Long");
     Dog dog2 = new Dog("Cam");
     BigDog bigDog1 = new BigDog("Nữ");
     BigDog bigDog2 = new BigDog("Oanh");

     cat.greets();
     dog1.greets();
     dog2.greets(dog2);
     bigDog1.greets(dog1);
     bigDog2.greets(bigDog2);
    }
}
