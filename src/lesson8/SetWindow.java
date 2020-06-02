package lesson8;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetWindow {
    public JPanel mainPanel;
    private JButton btnOK;
    private JButton btnCancel;
    private JSpinner размерИгровогоПоляSpinner;
    private JSpinner длиннаВыигрышнойКомбинацииSpinner;

    public SetWindow() {
        размерИгровогоПоляSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
