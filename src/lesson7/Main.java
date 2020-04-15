package lesson7;


import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int CAT_QTY = 5; // колличество котов
    private static int PLATE_QTY = 3; //количестов тарелок
    private static int FOOD_BOX = 100; // коробка с едой.
    private static FoodObserver foodObserver = FoodObserver.getInstance();


    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String numIn;
        boolean exit = false;
        String s;
        // Создаем массив котов и раздаем им имена и случайный аппетит.
        String[] names = {"Мартин", "Матроскин", "Барсик", "Васька", "Рыжик", "Пират", "Пушок", "Тимошка"};
        Cat[] cats = new Cat[CAT_QTY];
        for (int i = 0; i < cats.length; i++) {
            if (i < names.length) {
                cats[i] = new Cat(names[i], (int)(Math.random()*6));
            } else {
                cats[i] = new Cat("Кот" + (i+1), (int)(Math.random()*6));
            }
            foodObserver.addCat(cats[i]);// подписываем котов на еду.
        }

        // Создаем тарелки
        Plate[] plates = new Plate[PLATE_QTY];
        for (int i = 1; i<=PLATE_QTY; i++) {
            plates[i-1] = new Plate();
            foodObserver.addPlate(plates[i-1]); // регистрируем их в обсервере
        }
        do {


            //кладем в тарелки еду
            for (Plate p : plates
            ) {
                if (FOOD_BOX > 0) {
                    System.out.print("Наполним едой миску №" + p.getPlateNumber() + "? (1-да, 0 - нет)");
                    numIn = scanner.next();
                    if (numIn.equals("1")) {
                        FOOD_BOX -= p.addFood(FOOD_BOX);
                    }
                } else {
                    System.out.println("Закончилась еда хозяину пора в магазин");
                }
            }
            // оповещение котов о еде в тарелке происходит в методе addFood класса plate .

            // выводим в консоль информацию о сытости котов
            printCatSatiety(cats);

            // нагуливаем котам аппетит
            System.out.println("Коты отправились гулять");
            for (Cat cat : cats) {
                int nAp = (int) (Math.random()*cat.getMaxAppetite()+1);
                cat.setAppetite(nAp);
            }
            Thread.sleep(5000);
            System.out.println("Коты вернулись и ищут еду в тарелках");
            for (Cat cat : cats) {
                cat.eat();
            }

            System.out.print("Покормим котов ещё раз? (1-да, 0 - нет)");
            if (!"1".equals(scanner.next())){exit = true;}

        }while (!exit);
    }

    private static void printCatSatiety(Cat[] cats) {
        String s;
        for (Cat cat : cats) {
            if (cat.isSatiety()) {
                s = "сыт";
            } else {
                s = "голоден, его аппетит " + cat.getAppetite();
            }
            System.out.println(cat.getName() + " " + s);
        }
    }


}
