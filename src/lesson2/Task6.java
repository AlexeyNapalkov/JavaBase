package lesson2;
//6.	** Написать метод, в который передается не пустой одномерный целочисленный массив,
// метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
// Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
// checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.

import java.util.Arrays;
import java.util.Scanner;
public class Task6 {
    public static boolean task6(int[] numArr) {
        // метод для задания 6
        boolean ret = false;
        for (int i = 1; i < numArr.length; i++) {
            int leftSumm = 0;
            int rightSumm = 0;
            for (int j = 0; j < i; j++) {
                leftSumm = leftSumm + numArr[j];
            }
            for (int k = i; k < numArr.length; k++) {
                rightSumm = rightSumm + numArr[k];
            }
            if (leftSumm == rightSumm) {
                ret = true;
                break;
            }
        }
        return ret;
    }
}