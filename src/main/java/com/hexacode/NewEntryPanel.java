package com.hexacode;

import java.awt.*;
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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel pnlInputs = new JPanel(new GridLayout(3, 2, 10, 5));
        txfName = new JTextField(15);
        txfSubject = new JTextField(15);

        cbxType = new JComboBox<>(typeChoices);

        dspDate = new DateSelectionPanel();

        pnlInputs.add(new JLabel("Entry Name: "));
        pnlInputs.add(txfName);
        pnlInputs.add(new JLabel("Subject: "));
        pnlInputs.add(txfSubject);
        pnlInputs.add(new JLabel("Entry Type: "));
        pnlInputs.add(cbxType);
        add(pnlInputs);
        add(Box.createRigidArea(new Dimension(0, 5)));
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
