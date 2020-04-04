package lesson7;

//TODO сделать так что бы аппетит кота увеличивался со временем.

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;

    }
//
//    public Cat() {
//        this.name = "без имени";
//        this.appetite = 0;
//    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public void eat(Plate plate) {
        System.out.println("Кот " + name + " нагулял аппетит: " +appetite);
        if (satiety){
            System.out.println("Кот " + name + " сыт по горло и отказался от еды");
        }else {
            if (plate.getFood() >= appetite) {
                satiety = true;
                System.out.println("Кот " + name + " скушал " + appetite + " еды и наелся");
                plate.decreaseFood(appetite);
            } else {
                if (plate.getFood() == 0){
                    voice();
                }else{
                int foodEat = appetite - plate.getFood();
                if (foodEat > plate.getFood()) {
                    foodEat = plate.getFood();
                }// 1 пункт задания -  если кот хочет сьесть больше чем есть в миске,
                // то он съедает столько сколько есть в миске.
                    appetite -= foodEat; // 2 и пункт задания кот уменьшает свой аппетит на количество съеденного.
                    satiety = false; // кот остался голодным сытость = нет
                    System.out.println("Кот " + name + " скушал " + foodEat + " его аппетит " + appetite);
                    plate.decreaseFood(foodEat);
                    voice();
                }
            }
        }

    }

    public void voice(){
        System.out.println("Кот " + name + " мяукнул.");
    }

}
