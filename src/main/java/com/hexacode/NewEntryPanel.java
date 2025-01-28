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

    private static final Font CUSTOM_FONT = new Font("Inter", Font.PLAIN, 14);
    private static final TodoType[] typeChoices = {
            TodoType.ACTIVITY,
            TodoType.EXAM,
            TodoType.REVIEW,
            TodoType.ASSIGNMENT,
            TodoType.GROUPWORK,
    };

    NewEntryPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        JPanel pnlInputs = new JPanel(new GridLayout(3, 2, 10, 5));
        txfName = new JTextField(15);
        txfSubject = new JTextField(15);
        txfSubject.setFont(CUSTOM_FONT);
        txfName.setFont(CUSTOM_FONT);

        cbxType = new JComboBox<>(typeChoices);
        cbxType.setFont(CUSTOM_FONT);

        dspDate = new DateSelectionPanel();
        dspDate.setFont(CUSTOM_FONT);

        JLabel lblName = new JLabel("Entry Name: ");
        lblName.setFont(CUSTOM_FONT);
        pnlInputs.add(lblName);
        pnlInputs.add(txfName);

        JLabel lblSubject = new JLabel("Subject: ");
        lblSubject.setFont(CUSTOM_FONT);
        pnlInputs.add(lblSubject);
        pnlInputs.add(txfSubject);

        JLabel lblType = new JLabel("Entry Type: ");
        lblType.setFont(CUSTOM_FONT);
        pnlInputs.add(lblType);
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
