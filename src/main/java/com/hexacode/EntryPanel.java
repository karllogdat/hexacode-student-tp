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

        // make these like the ones above,
        // assign new JLabels to the declared fields of the class
        add(new JLabel(entry.getName()));
        add(new JLabel(entry.getSubject()));
        add(new JLabel(entry.getType().toString()));
        add(new JLabel(entry.getDeadline().toString()));
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
}
