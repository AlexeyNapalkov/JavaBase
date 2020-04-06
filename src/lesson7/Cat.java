package lesson7;



import javax.swing.event.ChangeListener;

public class Cat {
    private String name;
    private int maxAppetite;
    private int appetite;
    private boolean satiety = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        if (appetite!=0){this.maxAppetite = appetite;
        }else {
        this.maxAppetite=5;
        this.satiety=true;
        }


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

    public int getMaxAppetite() {
        return maxAppetite;
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
        this.satiety= this.appetite == 0;
    }

    public void eat() {
        System.out.println("Кот " + name + " нагулял аппетит: " +appetite);
        // кот обходит все тарелки и понижает свой аппетит до 0
        if (satiety){
            System.out.println("Кот " + name + " сыт по горло и отказался от еды");
        }else for (Plate p : FoodObserver.getInstance().plates) {
            if (!satiety){eatFromPlate(p);}
        }

    }

    public void eatFromPlate(Plate plate) {
        System.out.printf("Кот %s подошел к тарелке %s : ", name, plate.getPlateNumber());
        if (plate.getFood() >= appetite) {
            satiety = true;
            System.out.printf(" скушал %s еды и наелся %n", appetite);
            appetite -= plate.decreaseFood(appetite);
        } else {
            if (plate.getFood() > 0) {
                // 1 пункт задания -  если кот хочет сьесть больше чем есть в миске,
                // то он съедает столько сколько есть в миске.
                System.out.printf(" скушал %s еды его аппетит %s %n", plate.getFood(), appetite);
                appetite -= plate.decreaseFood(plate.getFood());// 2 и пункт задания кот уменьшает свой аппетит на количество съеденного.
                satiety = false; // кот остался голодным сытость = нет
            }
            voice(); // если тарелка пуста кот мяукает.
        }
    }

    public void voice(){
        System.out.println("Мяу!");
    }


}
