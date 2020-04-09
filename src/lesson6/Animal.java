package lesson6;

public abstract class Animal {
    static int quantity;
    protected int maxRunDistance;
    protected int maxSwimDistance;

    private String name;

    public Animal(String name) {
        this.name = name;
        quantity++;
    }

    protected Animal() {
        quantity++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void run(int distance);
    public abstract void swim(int distance);
}
