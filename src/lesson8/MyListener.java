// это универсальный слушатель для игровых кнопок
// слушатель возвращает координаты нажатой кнопки в виде хода человека.
// сохраняя их значения в lastY и lastX

package lesson8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        BtnX0 pressedBtn = (BtnX0) e.getSource();
        int i = pressedBtn.getCol();
        int j = pressedBtn.getRow();

        if (Main.map[j][i]==Main.DOT_EMPTY){
            Main.lastY = j;
            Main.lastX = i;
            Main.map[j][i] = Main.userMARK;
            pressedBtn.setText(Main.map[j][i]);
            pressedBtn.setEnabled(false);
            Main.userHasTurn = true;
        }
    }
}
