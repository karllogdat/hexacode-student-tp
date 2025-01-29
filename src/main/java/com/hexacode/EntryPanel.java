package com.hexacode;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.time.format.DateTimeFormatter;

// TODO: add mark as done and entry removal

public class EntryPanel extends JPanel {
    private static final Font CUSTOM_FONT = new Font("Inter", Font.PLAIN, 14);
    private Entry entry;
    private JLabel lblName, lblSubject, lblType, lblDeadline;
    private JCheckBox cbxDone, cbxDelete;

    private EntryManagerPanel entryManagerPanel;

    private JPanel contentPanel, checkboxPanel;

    EntryPanel(EntryManagerPanel entryManagerPanel, Entry entry) {
        this.entry = entry;
        this.entryManagerPanel = entryManagerPanel;
        setupUI();
        setupListeners();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(0x38374F), 1, true),
                        new EmptyBorder(10, 10, 10, 10)
                )
        );
        setBackground(new Color(0x38374F));

        contentPanel = new JPanel();
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

        checkboxPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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

        if (entry.isDone()) {
            cbxDone.setSelected(true);
            markAsDone();
        } else {
            cbxDone.setSelected(false);
            markAsUndone();
        }

        checkboxPanel.add(cbxDone);
        checkboxPanel.add(cbxDelete);

        add(contentPanel, BorderLayout.CENTER);
        add(checkboxPanel, BorderLayout.SOUTH);
    }

    private void markAsDone() {
        entry.setDone(true);

        setBackground(new Color(0x292232));
        contentPanel.setBackground(new Color((0x292232)));
        checkboxPanel.setBackground((new Color(0x292232)));
        cbxDone.setBackground(new Color(0x292232));
        cbxDelete.setBackground(new Color(0x292232));

        setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(0x38374F), 1, true),
                        new EmptyBorder(10, 10, 10, 10)
                )
        );

        lblName.setForeground(Color.LIGHT_GRAY);
        lblSubject.setForeground(Color.LIGHT_GRAY);
        lblType.setForeground(Color.LIGHT_GRAY);
        lblDeadline.setForeground(Color.LIGHT_GRAY);
    }

    private void markAsUndone() {
        entry.setDone(false);

        setBackground(new Color(0x38374F));
        contentPanel.setBackground(new Color(0x38374F));
        checkboxPanel.setBackground(new Color(0x38374F));
        cbxDone.setBackground(new Color(0x38374F));
        cbxDelete.setBackground(new Color(0x38374F));

        lblName.setForeground(Color.WHITE);
        lblSubject.setForeground(Color.WHITE);
        lblType.setForeground(Color.WHITE);
        lblDeadline.setForeground(Color.WHITE);
    }

    private void setupListeners() {
        cbxDone.addActionListener(e -> {
            if (cbxDone.isSelected()) {
                markAsDone();
            } else {
                markAsUndone();
            }
            revalidate();
            repaint();

            entryManagerPanel.rerender();
        });

        cbxDelete.addActionListener(e -> {
            if (cbxDelete.isSelected()) {
//                Container parent = this.getParent();
//                if (parent != null) {
//                    parent.remove(this);
//                    parent.revalidate();
//                    parent.repaint();
//                }
                entryManagerPanel.deleteEntry(entry);
            }
            entryManagerPanel.rerender();
        });
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
}
