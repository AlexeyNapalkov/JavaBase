package lesson7;

// TODO тарелка должна создавать событие когда в неё кладут еду

public class Plate {

    private int food;


    public Plate(int food) {
        this.food = food;
    }

    public Plate() {
        this(0);
    }

    public int getFood() {
        return food;
    }

    public void addFood(int food) {
        this.food += food;
    }

    public void info(){
        System.out.println(this);
    }

    public void decreaseFood(int foodEat) {
        food -= foodEat;
        if (food <= 0){
            System.out.println("тарелка пуста");
        }else {
            System.out.println("в тарелке осталось " + food + " еды");
        };
    }
    @Override
    public String toString() {
        return "Plate{" +
                "food=" + food +
                '}';
    }


}
