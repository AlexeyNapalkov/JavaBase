package lesson2;

public class Task2to5 {
    public static void task2to5() {
        //2.	Задать пустой целочисленный массив размером 8.
        // С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        System.out.println();
        System.out.println("Задание 2 объявляем массив и заполняем его в цикле i*3");
        int[] massiv2 = new int[8];
        for (int i = 0; i < 8; i++) {
            massiv2[i] = 3 * i;
        }
        for (int m : massiv2) {
            System.out.print(m + " ");
        }
        System.out.println("Выполнено");
        System.out.println();
        //3.	Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
        System.out.println("Задание 3 умножаем элементы массива на 2 если они меньше 6");
        int[] massiv3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int m: massiv3) {
            System.out.print(m+" ");
        }
        System.out.println();
        for (int i = 0; i < massiv3.length; i++){
            if (massiv3[i] < 6 ){ massiv3[i] = 2*massiv3[i];}
        }
        System.out.println("массив после умножения");
        for (int m: massiv3) {
            System.out.print(m + " ");
        }
        System.out.println("Выполнено");
        System.out.println();

        //4.	Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
        // и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
        System.out.println("Задание 4 заполняем квадратный массив по диагонали");
        int[][] massiv4 = new int[8][8];
        for(int i = 0; i < massiv4.length; i++){
            massiv4[i][i] = 1;
            massiv4[i][massiv4.length -1 - i] = 1;
        }

        for (int i =0; i < massiv4.length; i++) {
            for (int j=0; j < massiv4[i].length; j++){
                System.out.print(massiv4[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("Выполнено");
        System.out.println();

        //5.	** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
        System.out.println("Задание 5 поиск мин и мак элемента в одномерном массиве");

        int[] massiv5 = {1, 2, 5, 6, 7, 8, 3, 10, 0, -4, -2, 16, -40, 9};

        System.out.println("Задан одномерный массив");
        for (int m: massiv5) {
            System.out.print(m + " ");
        }
        System.out.println();
        int min = massiv5[0];
        int max = massiv5[0];
        for (int i = 1; i < massiv5.length; i++){
            if (min > massiv5[i]){
                min = massiv5[i];
            }
            if (max < massiv5[i]){
                max = massiv5[i];
            }
        }
        System.out.println("минимальный элемент массива = " + min);
        System.out.println("максмальный элемент массива = " + max);
        System.out.println("Выполнено");

    }
}

