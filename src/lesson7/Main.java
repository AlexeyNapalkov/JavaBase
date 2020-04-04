package lesson7;


public class Main {

    public static void main(String[] args) {
        String s;
        // Создаем массив котов и раздаем им имена и случайный аппетит.
        String[] names = {"Мартин", "Матроскин", "Барсик", "Васька", "Рыжик", "Пират", "Пушок", "Тимошка"};
        Cat[] cats = new Cat[5];
        for (int i = 0; i < cats.length; i++) {
            if (i < names.length) {
                cats[i] = new Cat(names[i], (int)(Math.random()*6));
            } else {
                cats[i] = new Cat("Кот" + i, (int)(Math.random()*6));
            }
        }
        // Создаем тарелку
        Plate plate = new Plate(0);

        //TODO подписываем всех котов на тарелку

        //кладем в тареку еду
        plate.addFood(10);

        //TODO оповещаем котов о еде в тарелке по событию.

        // пока нет обсервера кормим всех по очереди
        for (Cat cat : cats) {
            cat.eat(plate);
        }

        // выводим в консоль информацию о сытости котов
        for (Cat cat : cats) {
            if (cat.isSatiety()) {
                s = "сыт";
            } else {
                s = "голоден";
            }
            ;
            System.out.println(cat.getName() + " " + s);
        }

    }
}
