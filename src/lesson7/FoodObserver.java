package lesson7;

import java.util.ArrayList;

public class FoodObserver {

    protected ArrayList<Cat> cats = new ArrayList<>();
    protected ArrayList<Plate> plates = new ArrayList<>();


    // синглтон - приватный конструктор не позволяет создавать много объектов класса.
    private FoodObserver(){}

    private static FoodObserver instance;

    // геттер, который создает новый объект, либо если он уже есть, то возвращает уже созданный.
    public static FoodObserver getInstance(){
        if (instance == null) instance = new FoodObserver();
        return  instance;
    }

    // метод добавления кота в массив cats
    public void addCat(Cat cat){cats.add(cat);}

    // метод добавления тарелки в массив тарелок

    public void addPlate(Plate plate){plates.add(plate);
    }

    // принуждение котов к употреблению пищи из тарелки
    public void notifyCat(Plate plate){
        for (Cat cat:cats){
            if (!cat.isSatiety()){
                cat.eatFromPlate(plate);
            }
        }
    }
}
