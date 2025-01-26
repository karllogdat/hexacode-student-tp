package com.hexacode;

import java.awt.GridLayout;
import java.time.LocalDateTime;

import javax.swing.*;

import com.hexacode.Entry.TodoType;

// needs to be refactored 
// prolly add this to EntryManagerUI since it handles new entries
// by user
public class NewEntryPanel extends JPanel {
    private JTextField txfName, txfSubject;
    private JComboBox<TodoType> cbxType;
    private DateSelectionPanel dspDate;

    private static TodoType[] typeChoices = {
            TodoType.ACTIVITY,
            TodoType.EXAM,
            TodoType.REVIEW
    };

    NewEntryPanel() {
        setLayout(new GridLayout(4, 2, 10, 10));

        txfName = new JTextField(15);
        txfSubject = new JTextField(15);

        cbxType = new JComboBox<>(typeChoices);

        dspDate = new DateSelectionPanel();

        add(new JLabel("Entry Name: "));
        add(txfName);
        add(new JLabel("Subject: "));
        add(txfSubject);
        add(new JLabel("Entry Type: "));
        add(cbxType);
        add(dspDate);
    }

    public String getName() {
        return txfName.getText();
    }

    public String getSubject() {
        return txfSubject.getText();
    }

    public TodoType getTodoType() {
        return (TodoType) cbxType.getSelectedItem();
    }

    public LocalDateTime getDeadline() {
        return dspDate.getSelectedDateTime();
    }
}
