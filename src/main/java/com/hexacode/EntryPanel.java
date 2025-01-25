package com.hexacode;

import java.awt.*;

import javax.swing.*;

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

        cbxDone = new JCheckBox("Mark as Done");
        cbxDone.setSelected(entry.isDone());

        try {
            lblDeadline = new JLabel(entry.getDeadline().toString());
        } catch (NullPointerException e) {
            System.err.println("Null date found...");
            lblDeadline = new JLabel("NO DATE");
        }

        // make these like the ones above,
        // assign new JLabels to the declared fields of the class
        add(new JLabel(entry.getName()));
        add(new JLabel(entry.getSubject()));
        add(new JLabel(entry.getType().toString()));
        add(lblDeadline);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
}
