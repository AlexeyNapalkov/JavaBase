package lesson1;

import java.util.Scanner;

import static lesson1.Task3.calc;
import static lesson1.Task4.sum10to20;
import static lesson1.Task5.znakPint;
import static lesson1.Task6.znakRet;
import static lesson1.Task7.privetName;
import static lesson1.Task8.visocosnyGod;

public class Task1to2 {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        //Выполнено 1.	Создать пустой проект в IntelliJ IDEA и прописать метод main().
        int a1 = 100;
        short b1 = -32767;
        byte c1 = 127;
        long d1 = 75000000L;
        float e1 = 12.556f;
        double f1 = 222.345;
        boolean ret = false;
        // Выполнено 2.	Создать переменные всех пройденных типов данных и инициализировать их значения.

        System.out.print("Введите число(00,00) a = ");
        float a = scanner.nextFloat();
        System.out.print("Введите число(00,00) b = ");
        float b = scanner.nextFloat();
        System.out.print("Введите число(00,00) c = ");
        float c = scanner.nextFloat();
        System.out.print("Введите число(00,00) d = ");
        float d = scanner.nextFloat();
        System.out.println();

        System.out.println("Задание 3. Вызов метода вычисления по формуле с целыми числами");
        System.out.println("(" + a + " * (" + b + "+(" + c + "/" + d + "))) = " + calc(a, b, c, d));
        System.out.println();

        System.out.println("Задание 4 Вызов метода проверяющего попадание суммы чисел в диапазон");
        System.out.println("a + b = " + (a + b) + " попадет в диапазон от 10 до 20? Ответ:" + sum10to20((int) a, (int) b));

        System.out.print("Задание 5. вывод в консоль отрицательности числа текстом. введите число:");
        a1 = scanner.nextInt();
        znakPint(a1);

        System.out.print("Задание 6. Проверка числа на отрицательность метод возвращает логическое значение. введите число:");
        a1 = scanner.nextInt();
        System.out.println(znakRet(a1));

        System.out.print("Задание 7. Приветствие. Введите ваше имя:");
        String name = scanner.next();
        privetName(name);

        System.out.print("Задание 8. Введите год для определения его високосности:");
        int god = scanner.nextInt();
        visocosnyGod(god);

    }
}