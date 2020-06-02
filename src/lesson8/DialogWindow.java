package lesson8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JPanel buttonPanel;
    private JPanel messagePanel;
    private JLabel messageText;


    public void setTextField1(String message) {
        this.messageText.setText(message);
    }

    public DialogWindow(String message) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        messageText.setText(message);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void main(String[] args) {
        DialogWindow dialog = new DialogWindow("Текст");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
