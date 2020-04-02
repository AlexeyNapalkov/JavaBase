package lesson6;

import java.util.Objects;

public class Main {
    public static void main(String[] args){

        Cat cat1 = new Cat("Барсик");
        Cat cat2 = new Cat("Мурзик");

        Dog dog1 = new Dog ("Тузик");
        Dog dog2 = new Dog( "Оникс");

        cat1.run(250);
        cat2.run( 100);
        dog1.run(700);
        dog2.run(100);

        cat1.swim(10);
        cat2.swim(100);
        dog1.swim(15);
        dog2.swim(3);

        System.out.println("Всего животных " + Animal.quantity);
        System.out.println("Из них котов "+ Cat.quantity + " и собак " + Dog.quantity);
    }
}
