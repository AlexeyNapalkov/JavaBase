package lesson7;

//TODO сделать так что бы аппетит кота увеличивался со временем.

public class Cat {
    private final String name;
    private int appetite;
    private boolean satiety = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;

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
            plate.decreaseFood(appetite);;
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

}
