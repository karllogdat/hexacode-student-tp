package com.hexacode;

import javax.swing.*;

import com.hexacode.Entry.TodoType;

// needs to be refactored 
// prolly add this to EntryManagerUI since it handles new entries
// by user
public class NewEntryPanel extends JPanel {
    private JTextField txfName, txfSubject;
    private JComboBox<TodoType> cbxType;

    private static TodoType[] typeChoices = {
            TodoType.ACTIVITY,
            TodoType.EXAM,
            TodoType.REVIEW
    };

    NewEntryPanel() {
        txfName = new JTextField(30);
        txfSubject = new JTextField(30);

        cbxType = new JComboBox<>(typeChoices);

        add(new JLabel("Entry Name: "));
        add(txfName);
        add(new JLabel("Subject: "));
        add(txfSubject);
        add(new JLabel("Entry Type: "));
        add(cbxType);

        int result = JOptionPane.showConfirmDialog(null, this, "Enter New Entry", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

        }
    }
}
