package lesson7;
//TODO
// 4. Создать массив котов тарелку с едой, попросить всех котов покушать эз этой тарелки
// и потом вывести информацию о сытости котов в консоль
// из класса Plate сделать Observer, котрый приглашает котов есть если в миску попала еда.
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
