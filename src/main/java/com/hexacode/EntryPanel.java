package com.hexacode;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class EntryPanel extends JPanel {
    private Entry entry;
    private JLabel lblName, lblSubject, lblType, lblDeadline;
    private JCheckBox cbxDone;

    EntryPanel(Entry entry) {
        this.entry = entry;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);

        setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(Color.LIGHT_GRAY, 1, true),
                        new EmptyBorder(10, 10, 10, 10)));

        lblName = new JLabel(entry.getName());
        lblSubject = new JLabel(entry.getSubject());
        lblType = new JLabel(entry.getType().toString());

        cbxDone = new JCheckBox("Mark as Done");
        cbxDone.setSelected(entry.isDone());

        try {
            lblDeadline = new JLabel(entry.getDeadline().toString());
        } catch (NullPointerException e) {
            System.err.println("Null date found...");
            lblDeadline = new JLabel("NO DATE");
        }

        add(lblName);
        add(lblSubject);
        add(lblType);
        add(lblDeadline);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
}
