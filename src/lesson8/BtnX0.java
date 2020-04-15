package lesson8;

import javax.swing.*;
// Это расширение для класса обычной кнопки JButton
// кнопка принимает две переменные соответствующие координатам на игровом поле
// и хранит их для передачи листенеру при нажатии.

public class BtnX0 extends JButton {
    private int row;
    private int col;

    public BtnX0(String s) {
        super(s);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
