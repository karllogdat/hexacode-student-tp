package com.hexacode;

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
    }
}
