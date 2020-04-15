package lesson1;

public class Task8 {
    public static void visocosnyGod(int god){
        float del4 = (float) god / 4;
        float del400 = (float) god / 400;
        float del100 = (float) god / 100;

        if (del400 % 1 == 0){
            System.out.print("год " + god + " високосный - каждый 400");
        }else {
            if (del100 % 1 == 0){
                System.out.print("год " + god + " високосный - каждый 100");
            }else {
                if (del4 % 1 == 0){
                    System.out.print("год " + god + " високосный - каждый 4");
                }else System.out.print("год " + god + " не високосный");
            }
        }
    }
    //Выполнено 8.	*Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль.
    // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.

}
