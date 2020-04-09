package lesson6;

public class Cat extends Animal {
//    private final int CatRunDistance = 200;
//    private final int CatSwimDistance = 0;
    static int quantity;
    public Cat(String name) {
        super(name);
        quantity++;
        maxRunDistance = 200;
        maxSwimDistance =0;
    }

    @Override
    public void run(int distance) {
        if (distance>maxRunDistance){
            distance = maxRunDistance;
        }
        System.out.println("Кот "+ getName() + " пробежал "+ distance);
    }

    @Override
    public void swim(int distance) {
        System.out.println("Кот "+ getName() + " не умеет плавать, он утонул");

    }
}
