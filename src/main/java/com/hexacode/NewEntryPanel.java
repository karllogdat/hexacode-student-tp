package com.hexacode;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

import javax.swing.*;

import com.hexacode.Entry.TodoType;

/**
 * Panel UI for adding new entries (shown as a dialog box)
 */
public class NewEntryPanel extends JPanel {
    private final JTextField txfName;
    private final JTextField txfSubject;
    private final JComboBox<TodoType> cbxType;
    private final DateSelectionPanel dspDate;

    private static final Font CUSTOM_FONT = new Font("Inter", Font.PLAIN, 14);
    private static final TodoType[] typeChoices = {
            TodoType.ACTIVITY,
            TodoType.EXAM,
            TodoType.REVIEW,
            TodoType.ASSIGNMENT,
            TodoType.GROUPWORK,
    };

    /**
     * Creates a {@link NewEntryPanel} instance
     */
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

        JLabel lblName = new JLabel("Name: ");
        lblName.setFont(CUSTOM_FONT);
        lblName.setDisplayedMnemonic(KeyEvent.VK_N);
        lblName.setLabelFor(txfName);
        pnlInputs.add(lblName);
        pnlInputs.add(txfName);

        JLabel lblSubject = new JLabel("Subject: ");
        lblSubject.setFont(CUSTOM_FONT);
        lblSubject.setDisplayedMnemonic(KeyEvent.VK_S);
        lblSubject.setLabelFor(txfSubject);
        pnlInputs.add(lblSubject);
        pnlInputs.add(txfSubject);

        JLabel lblType = new JLabel("Type: ");
        lblType.setFont(CUSTOM_FONT);
        lblType.setDisplayedMnemonic(KeyEvent.VK_T);
        lblType.setLabelFor(cbxType);
        pnlInputs.add(lblType);
        pnlInputs.add(cbxType);

        add(pnlInputs);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(dspDate);
    }

    /**
     * Gets string from name {@link JTextField}
     * @return Name
     */
    public String getName() {
        return txfName.getText();
    }

    /**
     * Gets string from subject {@link JTextField}
     * @return Subject
     */
    public String getSubject() {
        return txfSubject.getText();
    }

    /**
     * Gets {@link TodoType} from type {@link JComboBox}
     * @return {@link TodoType} type
     */
    public TodoType getTodoType() {
        return (TodoType) cbxType.getSelectedItem();
    }

    /**
     * Gets {@link LocalDateTime} from date {@link DateSelectionPanel}
     * @return {@link LocalDateTime} Date
     */
    public LocalDateTime getDeadline() {
        return dspDate.getSelectedDateTime();
    }
}
