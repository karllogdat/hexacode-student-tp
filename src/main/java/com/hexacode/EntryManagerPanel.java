package com.hexacode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EntryManagerPanel extends JPanel implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("wow such add");

        NewEntryPanel newEntry = new NewEntryPanel();

        int result = JOptionPane.showConfirmDialog(null, newEntry, "Create new entry", JOptionPane.PLAIN_MESSAGE);

        // TODO: handle invalid date usign try catch
        if (result == JOptionPane.OK_OPTION) {
            // get field values
            Entry entry = new Entry(newEntry.getName(), newEntry.getSubject(), newEntry.getTodoType(),
                    newEntry.getDeadline());

            entryManager.addEntry(entry);

            pnlEntries.add(Box.createRigidArea(new Dimension(0, 10)));
            pnlEntries.add(new EntryPanel(entry));

            System.out.println("new entry added");

            scpEntries.revalidate();
            scpEntries.repaint();
            pnlEntries.revalidate();
            pnlEntries.repaint();
        }
    }

    // contains all the entries
    private EntryManager entryManager;
    private JScrollPane scpEntries;
    private JPanel pnlEntries;

    private JButton btnNewEntry;

    EntryManagerPanel() {
        setLayout(new BorderLayout());

        entryManager = new EntryManager();

        pnlEntries = new JPanel();
        pnlEntries.setLayout(new BoxLayout(pnlEntries, BoxLayout.Y_AXIS));

        scpEntries = new JScrollPane(pnlEntries);
        scpEntries.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btnNewEntry = new JButton("Add new entry");
        btnNewEntry.addActionListener(this);

        add(scpEntries, BorderLayout.CENTER);
        add(btnNewEntry, BorderLayout.SOUTH);

        // loads all saved entries into manager panel
        for (Entry todo : entryManager.getEntries()) {
            pnlEntries.add(Box.createRigidArea(new Dimension(0, 10)));
            pnlEntries.add(new EntryPanel(todo));
        }

        scpEntries.revalidate();
        scpEntries.repaint();
        pnlEntries.revalidate();
        pnlEntries.repaint();

        setPreferredSize(new Dimension(400, 600));
    }
}
