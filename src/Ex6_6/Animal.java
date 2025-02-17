package Ex6_6;

public abstract class Animal {
    private String name;

    //Constructor
    public Animal(String name) {
        this.name = name;
    }

    public abstract void greets();
}
