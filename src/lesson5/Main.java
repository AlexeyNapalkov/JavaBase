package lesson5;

public class Main {
    public  static void main(String[] args){

/*        Cat cat = new Cat("Мартин", "черный", 1);
        Cat cat2 = new Cat("Мартел");

        System.out.println(cat);
        System.out.println(cat2);
        System.out.println("Возраст кота "+ cat.age);*/

        Employee[] persArray = new Employee[5];
        for (int i =0; i<persArray.length; i++){
            persArray[i] = new Employee();
        }

        for (int i = 0; i<persArray.length; i++){
            if (persArray[i].age>40) {
                persArray[i].printInfo();
            }
        }
    }
}
