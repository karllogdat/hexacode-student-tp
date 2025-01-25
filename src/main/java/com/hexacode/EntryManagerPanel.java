package com.hexacode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.*;

import com.hexacode.Entry.TodoType;

public class EntryManagerPanel extends JPanel implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("wow such add");

        Entry entry = new Entry("Unnamed Entry", "Unnamed Subject", TodoType.ACTIVITY,
                LocalDateTime.of(2025, 12, 30, 0, 0, 0));

        entryManager.addEntry(entry);

        for (Entry todo : entryManager.getEntries()) {
            pnlEntries.add(new EntryPanel(todo));
        }

        pnlEntries.revalidate();
        pnlEntries.repaint();
    }

    // contains all the entries
    private EntryManager entryManager;
    private JScrollPane scpEntries;
    private JPanel pnlEntries;

    private JButton btnNewEntry;

    EntryManagerPanel() {
        entryManager = new EntryManager();

        pnlEntries = new JPanel();
        pnlEntries.setLayout(new BoxLayout(pnlEntries, BoxLayout.Y_AXIS));

        scpEntries = new JScrollPane();
        scpEntries.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scpEntries.setViewportView(pnlEntries);

        btnNewEntry = new JButton("Add new entry");
        btnNewEntry.addActionListener(this);

        add(pnlEntries);
        add(btnNewEntry);
    }
}
