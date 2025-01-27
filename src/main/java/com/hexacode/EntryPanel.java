package com.hexacode;

import java.awt.*;

import javax.swing.*;

// TODO: add mark as done and entry removal
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
                BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0x38374F));

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
