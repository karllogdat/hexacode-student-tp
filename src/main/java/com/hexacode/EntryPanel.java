package com.hexacode;

import java.awt.*;
import javax.swing.*;
import java.time.format.DateTimeFormatter;

// TODO: add mark as done and entry removal

public class EntryPanel extends JPanel {
    private static final Font CUSTOM_FONT = new Font("Inter", Font.PLAIN, 14);
    private Entry entry;
    private JLabel lblName, lblSubject, lblType, lblDeadline;
    private JCheckBox cbxDone, cbxDelete;

    EntryPanel(Entry entry) {
        this.entry = entry;
        setupUI();
        setupListeners();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0x38374F));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(0x38374F));

        lblName = new JLabel(entry.getName());
        lblName.setFont(CUSTOM_FONT);
        lblName.setForeground(Color.WHITE);

        lblSubject = new JLabel(entry.getSubject());
        lblSubject.setFont(CUSTOM_FONT);
        lblSubject.setForeground(Color.WHITE);

        lblType = new JLabel(entry.getType().toString());
        lblType.setFont(CUSTOM_FONT);
        lblType.setForeground(Color.WHITE);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            lblDeadline = new JLabel(entry.getDeadline().format(formatter)); // Use the formatter
        } catch (NullPointerException e) {
            System.err.println("Null date found...");
            lblDeadline = new JLabel("NO DATE");
        }
        lblDeadline.setFont(CUSTOM_FONT);
        lblDeadline.setForeground(Color.WHITE);

        contentPanel.add(lblName);
        contentPanel.add(lblSubject);
        contentPanel.add(lblType);
        contentPanel.add(lblDeadline);

        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        checkboxPanel.setBackground(new Color(0x38374F));

        cbxDone = new JCheckBox("Done");
        cbxDone.setFont(CUSTOM_FONT);
        cbxDone.setSelected(entry.isDone());
        cbxDone.setForeground(Color.WHITE);
        cbxDone.setBackground(new Color(0x38374F));

        cbxDelete = new JCheckBox("Delete");
        cbxDelete.setFont(CUSTOM_FONT);
        cbxDelete.setForeground(Color.WHITE);
        cbxDelete.setBackground(new Color(0x38374F));

        checkboxPanel.add(cbxDone);
        checkboxPanel.add(cbxDelete);

        add(contentPanel, BorderLayout.CENTER);
        add(checkboxPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        cbxDone.addActionListener(e -> {
            if (cbxDone.isSelected()) {
                setBackground(new Color(0x6F6F6F));
                lblName.setForeground(Color.LIGHT_GRAY);
                lblSubject.setForeground(Color.LIGHT_GRAY);
                lblType.setForeground(Color.LIGHT_GRAY);
                lblDeadline.setForeground(Color.LIGHT_GRAY);
            } else {
                setBackground(new Color(0x38374F));
                lblName.setForeground(Color.WHITE);
                lblSubject.setForeground(Color.WHITE);
                lblType.setForeground(Color.WHITE);
                lblDeadline.setForeground(Color.WHITE);
            }
            revalidate();
            repaint();
        });

        cbxDelete.addActionListener(e -> {
            if (cbxDelete.isSelected()) {
                Container parent = this.getParent();
                if (parent != null) {
                    parent.remove(this);
                    parent.revalidate();
                    parent.repaint();
                }
            }
        });
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
}
