package lesson7;


public class Plate {
    private int plateNumber;
    private int plateSize;
    private int food;

    public Plate(int plateSize) {
        if (plateSize<5){
            plateSize = 5;
        } // если указан размер тарелки меньше 5 то минимальный размер принудительно = 5.
        this.plateSize = plateSize;
        plateNumber = FoodObserver.getInstance().plates.size()+1;
    }

    public Plate() {
        this(5);
        plateNumber = FoodObserver.getInstance().plates.size()+1;
    }

    public int getFood() {
        return food;
    }

    public int getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(int plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int addFood(int food) {

        if (food>0){
            int add2full = plateSize - this.food;
            if(food > add2full){ // если в тарелку положить больше её размера она вернет количество уместившегося

                this.food += add2full;
                System.out.println("В тарелку №" + plateNumber + " добавлено " + add2full + " еды." );
                FoodObserver.getInstance().notifyCat(this);
                return add2full;
            }
        }else{
            if (food<0){ // если забираем еду обратно - то вызываеме метод уменшаения
                decreaseFood(-food);
            }
        }
        return 0;
    }

    public void info(){
        System.out.println(this);
    }

    public int decreaseFood(int foodEat) {
        if (foodEat>0) {
            int stub; // метод возвращает количество еды на которое реально уменшено количество
            food -= foodEat;
            if (food <= 0) {
                System.out.println("тарелка пуста");
                stub = foodEat + food;
                this.food = 0;
                return stub;
            } else {
                System.out.println("в тарелке осталось " + food + " еды");
            }
        }else { // если кот блеванул в тарелку - вызываем метод добавления в тарелку
            if (foodEat<0){
                addFood(-foodEat);
            }
        }return  0;
    }
    @Override
    public String toString() {
        return "Plate{" +
                "food=" + food +
                '}';
    }


    public int getSize() {
        return plateSize;
    }
}
