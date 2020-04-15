package lesson6;

public class Dog extends Animal {
    static int quantity;
//    private final int DogRunDistance = 500;
//    private final int DogSwimDistance = 10;
    public Dog() {
    }

    public Dog(String name) {
        super(name);
        quantity++;
        maxRunDistance = 500;
        maxSwimDistance = 10;
    }
    @Override
    public void run(int distance) {
        if (distance>maxRunDistance){
            distance = maxRunDistance;
        }
        System.out.println("Собак "+ getName() + " пробежал "+ distance);
    }

    @Override
    public void swim(int distance) {
        if (distance>maxSwimDistance){
            distance = maxSwimDistance;
        }
        System.out.println("Собак "+ getName() + " проплыл "+ distance);
    }


}
