package lesson2;
import lesson2.Task2to5;
import lesson2.Task6;
import lesson2.Task7;

import java.util.Arrays;
import java.util.Scanner;

public class Task1 {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        //1.	Задать целочисленный массив, состоящий из элементов 0 и 1.
        // Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
        // С помощью цикла и условия заменить 0 на 1, 1 на 0;
        System.out.println("Задание 1. задаем массив:");
        int[] massiv = {1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1};
        for (int m : massiv) {
            System.out.print(m + " ");
        }
        System.out.println();
        System.out.println("В цикле меняем 0 на 1, а 1 на 0");
        for (int i = 0; i < massiv.length; i++) {
            if (massiv[i] == 0) {
                massiv[i] = 1;
            } else {
                massiv[i] = 0;
            }
        }
        for (int m : massiv) {
            System.out.print(m + " ");
        }
        System.out.println("Выполнено");
        // Выполено
        // вызов сласса с заданиями 2 - 5
        Task2to5.task2to5();

        // подготовка данных для задания 6
        Scanner scanner = new Scanner(System.in);
        System.out.println("Задание 6 метод для поиска в одномерном массиве места равенства правой и левой сумм элементов");
        System.out.println("Введите массив целых чисел через пробел и нажмите ввод:");
        String inpuMassiv = scanner.nextLine();
        int[] numArr = Arrays.stream(inpuMassiv.split(" ")).mapToInt(Integer::parseInt).toArray();
        // вызов класса 6 задания
        System.out.println("Вызванный метод вернул значение:" + Task6.task6(numArr));
        System.out.println("Выполнено");

        // Вввод данных для 7го задания
        System.out.println("Задание 7 сдвиг элементов массива на целое число");
        System.out.println("Элементы введенного ранее масиива будут смещены на введенное число. Введите целое число:");
        int sdvig = scanner.nextInt();
        // вызов класса 7 задания
        Task7.sdvigArr(numArr, sdvig);
        System.out.println("Метод вернул следующий массив");
        for (int m : numArr) {
            System.out.print(m + " ");
        }
        System.out.println("Выполнено");

    }
}