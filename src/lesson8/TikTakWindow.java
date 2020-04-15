package lesson8;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.http.WebSocket;

public class TikTakWindow extends JFrame {
public static BtnX0[][] jbs;

    public TikTakWindow(String map[][]) {
        // создаем слушатель для кнопок игрового поля
        MyListener jbnListener = new MyListener();
        JFrame frame = new JFrame();
            // Создание строки главного меню
            JMenuBar menuBar = new JMenuBar();
                JMenu GameMenu = new JMenu("Игра");
                    // элемент меню Новая Игра - запускает игру сначала.
                    JMenuItem NewGame = new JMenuItem("Новая игра");
                        GameMenu.add(NewGame);
                        NewGame.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                            //TODO тут надо запускать новый игровой цикл
                            }
                        });

                     // элемент меню Настройки - открывает окно с настройками размера поля и длинной выигрышной комбинации.
                    JMenuItem SettingsGame = new JMenuItem("Настроики");
                        GameMenu.add(SettingsGame);
                        SettingsGame.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                            Main.OpenSetWindow();
                            }
                        });

                    // элемент меню для завершения программы.
                    JMenuItem ExitGame = new JMenuItem("Выйти");
                        GameMenu.add(ExitGame);
                        ExitGame.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                            System.exit(1);
                            }
                        });
            menuBar.add(GameMenu);
        frame.setJMenuBar(menuBar);
        setTitle("Крестики - нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, (map.length)*30, (map.length+1)*30);
        frame.setLayout(new GridLayout(map.length,map.length));
        jbs = new BtnX0[map.length][map.length];

        // раскладываем кнопки по координатам массива из 4го урока

        for (int j=0; j< map.length; j++){
            for (int i=0; i<map.length; i++){


                if (j*i ==0){
                    jbs[j][i] = new BtnX0(map[j][i]);
                    jbs[j][i].setEnabled(false); // отключение активности кнопок для начальной столбца и начальной строки
                }else {
                    jbs[j][i] = new BtnX0("  ");
                    jbs[j][i].setRow(j);
                    jbs[j][i].setCol(i);
                    jbs[j][i].addActionListener(jbnListener);
                }
                frame.add(jbs[j][i]);
            }
        }

        frame.setVisible(true);
    }


}
