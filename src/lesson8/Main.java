package lesson8;


import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static String[][] map;
    public static int[][] mapX;
    public static int[][] map0;
    public static int SIZE;
    public static int DOTS_TO_VIN;
    public static final String DOT_EMPTY = "• ";
    public static final String DOT_X = "X ";
    public static final String DOT_0 = "0 ";
    public static final String EMPTY = "  ";
    public static int nextPlayer; // определитель следующего игрока 0 - человек 1 - компьютер
    public static String userMARK; // фишка человека
    public static String computerMARK; // фишка компьютера
    public static int stepCounter; // Счетчик ходов
    public static boolean VIN; // флаг победы
    public static String END_MESSAGE;
    public static Scanner scan = new Scanner(System.in);
    public static int lastY, lastX; // координаты последнего хода
    public static boolean gameToEnd = false;
    public static boolean userHasTurn;


    // инициация игрового поля размером SIZE
    public static void initMap(int SIZE) {
        map = new String[1 + SIZE][1 + SIZE];
        // ячейка с нулевыми координатами - пустая
        map[0][0] = EMPTY;

        for (int i = 1; i <= SIZE; i++) {
            map[0][i] = i + " "; // нулевая строка содержит коорднаты колонок
            map[i][0] = i + " "; // нулевая колонка заполняется координатами строк
        }
        for (int j = 1; j <= SIZE; j++) {
            for (int i = 1; i <= SIZE; i++) {
                map[j][i] = DOT_EMPTY; // остальные ячейки точками
            }
        }
    }

    //TODO метод вывода игрового поля в консоль заменить на создание и перерисовку окна.
    public static void printMap(String map[][]) {
        for (int j = 0; j <= SIZE; j++) {
            for (int i = 0; i <= SIZE; i++) {
                System.out.print(map[j][i]);
            }
            System.out.println();
        }
    }

    //TODO убрать метод вывода челочисленных массивов в консоль
    public static void printIntMap(int mapInt[][]) {
        for (int j = 0; j <= SIZE; j++) {
            for (int i = 0; i <= SIZE; i++) {
                if (mapInt[j][i] < 10) {
                    System.out.print(" ");
                }
                System.out.print(mapInt[j][i] + " ");
            }
            System.out.println();
        }
    }

    // метод ход человека
    public static void userTurn() {
        int rowY = 0, colX = 0;
        boolean goodIN = false;
        do {
            System.out.println("Введи координаты:");
            System.out.print("Строка ");
            rowY = userInput();
            System.out.print("Столбец ");
            colX = userInput();
            System.out.println();
            if (rowY < 1 || rowY > SIZE || colX < 1 || colX > SIZE) {
                System.out.println("Координаты за пределом поля повтори ввод");
            } else {
                if (map[rowY][colX] == DOT_EMPTY) {
                    goodIN = true;
                    lastY = rowY;
                    lastX = colX;
                } else {
                    System.out.println("Ячейка уже занята выбери другую");
                }
            }
        } while (!goodIN);
    }

    // метод рандомного выбора компьютером свободной ячейки на поле
    public static void randomTurn() {
        boolean userInOk = false;
        do {
            int j = (int) (1 + Math.random() * (SIZE));
            int i = (int) (1 + Math.random() * (SIZE));
            if (map[j][i].equals(DOT_EMPTY)) {
                userInOk = true;
                // map[j][i] = computerMARK; // занимаем ячейку на поле
                lastY = j; // запоминаем координаты последнего хода компьютера
                lastX = i;
            }
        } while (!userInOk);
    }

    //TODO оформить в окне метод для ввода человеком чисел с консоли возвращает целое число или ругается если не число
    private static int userInput() {
        Scanner scan = new Scanner(System.in);
        try {
            return scan.nextInt();
        } catch (Exception e) {
            System.out.println("Введенное знечение не число. Повторите ввод:");
        }
        return 0;
    }

    //главный метод пакета
    public static void main(String[] args) throws InterruptedException {
        // задаем размер массива по умолчанию 3Х3
        SIZE = 3;
        DOTS_TO_VIN = 3;

//TODO Заменить на выбор размера игрового поля из меню
// деалем окно настроек с доп окном - ширина поля и длинна выигрышной комбинации
// по сохранении настроек окно главное окно переинициализируется.
// на выходе две переменные SIZE и DOTS_TO_VIN
//
// ------ это на удаление после готовности окна-------
//         System.out.println("Играем в крестики нолики на квадратном поле ");
//        System.out.print("Какого размера поляну накрывать? ");
//        SIZE = userInput();
//        if (SIZE < 3) {
//            System.out.println("Меньше трех играй один ");
//            SIZE = 3;
//        }
//        System.out.println("Играем на поле размером " + SIZE + " X " + SIZE);
//
//        // задаем длинну выигрышной комбинации
//        if (SIZE < 4) {
//            DOTS_TO_VIN = 3;
//        } else {
//            System.out.println("Сколько знаков подряд будем считать победой?");
//            do {
//                DOTS_TO_VIN = userInput();
//                if (DOTS_TO_VIN < 3) {
//                    System.out.println("Меньше трех играй один ");
//                    DOTS_TO_VIN = 3;
//                }
//                if (DOTS_TO_VIN > SIZE) {
//                    System.out.println("Количество знаков выигрышной линии должно быть меньше " + SIZE);
//                }
//            } while (DOTS_TO_VIN > SIZE);
//        }
//        System.out.println("Победит тот, кто первый поставит в ряд " + DOTS_TO_VIN + " знака");



        // инициируем игровое поле

        initMap(SIZE);

        // создаем два поля возможных побед для 0 и Х
        mapX = new int[1 + SIZE][1 + SIZE];
        map0 = new int[1 + SIZE][1 + SIZE];
        for (int i = 1; i <= SIZE; i++) {
            mapX[0][i] = i;
            mapX[i][0] = i;
            map0[0][i] = i;
            map0[i][0] = i;
        }

        // рандомный выбор первого игрока
        nextPlayer = (int) (Math.random() + 0.499);
        if (nextPlayer == 0) {

            // вывод окна с текстом ("Я тут подумал что ты ходишь первым!")
            DialogWindow DW = new DialogWindow("Я тут подумал и решил, что ты ходишь первым!");
            DW.pack();
            DW.setVisible(true);
            nextPlayer = 0;
            userMARK = DOT_X;
            computerMARK = DOT_0;

        } else {
            //вывод окна с текстом ("Я тут подумал, и решил сходить первым!");
            DialogWindow DW = new DialogWindow("Я тут подумал, и решил сходить первым!");
            DW.pack();
            DW.setVisible(true);
            nextPlayer = 1;
            userMARK = DOT_0;
            computerMARK = DOT_X;
        }

        //Выводим пустое игровое поле в окною
        TikTakWindow window1 = new TikTakWindow(map);

        // игровой цикл до победы или последней свободной ячейки
        stepCounter = SIZE * SIZE;
        do {
            END_MESSAGE = "Ты такой же умный как я, мы оба проиграли!";
            if (nextPlayer == 0) {
                
                // цикл ожидающий что MyListener установит переменную userHasTurn = true
                do {
                    // меняем фон случайной кнопки на зеленый
                    Thread.sleep(1000);
                    // меняем фон случайной кнопки на обычный
                }while (!userHasTurn);

                VIN = checkVIN(userMARK, lastY, lastX);
                if (VIN) {
                    END_MESSAGE = "Ты играл " + userMARK + " и выиграл! Поздравляю!";
                }
                nextPlayer = 1;
            } else {
                // ход компьютера
                possibleVIN_maps(); // обновляем поля возможностей
                check2VIN(userMARK);
                check2VIN(computerMARK);
                bestTurn(computerMARK);
                //System.out.println("Я сходил строка " + lastY + " столбец " + lastX);
                map[lastY][lastX] = computerMARK;
                window1.jbs[lastY][lastX].setText(computerMARK);
                window1.jbs[lastY][lastX].setEnabled(false);
                VIN = checkVIN(computerMARK, lastY, lastX);
                if (VIN) {
                    END_MESSAGE = "Я играл " + computerMARK + " и выиграл!";
                }
                nextPlayer = 0;
                userHasTurn = false;
            }


//            if (!gameToEnd){
                if (!isVINposiable()){
                    DialogWindow DW = new DialogWindow("На игровом поле не осталось места ни для одной выигрышной комбинации");
                    DW.pack();
                    DW.setVisible(true);
                    stepCounter = 1;
                }
//            }
            stepCounter--;
        } while (!VIN & stepCounter > 0);
        DialogWindow DW = new DialogWindow( END_MESSAGE);
        DW.pack();
        DW.setVisible(true);

    }

    // метод возвращает true если установка в точку с координатами Y X для указанного знака приводит к победе
    private static boolean checkVIN(String MARK, int Y, int X) {
        // поиск выигрышной комбинации в строке
        boolean rowVIN;
        int VINcount = 1;
        int chkStep = 1;
        boolean stopPLUS = false;
        boolean stopMINUS = false;
        do {
            //проверяем возможность хода вправо на величину chkStep и если можем шагаем туда
            if (X + chkStep <= SIZE && !stopPLUS) {
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[Y][X + chkStep].equals(MARK)) {
                    VINcount++;
                } else {
                    stopPLUS = true;
                }
            } else {
                stopPLUS = true;
            }
            // проверяем возможность хода влево на величину chkStep и если можно шагаем туда
            if (X - chkStep > 0 && !stopMINUS) {
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[Y][X - chkStep].equals(MARK)) {
                    VINcount++;
                } else {
                    stopMINUS = true;
                }
            } else {
                stopMINUS = true;
            }
            chkStep++;
            // если значение счетчика больше чем vinLINE-1 значит в строке содержится выигрышная комбинация
            rowVIN = VINcount > DOTS_TO_VIN - 1;

        } while ((chkStep < DOTS_TO_VIN - 1) || !(stopMINUS && stopPLUS));


        // поиск выигрышной комбинации в колонке
        boolean colVIN;
        VINcount = 1;
        chkStep = 1;
        stopPLUS = false;
        stopMINUS = false;
        do {
            //проверяем возможность хода вниз на величину chkStep и если можем шагаем туда
            if (Y + chkStep <= SIZE && !stopPLUS) {
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[Y + chkStep][X].equals(MARK)) {
                    VINcount++;
                } else {
                    stopPLUS = true;
                }
            } else {
                stopPLUS = true;
            }
            // проверяем возможность хода вверх на величину chkStep и если можно шагаем туда
            if (Y - chkStep > 0 && !stopMINUS) {
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[Y - chkStep][X].equals(MARK)) {
                    VINcount++;
                } else {
                    stopMINUS = true;
                }
            } else {
                stopMINUS = true;
            }
            chkStep++;
            // если значение счетчика больше чем vinLINE-1 значит в колонке содержится выигрышная комбинация
            colVIN = VINcount > DOTS_TO_VIN - 1;

        } while ((chkStep < DOTS_TO_VIN - 1) || !(stopMINUS && stopPLUS));


        // поиск выигрышной комбинации в правой диагонали
        boolean rdiVIN;
        VINcount = 1;
        chkStep = 1;
        stopPLUS = false;
        stopMINUS = false;
        do {
            //проверяем возможность хода вниз и в право на величину chkStep и если можем шагаем туда
            if (Y + chkStep <= SIZE && X + chkStep <= SIZE && !stopPLUS) {
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[Y + chkStep][X + chkStep].equals(MARK)) {
                    VINcount++;
                } else {
                    stopPLUS = true;
                }
            } else {
                stopPLUS = true;
            }
            // проверяем возможность хода вверх и влево на величину chkStep и если можно шагаем туда
            if ((Y - chkStep) > 0 && (X - chkStep) > 0 && !stopMINUS) {
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[Y - chkStep][X - chkStep].equals(MARK)) {
                    VINcount++;
                } else {
                    stopMINUS = true;
                }
            } else {
                stopMINUS = true;
            }
            chkStep++;
            // если значение счетчика больше чем vinLINE-1 значит в колонке содержится выигрышная комбинация
            rdiVIN = VINcount > DOTS_TO_VIN - 1;

        } while ((chkStep < DOTS_TO_VIN - 1) || !(stopMINUS && stopPLUS));

        // поиск выигрышной комбинации в левой диагонали
        boolean ldiVIN;
        VINcount = 1;
        chkStep = 1;
        stopPLUS = false;
        stopMINUS = false;
        do {
            //проверяем возможность хода вверх и в право на величину chkStep и если можем шагаем туда
            if (Y - chkStep > 0 && X + chkStep <= SIZE && !stopPLUS) {
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[Y - chkStep][X + chkStep].equals(MARK)) {
                    VINcount++;
                } else {
                    stopPLUS = true;
                }
            } else {
                stopPLUS = true;
            }
            // проверяем возможность хода вниз и влево на величину chkStep и если можно шагаем туда
            if ((Y + chkStep) <= SIZE && (X - chkStep) > 0 && !stopMINUS) {
                // сравниваем значение ячеки со сначением PlayerMARK если совпадает, то поле наше значит увеличиваем счетчик
                if (map[Y + chkStep][X - chkStep].equals(MARK)) {
                    VINcount++;
                } else {
                    stopMINUS = true;
                }
            } else {
                stopMINUS = true;
            }
            chkStep++;
            // если значение счетчика больше чем vinLINE-1 значит в колонке содержится выигрышная комбинация
            ldiVIN = VINcount > DOTS_TO_VIN - 1;

        } while ((chkStep < DOTS_TO_VIN - 1) || !(stopMINUS && stopPLUS));

        return rowVIN | colVIN | rdiVIN | ldiVIN;

    }

    // метод сканирует игровое поле и для каждой ячейки расчитывает количество возможных победных комбинаций
    // результат метода поле возможностей для Х и поле возможностей для 0
    public static void possibleVIN_maps() {
        for (int i = 1; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++) {
                map0[i][j] = 0;
                mapX[i][j] = 0;
            }
        }// сброс массивов

        for (int j = 1; j <= SIZE; j++) {
            int lineCount = 0;
            int xCount = 0;
            for (int i = 1; i <= SIZE; i++) {
                if (map[j][i] != DOT_0) {
                    lineCount++;
                    if (map[j][i] == DOT_X) {
                        xCount++;
                    } //  счетчик Х-ов в нашей комбинации

                } else {
                    lineCount = 0;
                }
                if (lineCount >= DOTS_TO_VIN) {
                    for (int s = DOTS_TO_VIN - 1; s >= 0; s--) {
                        if (i - s > 0) {
                            if (map[j][i - s] != DOT_X) {
                                mapX[j][i - s]++; // увеличиваем значение на 1 если комбинация возможна
                                mapX[j][i - s] = mapX[j][i - s] + 3 * xCount; // увеличиваем значение на количество Х-ов в комбинации
                                if (map[j][i - s] == DOT_EMPTY) {
                                    map0[j][i - s] = map0[j][i - s] + 3 * xCount;
                                } // увеличиваем вес ячейки в map0 как угрозу
                            }
                        }
                    }
                }

            }
        } // подсчет комбинации для поля mapX в строках

        for (int j = 1; j <= SIZE; j++) {
            int lineCount = 0;
            int xCount = 0;
            for (int i = 1; i <= SIZE; i++) {
                if (map[i][j] != DOT_0) {
                    lineCount++;
                    if (map[i][j] == DOT_X) {
                        xCount++;
                    } //  счетчик Х-ов в нашей комбинации
                } else {
                    lineCount = 0;
                }
                if (lineCount >= DOTS_TO_VIN) {
                    for (int s = DOTS_TO_VIN - 1; s >= 0; s--) {
                        if (i - s > 0) {
                            if (map[i - s][j] != DOT_X) {
                                mapX[i - s][j]++; // увеличиваем значение на 1 если комбинация возможна
                                mapX[i - s][j] = mapX[i - s][j] + 3 * xCount; // увеличиваем значение на количество Х-ов в комбинации
                                if (map[i - s][j] == DOT_EMPTY) {
                                    map0[i - s][j] = map0[i - s][j] + 3 * xCount;
                                } // увеличиваем вес ячейки в map0 как угрозу
                            }
                        }
                    }
                }
            }
        } // подсчет комбинации для поля mapX в колонках

        for (int j = 1; j <= SIZE - DOTS_TO_VIN + 1; j++) {
            int lineCount = 0;
            int xCount = 0;
            for (int d = 0; d <= (SIZE - j); d++) {
                if (map[j + d][1 + d] != DOT_0) {
                    lineCount++;
                    if (map[j + d][1 + d] == DOT_X) {
                        xCount++;
                    } //  счетчик Х-ов в нашей комбинации
                } else {
                    lineCount = 0;
                }
                if (lineCount >= DOTS_TO_VIN) { // если комбинация возможна для победы Х ов
                    for (int s = DOTS_TO_VIN - 1; s >= 0; s--) {
                        if (1 + d - s > 0) {
                            if (map[j + d - s][1 + d - s] != DOT_X) {
                                mapX[j + d - s][1 + d - s]++;// увеличиваем значение на 1 если комбинация возможна
                                mapX[j + d - s][1 + d - s] = mapX[j + d - s][1 + d - s] + 3 * xCount; // увеличиваем значение на количество Х-ов в комбинации
                                if (map[j + d - s][1 + d - s] == DOT_EMPTY) {
                                    map0[j + d - s][1 + d - s] = map0[j + d - s][1 + d - s] + 3 * xCount;
                                } // увеличиваем вес ячейки в map0 как угрозу
                            }
                        }
                    }
                }
            }
        }// нижняя половина правых диагоналей mapX

        for (int i = 2; i <= SIZE - DOTS_TO_VIN + 1; i++) {
            int lineCount = 0;
            int xCount = 0;
            for (int d = 0; d <= SIZE - i; d++) {
                if (map[1 + d][i + d] != DOT_0) {
                    lineCount++;
                    if (map[1 + d][i + d] == DOT_X) {
                        xCount++;
                    } //  счетчик Х-ов в нашей комбинации
                } else {
                    lineCount = 0;
                }
                if (lineCount >= DOTS_TO_VIN) {
                    for (int s = DOTS_TO_VIN - 1; s >= 0; s--) {
                        if (1 + d - s > 0) {
                            if (map[1 + d - s][i + d - s] != DOT_X) {
                                mapX[1 + d - s][i + d - s]++;// увеличиваем значение на 1 если комбинация возможна
                                mapX[1 + d - s][i + d - s] = mapX[1 + d - s][i + d - s] + 3 * xCount; // увеличиваем значение на количество Х-ов в комбинации
                                if (map[1 + d - s][i + d - s] == DOT_EMPTY) {
                                    map0[1 + d - s][i + d - s] = map0[1 + d - s][i + d - s] + 3 * xCount;
                                } // увеличиваем вес ячейки в map0 как угрозу
                            }
                        }
                    }
                }
            }
        }// верхняя часть правых диагоналей mapX

        for (int j = 1; j <= SIZE - DOTS_TO_VIN + 1; j++) {
            int lineCount = 0;
            int xCount = 0;
            for (int d = 0; d <= (SIZE - j); d++) {
                if (map[j + d][SIZE - d] != DOT_0) {
                    lineCount++;
                    if (map[j + d][SIZE - d] == DOT_X) {
                        xCount++;
                    } //  счетчик Х-ов в нашей комбинации

                } else {
                    lineCount = 0;
                }
                if (lineCount >= DOTS_TO_VIN) {
                    for (int s = DOTS_TO_VIN - 1; s >= 0; s--) {
                        if (SIZE - d + s >= 1) {
                            if (map[j + d - s][SIZE - d + s] != DOT_X) {
                                mapX[j + d - s][SIZE - d + s]++;// увеличиваем значение на 1 если комбинация возможна
                                mapX[j + d - s][SIZE - d + s] = mapX[j + d - s][SIZE - d + s] + 3 * xCount; // увеличиваем значение на количество Х-ов в комбинации
                                if (map[j + d - s][SIZE - d + s] == DOT_EMPTY) {
                                    map0[j + d - s][SIZE - d + s] = map0[j + d - s][SIZE - d + s] + 3 * xCount;
                                } // увеличиваем вес ячейки в map0 как угрозу
                            }
                        }
                    }
                }
            }
        }// нижняя половина левых диагоналей mapX

        for (int i = SIZE - DOTS_TO_VIN + 1; i < SIZE; i++) {
            int lineCount = 0;
            int xCount = 0;
            for (int d = 0; d < i; d++) {
                if (map[i - d][1 + d] != DOT_0) {
                    lineCount++;
                    if (map[i - d][1 + d] == DOT_X) {
                        xCount++;
                    } //  счетчик Х-ов в нашей комбинации
                } else {
                    lineCount = 0;
                }
                if (lineCount >= DOTS_TO_VIN) {
                    for (int s = DOTS_TO_VIN - 1; s >= 0; s--) {
                        if (i - d + s >= 1) {
                            if (map[i - d + s][1 + d - s] != DOT_X) {
                                mapX[i - d + s][1 + d - s]++;// увеличиваем значение на 1 если комбинация возможна
                                mapX[i - d + s][1 + d - s] = mapX[i - d + s][1 + d - s] + 3 * xCount;// увеличиваем значение на количество Х-ов в комбинации
                                if (map[i - d + s][1 + d - s] == DOT_EMPTY) {
                                    map0[i - d + s][1 + d - s] = map0[i - d + s][1 + d - s] + 3 * xCount;
                                } // увеличиваем вес ячейки в map0 как угрозу
                            }
                        }
                    }
                }
            }
        }// верхняя часть левых диагоналей mapX


        for (int j = 1; j <= SIZE; j++) {
            int lineCount = 0;
            int xCount = 0;
            for (int i = 1; i <= SIZE; i++) {
                if (map[j][i] != DOT_X) {
                    lineCount++;
                    if (map[j][i] == DOT_0) {
                        xCount++;
                    } //  счетчик 0-ей в нашей комбинации
                } else {
                    lineCount = 0;
                }
                if (lineCount >= DOTS_TO_VIN) {
                    for (int s = DOTS_TO_VIN - 1; s >= 0; s--) {
                        if (i - s > 0) {
                            if (map[j][i - s] != DOT_0) {
                                map0[j][i - s]++;// увеличиваем значение на 1 если комбинация возможна
                                map0[j][i - s] = map0[j][i - s] + 3 * xCount; // увеличиваем значение на количество 0-ов в комбинации
                                if (map[j][i - s] == DOT_EMPTY) {
                                    mapX[j][i - s] = mapX[j][i - s] + 3 * xCount;
                                } // увеличиваем вес ячейки в mapX как угрозу
                            }
                        }
                    }
                }

            }
        } // подсчет комбинации для поля map0 в строке

        for (int j = 1; j <= SIZE; j++) {
            int lineCount = 0;
            int xCount = 0;
            for (int i = 1; i <= SIZE; i++) {
                if (map[i][j] != DOT_X) {
                    lineCount++;
                    if (map[i][j] == DOT_0) {
                        xCount++;
                    } //  счетчик 0-ей в нашей комбинации
                } else {
                    lineCount = 0;
                }
                if (lineCount >= DOTS_TO_VIN) {
                    for (int s = DOTS_TO_VIN - 1; s >= 0; s--) {
                        if (i - s > 0) {
                            if (map[i - s][j] != DOT_0) {
                                map0[i - s][j]++;// увеличиваем значение на 1 если комбинация возможна
                                map0[i - s][j] = map0[i - s][j] + 3 * xCount; // увеличиваем значение на количество 0-ов в комбинации
                                if (map[i - s][j] == DOT_EMPTY) {
                                    mapX[i - s][j] = mapX[i - s][j] + 3 * xCount;
                                } // увеличиваем вес ячейки в mapX как угрозу
                            }
                        }
                    }
                }
            }
        } // подсчет комбинации для поля map0 в колонках

        for (int j = 1; j <= SIZE - DOTS_TO_VIN + 1; j++) {
            int lineCount = 0;
            int xCount = 0;
            for (int d = 0; d <= (SIZE - j); d++) {
                if (map[j + d][1 + d] != DOT_X) {
                    lineCount++;
                    if (map[j + d][1 + d] == DOT_0) {
                        xCount++;
                    } //  счетчик 0-ей в нашей комбинации
                } else {
                    lineCount = 0;
                }
                if (lineCount >= DOTS_TO_VIN) {
                    for (int s = DOTS_TO_VIN - 1; s >= 0; s--) {
                        if (1 + d - s > 0) {
                            if (map[j + d - s][1 + d - s] != DOT_0) {
                                map0[j + d - s][1 + d - s]++;// увеличиваем значение на 1 если комбинация возможна
                                map0[j + d - s][1 + d - s] = map0[j + d - s][1 + d - s] + 3 * xCount; // увеличиваем значение  3x количество 0-ов в комбинации
                                if (map[j + d - s][1 + d - s] == DOT_EMPTY) {
                                    mapX[j + d - s][1 + d - s] = mapX[j + d - s][1 + d - s] + 3 * xCount;
                                } // увеличиваем вес ячейки в mapX как угрозу
                            }
                        }
                    }
                }
            }
        }// нижняя половина правых диагоналей map0

        for (int i = 2; i <= SIZE - DOTS_TO_VIN + 1; i++) {
            int lineCount = 0;
            int xCount = 0;
            for (int d = 0; d <= SIZE - i; d++) {
                if (map[1 + d][i + d] != DOT_X) {
                    lineCount++;
                    if (map[1 + d][i + d] == DOT_0) {
                        xCount++;
                    } //  счетчик 0-ей в нашей комбинации
                } else {
                    lineCount = 0;
                }
                if (lineCount >= DOTS_TO_VIN) {
                    for (int s = DOTS_TO_VIN - 1; s >= 0; s--) {
                        if (1 + d - s > 0) {
                            if (map[1 + d - s][i + d - s] != DOT_0) {
                                map0[1 + d - s][i + d - s]++;// увеличиваем значение на 1 если комбинация возможна
                                map0[1 + d - s][i + d - s] = map0[1 + d - s][i + d - s] + 3 * xCount; // увеличиваем значение на 3x количество 0-ов в комбинации
                                if (map[1 + d - s][i + d - s] == DOT_EMPTY) {
                                    mapX[1 + d - s][i + d - s] = mapX[1 + d - s][i + d - s] + 3 * xCount;
                                } // увеличиваем вес ячейки в mapX как угрозу
                            }
                        }
                    }
                }
            }
        }// верхняя часть правых диагоналей map0

        for (int j = 1; j <= SIZE - DOTS_TO_VIN + 1; j++) {
            int lineCount = 0;
            int xCount = 0;
            for (int d = 0; d <= (SIZE - j); d++) {
                if (map[j + d][SIZE - d] != DOT_X) {
                    lineCount++;
                    if (map[j + d][SIZE - d] == DOT_0) {
                        xCount++;
                    } //  счетчик 0-ей в нашей комбинации
                } else {
                    lineCount = 0;
                }
                if (lineCount >= DOTS_TO_VIN) {
                    for (int s = DOTS_TO_VIN - 1; s >= 0; s--) {
                        if (SIZE - d + s >= 1) {
                            if (map[j + d - s][SIZE - d + s] != DOT_0) {
                                map0[j + d - s][SIZE - d + s]++;// увеличиваем значение на 1 если комбинация возможна
                                map0[j + d - s][SIZE - d + s] = map0[j + d - s][SIZE - d + s] + 3 * xCount; // увеличиваем значение на 3x количество Х-ов в комбинации
                                if (map[j + d - s][SIZE - d + s] == DOT_EMPTY) {
                                    mapX[j + d - s][SIZE - d + s] = mapX[j + d - s][SIZE - d + s] + 3 * xCount;
                                } // увеличиваем вес ячейки в mapX как угрозу
                            }
                        }
                    }
                }
            }
        }// нижняя половина левых диагоналей map0

        for (int i = SIZE - DOTS_TO_VIN + 1; i < SIZE; i++) {
            int lineCount = 0;
            int xCount = 0;
            for (int d = 0; d < i; d++) {
                if (map[i - d][1 + d] != DOT_X) {
                    lineCount++;
                    if (map[i - d][1 + d] == DOT_0) {
                        xCount++;
                    } //  счетчик 0-ей в нашей комбинации
                } else {
                    lineCount = 0;
                }
                if (lineCount >= DOTS_TO_VIN) {
                    for (int s = DOTS_TO_VIN - 1; s >= 0; s--) {
                        if (i - d + s >= 1) {
                            if (map[i - d + s][1 + d - s] != DOT_0) {
                                map0[i - d + s][1 + d - s]++;// увеличиваем значение на 1 если комбинация возможна
                                map0[i - d + s][1 + d - s] = map0[i - d + s][1 + d - s] + 3 * xCount; // увеличиваем значение на 3x количество Х-ов в комбинации
                                if (map[i - d + s][1 + d - s] == DOT_EMPTY) {
                                    mapX[i - d + s][1 + d - s] = mapX[i - d + s][1 + d - s] + 3 * xCount;
                                } // увеличиваем вес ячейки в mapX как угрозу
                            }
                        }
                    }
                }
            }
        }// верхняя часть левых диагоналей map0
    }

    // метот определяющий координаты свободной ячейки которая наибольший вес в поле решений для переданного аргумента
    // в качетве аргумента методу надо сообщить для какого символа надо искать решение
    public static void bestTurn(String playerMARK) {
        int playerMap[][];
        int MAX = 0;
        int maxJ = 0;
        int maxI = 0;
        if (playerMARK == DOT_X) {
            playerMap = mapX;
        } else {
            playerMap = map0;
        }

        for (int j = 1; j <= SIZE; j++) {
            for (int i = 1; i <= SIZE; i++) {
                if (MAX <= playerMap[j][i]) {
                    MAX = playerMap[j][i];
                    maxI = i;
                    maxJ = j;
                }
            }
        }
        if (map[maxJ][maxI] == DOT_EMPTY) {
            lastY = maxJ;
            lastX = maxI;
        }
    }

    // метод устанавливает в свободную ячейку марку игрока и спомощью метода checkVIN определяет является ли эта ячейка
    // угрозой, если да, то вес ячейки в обоих массивах возможностей увеличивается на 10. Ячейке устанавливается исходное состояние.
    public static void check2VIN(String MARK) {
        int j, i;
        for (j = 1; j <= SIZE; j++) {
            for (i = 1; i <= SIZE; i++) {
                if (map[j][i] == DOT_EMPTY) {
                    map[j][i] = MARK;
                    if (checkVIN(MARK, j, i)) {
                        mapX[j][i] = mapX[j][i] + 20;
                        map0[j][i] = map0[j][i] + 20;
                    } // если установка знака сразу дает выигрыш, то угроза выше на 20
                    else {
                        int k, l;
                        boolean toVIN;
                        for (k = 1; k <= SIZE; k++) {
                            for (l = 1; l <= SIZE; l++) {
                                if (map[k][l] == DOT_EMPTY) {
                                    toVIN = checkVIN(MARK, k, l);
                                    if (toVIN) {
                                        mapX[j][i] = mapX[j][i] + 5;
                                        map0[j][i] = map0[j][i] + 5;
                                        mapX[k][l] = mapX[k][l] + 5;
                                        map0[k][l] = map0[k][l] + 5;
                                        // тут надо добавку веса ячейкам если они не рядом j-k>1 i-l>1
                                        if (Math.abs(j - k) > 1 && Math.abs(i - l) > 1) {
                                            mapX[j][i] = mapX[j][i] + 10;
                                            map0[j][i] = map0[j][i] + 10;
                                            mapX[k][l] = mapX[k][l] + 10;
                                            map0[k][l] = map0[k][l] + 10;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    map[j][i] = DOT_EMPTY;
                }
            }
        }
    }

    // метод сравнивающий все элементы массивов mapX и map0 и возвращающий false если все = 0.
    public static boolean isVINposiable() {
        possibleVIN_maps();
        boolean vinPosiable = false;
        for (int j=1; j<=SIZE; j++){
            for (int i=1; i<=SIZE; i++){
                if (mapX[j][i]>0){vinPosiable=true;}
                if (map0[j][i]>0){vinPosiable=true;}
            }
        }
       // System.out.println(vinPosiable);
        return vinPosiable;
    }

    // метод рисует окно настроек игры
    public static void OpenSetWindow() {
        JFrame SettingsWindow = new JFrame("Настройки");
        SettingsWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        SettingsWindow.setContentPane(new SetWindow().mainPanel);
        // SetWindow win1 = new SetWindow();
        SettingsWindow.setBounds(100, 100, 420, 200);
        SettingsWindow.setResizable(false);
        SettingsWindow.setVisible(true);
    }







}
