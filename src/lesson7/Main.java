package lesson7;

public class Main {
    public static void main(String[] args) {

        Cat cat = new Cat("Рыжик", 5);
        Plate plate = new Plate(5);
        plate.info();
        cat.eat(plate);
        plate.info();
        cat.eat(plate);
        plate.info();
        plate.addFood(10); //Observer
        plate.info();
    }
}
