package lesson4;

import java.util.Random;
import java.util.Scanner;

public class TikTakToe {
    public static int SIZE;
    public static int VINLINE;
    public static boolean VIN;
    //   public static String userIn = "";
    public static String[][] map;
    public static final String DOT_EMPTY = "• ";
    public static final String DOT_X = "X ";
    public static final String DOT_0 = "0 ";
    public static final String EMPTY = "  ";

// рандом кто первый ходит 0 - Игрок - 1 - ИИ
    public static int nextPlayer = (int) (Math.random()*2+1);
    public static String Player0;
    public static String Player1;
    public static int userCorrectIn = 0;
    public static boolean userInOk = false;
    public static int stepCOUNTER = 0;
    public static String VINER = "";

    public static void main(String[] args) {
        System.out.println("Играем в креситики-нолики.");
// пользователь выбирает ширину поля
        do {
            System.out.println("Ведите ширину поля 3 - 5.");
            SIZE = userInput();
        }while (SIZE < 3 || SIZE >5);
        System.out.println( "Играем на поле размером " + SIZE+"Х"+ SIZE);
        stepCOUNTER = SIZE*SIZE;

// Пользователь определяет длинну выигрышной линии
        do {
            if (SIZE > 3){
                System.out.println("Ведите длинну выигрышной комбинации от 3 до " + SIZE);
                VINLINE = userInput();
            } else VINLINE =3;

        }while ( VINLINE < 3 || VINLINE >SIZE);
        System.out.println( "Победа достанется тому, то поставит на поле " + VINLINE+" в ряд!");
        // инициализация массива с игровым полем
        // инициализация массива решений
        String[][] map = new String[SIZE+1][SIZE+1] ;
        map [0][0] = EMPTY;
        for (int i = 1; i<=SIZE; i++ ){
            map [0][i] = i +" ";
        }
        for ( int j = 1; j <= SIZE; j++){
            map[j][0] = j + " ";
            for ( int i = 1; i <= SIZE; i++){
                map[j][i] = DOT_EMPTY;
            }
        }

        nextPlayer = (int) (Math.random()*2+1);
        if (nextPlayer == 0) {
            System.out.println("Я тут подумал что ты ходишь первым!");
            nextPlayer = 0;
            Player0 = DOT_X;
            Player1 = DOT_0;

        }else {
            System.out.println("Я тут подумал, и решил сходить первым!");
            nextPlayer = 1;
            Player0 = DOT_0;
            Player1 = DOT_X;
        }


// Игровой цикл до достижения максимального количества ходов

        VIN = false;
        do {
            if (nextPlayer == 0){
                drawMap(map);
                // ходит человек
                System.out.println("Введи координаты своего хода");

                // проверка правильности хода
                do {
                    userInOk = false;
                    System.out.println("Строка");
                    int j = userInput();
                    System.out.println("Столбец:");
                    int i = userInput();
                    if (i<SIZE+1 && 0<i && j<SIZE+1 && 0<j){
                        if (map[j][i].equals(DOT_EMPTY)) {
                            System.out.println("Хороший выбор!");
                            userInOk = true;
                            map[j][i] = Player0;
                            nextPlayer = 1;
                        }else {
                            System.out.println("Ячейка уже занята! вводи снова.");
                        }
                    }else {
                        System.out.println("Плохой выбор! вводи снова.");
                    }
                }while (!userInOk);
            }else{
                // ходит ИИ
                randomII(map);
                nextPlayer = 0;
            }
            stepCOUNTER--;
            // проверка
            //1 в массиве построчно последовательность из Х или 0 в количестве равном VINLINE
            for (int j = 1; j<=SIZE-VINLINE+1; j++){
                int counterX = 0;
                int counter0 = 0;
                int counterX1 = 0;
                int counter01 = 0;
                for (int i = 1; i <=SIZE-VINLINE+1; i++){
                    if (map[j][i].equals(DOT_X)){counterX++;}
                    if (map[j][i].equals(DOT_0)){counter0++;}
                    if (map[i][j].equals(DOT_X)){counterX1++;}
                    if (map[i][j].equals(DOT_0)){counter01++;}
                    if (counter0>=VINLINE || counter01>=VINLINE) {VIN = true; VINER = DOT_0;}
                    else {if (counterX>=VINLINE || counterX1>=VINLINE) {VIN = true; VINER = DOT_X;}
                            else {VIN = false; VINER = "никто";}
                    }

                }

            }

        }while (stepCOUNTER!=0 && !VIN);
System.out.println("Выигал "+ VINER);
    }

    public static void randomII(String[][] map) {
        do {
            userInOk = false;
            int j = (int) (1 + Math.random()*(SIZE));
            int i = (int) (1 + Math.random()*(SIZE));
            if (map[j][i].equals(DOT_EMPTY)) {
                userInOk = true;
                map[j][i] = Player1;
                nextPlayer = 0;
            }
        }while (!userInOk);
    }

    private static void drawMap(String[][] map) {
        for ( int j = 0; j <= SIZE; j++){
            for (int i = 0; i <= SIZE; i++){
                System.out.print(map[j][i]);
            }System.out.println();
        }
    }

    private static int userInput() {
        Scanner scan = new Scanner(System.in);
        try {
            return scan.nextInt();
        } catch (Exception e) {
            System.out.println("Введенное знечение не число. Повторите ввод:");
        }
        return 0;
    }


}
