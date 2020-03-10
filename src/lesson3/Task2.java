package lesson3;
// Теперь играем в слова
import java.util.Random;
import java.util.Scanner;

public class Task2 {
    public static void task2() {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        String inWord;
        boolean ex;
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String randWord = words[rand.nextInt(words.length)];
        System.out.println("Играем в угадай слово. Вы вводите слово, если буквы вашего слова совпадают с загаданным, то они появятся на своих местах в подсказке.");
        System.out.println("Игра продолжается до тех пор опка вы не угадаете слово");

        do {
            System.out.print("Введите слово:");
            inWord = scan.next();
            System.out.println();
            ex = inWord.equals(randWord);
            int i;
            for (i = 0; (i < randWord.length())&(i < inWord.length()); i++) {
                if (inWord.charAt(i) == randWord.charAt(i)) {
                    System.out.print(randWord.charAt(i));
                } else {
                    System.out.print("#");
                }
            }
            while (i < 16) {
                System.out.print("#");
                i++;
            }
            System.out.println();
        }while (!ex);
        System.out.println("Поздравляю! Слово угадано!");
    }
}
