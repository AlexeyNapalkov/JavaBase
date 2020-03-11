package lesson4;

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
    public static int lastStepJ; // строка последнего хода
    public static int lastStepI; // столбец последжнего хода

// рандом кто первый ходит 0 - Игрок - 1 - ИИ
    public static int nextPlayer = (int) (Math.random()*2+1);
    public static String player0MARK;
    public static String player1MARK;
    public static int userCorrectIn = 0;
    public static boolean userInOk = false;
    public static int stepCOUNTER = 0;
    public static String VINER = "";

    public static void main(String[] args) {
        System.out.println("Играем в креситики-нолики.");
// пользователь выбирает ширину поля
        do {
            System.out.println("Ведите ширину поля 3 - 9.");
            SIZE = userInput();
        }while (SIZE < 3 || SIZE >9);
        System.out.println( "Играем на поле размером " + SIZE+"Х"+ SIZE);
        stepCOUNTER = SIZE*SIZE;

// Пользователь определяет длинну выигрышной линии
        do {
            if (SIZE > 3){
                System.out.println("Ведите длинну выигрышной комбинации от 3 до " + SIZE);
                VINLINE = userInput();
            } else VINLINE =3;

        }while ( VINLINE < 3 || VINLINE >SIZE);
        System.out.println( "Победа достанется тому, то поставит на поле " + VINLINE +" в ряд!");
        // инициализация массива с игровым полем
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

        nextPlayer = (int) (Math.random()*2);
        if (nextPlayer == 0) {
            System.out.println("Я тут подумал что ты ходишь первым!");
            nextPlayer = 0;
            player0MARK = DOT_X;
            player1MARK = DOT_0;

        }else {
            System.out.println("Я тут подумал, и решил сходить первым!");
            nextPlayer = 1;
            player0MARK = DOT_0;
            player1MARK = DOT_X;
        }


// Игровой цикл до достижения максимального количества ходов

        VIN = false;
        do {
            if (nextPlayer == 0){
                drawMap(map);
                // ходит человек
                System.out.println("Введи координаты своего хода");

                // проверка правильности хода игрока
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
                            map[j][i] = player0MARK; // записываем на поле ход игрока
                            VIN = checkVIN(map, player0MARK, j, i, VINLINE); // вызов метода проверки последнего хода на выигрыш
                            VINER = "Ты играл за "+player0MARK;
                            nextPlayer = 1; // переключаем цикл на ход компьютера
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
                VIN = checkVIN(map, player1MARK,lastStepJ,lastStepI, VINLINE);
                VINER = "Я "+ player1MARK;
                nextPlayer = 0;
            }

            stepCOUNTER--;
        }while (stepCOUNTER!=0 && !VIN);
        if (VIN){
            System.out.println(VINER + " и выигал эту игру");
        }else {
            System.out.println("Мы оба проиграли наше время!");
        }

drawMap(map);
    }

    public static boolean checkVIN(String[][] map, String playerMARK, int lastStepJ, int lastStepI, int vinLINE) {
        // поиск выигрышной комбинации в строке
        boolean rowVIN;
        int VINcounter = 1;
        int chkStep = 1;
        boolean stopPLUS = false;
        boolean stopMINUS = false;
        do {
            //проверяем возможность хода вправо на величину chkStep и если можем шагаем туда
            if(lastStepI+chkStep<=SIZE&&!stopPLUS){
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[lastStepJ][lastStepI + chkStep].equals(playerMARK)){
                    VINcounter++;
                }else{
                    stopPLUS = true;
                }
            }else {stopPLUS = true;}
            // проверяем возможность хода влево на величину chkStep и если можно шагаем туда
            if(lastStepI-chkStep>0&&!stopMINUS) {
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[lastStepJ][lastStepI - chkStep].equals(playerMARK)) {
                    VINcounter++;
                } else {
                    stopMINUS = true;
                }
            }else {stopMINUS=true;}
            chkStep++;
            // если значение счетчика больше чем vinLINE-1 значит в строке содержится выигрышная комбинация
            rowVIN = VINcounter > vinLINE-1;
            
        }while ( (chkStep < vinLINE -1)||!(stopMINUS&&stopPLUS));



        // поиск выигрышной комбинации в колонке
        boolean colVIN;
        VINcounter = 1;
        chkStep = 1;
        stopPLUS = false;
        stopMINUS = false;
        do {
            //проверяем возможность хода вниз на величину chkStep и если можем шагаем туда
            if(lastStepJ+chkStep<=SIZE&&!stopPLUS){
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[lastStepJ + chkStep][lastStepI].equals(playerMARK)){
                    VINcounter++;
                }else{
                    stopPLUS = true;
                }
            }else {stopPLUS=true;}
            // проверяем возможность хода вверх на величину chkStep и если можно шагаем туда
            if(lastStepJ-chkStep>0&&!stopMINUS){
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[lastStepJ - chkStep][lastStepI].equals(playerMARK)){
                    VINcounter++;
                }else{
                    stopMINUS = true;
                }
            }else {stopMINUS=true;}
            chkStep++;
            // если значение счетчика больше чем vinLINE-1 значит в колонке содержится выигрышная комбинация
            colVIN = VINcounter > vinLINE-1;

        }while ( (chkStep < vinLINE -1)||!(stopMINUS&&stopPLUS));


        // поиск выигрышной комбинации в правой диагонали
        boolean rdiVIN;
        VINcounter = 1;
        chkStep = 1;
        stopPLUS = false;
        stopMINUS = false;
        do {
            //проверяем возможность хода вниз и в право на величину chkStep и если можем шагаем туда
            if(lastStepJ+chkStep<=SIZE && lastStepI+chkStep <=SIZE &&!stopPLUS){
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[lastStepJ + chkStep][lastStepI + chkStep].equals(playerMARK)){
                    VINcounter++;
                }else{
                    stopPLUS = true;
                }
            }else{stopPLUS=true;}
            // проверяем возможность хода вверх и влево на величину chkStep и если можно шагаем туда
            if((lastStepJ-chkStep)>0 && (lastStepI-chkStep)>0 && !stopMINUS){
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[lastStepJ - chkStep][lastStepI - chkStep].equals(playerMARK)){
                    VINcounter++;
                }else{
                    stopMINUS = true;
                }
            }else{stopMINUS=true;}
            chkStep++;
            // если значение счетчика больше чем vinLINE-1 значит в колонке содержится выигрышная комбинация
            rdiVIN = VINcounter > vinLINE-1;

        }while ( (chkStep < vinLINE -1)||!(stopMINUS&&stopPLUS));

        // поиск выигрышной комбинации в левой диагонали
        boolean ldiVIN;
        VINcounter = 1;
        chkStep = 1;
        stopPLUS = false;
        stopMINUS = false;
        do {
            //проверяем возможность хода вверх и в право на величину chkStep и если можем шагаем туда
            if(lastStepJ-chkStep>0 && lastStepI+chkStep <=SIZE &&!stopPLUS){
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[lastStepJ - chkStep][lastStepI + chkStep].equals(playerMARK)){
                    VINcounter++;
                }else{
                    stopPLUS = true;
                }
            }else{stopPLUS=true;}
            // проверяем возможность хода вниз и влево на величину chkStep и если можно шагаем туда
            if((lastStepJ+chkStep)<=SIZE && (lastStepI-chkStep)>0 && !stopMINUS){
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[lastStepJ + chkStep][lastStepI - chkStep].equals(playerMARK)){
                    VINcounter++;
                }else{
                    stopMINUS = true;
                }
            }else{stopMINUS=true;}
            chkStep++;
            // если значение счетчика больше чем vinLINE-1 значит в колонке содержится выигрышная комбинация
            ldiVIN = VINcounter > vinLINE-1;

        }while ( (chkStep < vinLINE -1)||!(stopMINUS&&stopPLUS));

        return rowVIN|colVIN|rdiVIN|ldiVIN;
    }

    public static void randomII(String[][] map) {
        do {
            userInOk = false;
            int j = (int) (1 + Math.random()*(SIZE));
            int i = (int) (1 + Math.random()*(SIZE));
            if (map[j][i].equals(DOT_EMPTY)) {
                userInOk = true;
                map[j][i] = player1MARK;

                lastStepJ = j; // запоминаем координаты последнего хода интеллекта
                lastStepI = i;
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
