package lesson3;
// 1	Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки угадать это число.
// При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное, или меньше.
// После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).

import java.util.Random;
import java.util.Scanner;
import lesson3.Task2;

public class Task1 {
    public static Scanner scan = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        int endGame;
        System.out.println("За три попытки угадай число от 0 до 9");
        do {
            int randNum = rand.nextInt(9);
            int inNum = 0;
            for (int i = 0; i < 3; i++) {
                System.out.print("Введите число:");
                inNum = scan.nextInt();
                System.out.println();
                if (inNum == randNum) {
                    System.out.println("Вы угадали!");
                    i = 3;
                } else {
                    if (inNum < randNum) {
                        System.out.println("Загаданное число больше.");
                    } else {
                        System.out.println("Загаданное число меньше.");
                    }
                }
            }
            if (inNum != randNum) {
                System.out.println("Попытки закончились. Вы проиграли");
            }
            System.out.println("Повторить игру ещё раз? 1 - да / 0 - нет:");
            endGame = scan.nextInt();

        } while (endGame == 1);
        // вызов метода второго задания
        Task2.task2();
    }
}