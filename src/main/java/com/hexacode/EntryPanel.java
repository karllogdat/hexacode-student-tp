package com.hexacode;

import java.awt.*;

import javax.swing.*;

// TODO: add mark as done and entry removal

public class EntryPanel extends JPanel {
    private static final Font CUSTOM_FONT = new Font("Inter", Font.PLAIN, 14);
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
        lblName.setFont(CUSTOM_FONT);
        lblName.setForeground(Color.WHITE);

        lblSubject = new JLabel(entry.getSubject());
        lblSubject.setFont(CUSTOM_FONT);
        lblSubject.setForeground(Color.WHITE);

        lblType = new JLabel(entry.getType().toString());
        lblType.setFont(CUSTOM_FONT);
        lblType.setForeground(Color.WHITE);

        cbxDone = new JCheckBox("Mark as Done");
        cbxDone.setFont(CUSTOM_FONT);
        cbxDone.setSelected(entry.isDone());
        cbxDone.setBackground(new Color(0x38374F));
        cbxDone.setForeground(Color.WHITE);

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
