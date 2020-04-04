package lesson7;
//TODO
// 1. Сделано!!! Сделать так что бы в тарелке не могло оставаться отрицательного количества еды
// 2. Каждому коту добавить поле сытость
// 3. Сделать так что бы кот уменьшал свой апетит на количество съеденной еды,
// а сытость кота наступала только когда он заполняет весь свой аппетит едой.
// увеличивать аппетит кота через некоторое время.
// 4. Создать массив котов тарелку с едой, попросить всех котов покушать эз этой тарелки
// и потом вывести информацию о сытости котов в консоль
// из класса Plate сделать Observer, котрый приглашает котов есть если в миску попала еда.

public class Cat {
    private final String name;
    private int appetite;
    private boolean satiety = false;
    private int full;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        full = appetite;

    }

    public boolean isHungry() {
     //   if (hungry)
        return false;
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void eat(Plate plate) {
        if (plate.getFood()>= appetite){
            satiety = true;
            doEat(plate);
        }else{
            int foodEat = appetite - plate.getFood();
            if (foodEat>plate.getFood()){
                foodEat = plate.getFood();
            }// 1 пункт задания -  если кот хочет сьесть больше чем есть в миске,
            // то он съедает столько сколько есть в миске.
            plate.decreaseFood(foodEat);
            appetite -= foodEat; // 2 и пункт задания кот уменьшает свой аппетит на количество съеденного.
            satiety = false; // кот остался голодным сытость = нет
            System.out.println("Кот остался недоволен");
        }

    }

    public void doEat(Plate plate){
        plate.decreaseFood(appetite);
    }
}
