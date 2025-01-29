package com.hexacode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.naming.InvalidNameException;
import javax.swing.*;

import com.hexacode.Entry.TodoType;
import com.hexacode.exceptions.IllegalDateException;
import com.hexacode.exceptions.InvalidSubjectException;

/**
 * UI class for entry manager.
 * <p>
 *     Manages all UI related methods for the entry manager,
 *     such as rendering entries based on status, etc.
 * </p>
 */
public class EntryManagerPanel extends JPanel implements ActionListener {
    private final EntryManager entryManager;
    private final SidebarPanel sidebarPanel;
    private final JScrollPane scpEntries;
    private final JPanel pnlEntries;

    private final JButton btnNewEntry;

    /**
     * Listener method for adding new entries both on the panel and the entry
     * manager.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        NewEntryPanel newEntry = new NewEntryPanel();

        int result = JOptionPane.showConfirmDialog(null, newEntry, "Create new entry", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Entry entry;

            // catch all invalid cases
            try {
                String name = newEntry.getName();
                String subj = newEntry.getSubject();
                TodoType type = newEntry.getTodoType();
                LocalDateTime deadline = newEntry.getDeadline();

                if (name.equals("")) 
                    throw new InvalidNameException("Empty entry name is not allowed");

                if (subj.equals(""))
                    throw new InvalidSubjectException("Empty subject name is not allowed");

                entry = new Entry(name, subj, type, deadline);
            } catch (IllegalDateException exception) {
                JOptionPane.showMessageDialog(newEntry, "Illegal date time:\nNew entry is in the past", "Illegal Date Exception", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (InvalidNameException exception) {
                JOptionPane.showMessageDialog(newEntry, "Invalid entry name:\nNew entry must be named", "Invalid Name Exception", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (InvalidSubjectException exception) {
                JOptionPane.showMessageDialog(newEntry, "Invalid subject name:\nNew entry must have a subject name", "Invalid Subject Exception", JOptionPane.ERROR_MESSAGE);
                return;
            }

            entryManager.addEntry(entry);

            pnlEntries.add(Box.createRigidArea(new Dimension(0, 10)));
            pnlEntries.add(new EntryPanel(this, entry));

            // update entries in the UI
            this.rerender();
            // update sidebar if an entry is added
            sidebarPanel.update();

            scpEntries.revalidate();
            scpEntries.repaint();
            pnlEntries.revalidate();
            pnlEntries.repaint();
        }
    }

    /**
     * Creates an {@link EntryManagerPanel} instance
     */
    EntryManagerPanel() {
        setLayout(new BorderLayout());

        // add header to panel
        add(new HeaderPanel(), BorderLayout.NORTH);

        // initialize entry manager and sidebar (also acts like a manager)
        entryManager = new EntryManager();
        sidebarPanel = new SidebarPanel(entryManager);

        // initialize panel for entries
        pnlEntries = new JPanel();
        pnlEntries.setLayout(new BoxLayout(pnlEntries, BoxLayout.Y_AXIS));
        pnlEntries.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // make entry list scrollable
        scpEntries = new JScrollPane(pnlEntries);
        scpEntries.setBorder(BorderFactory.createEmptyBorder());
        scpEntries.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // initialize button and listener and mnemonic (keyboard shortcut)
        btnNewEntry = new JButton("Add new entry");
        btnNewEntry.setMnemonic('A');
        btnNewEntry.addActionListener(this);

        // add expected panels on manager
        add(sidebarPanel, BorderLayout.WEST);
        add(scpEntries, BorderLayout.CENTER);
        add(btnNewEntry, BorderLayout.SOUTH);

        // safety rerender method call to render all entries after initializing
        rerender();

        // update UI
        scpEntries.revalidate();
        scpEntries.repaint();
        pnlEntries.revalidate();
        pnlEntries.repaint();

        setPreferredSize(new Dimension(400, 600));
    }

    /**
     * Method for re-rendering all entries in the panel
     */
    public void rerender() {
        // re-rendering also requires updating the sidebar entries
        sidebarPanel.update();
        pnlEntries.removeAll();

        for (Entry todo: entryManager.getEntries()) {
            pnlEntries.add(Box.createRigidArea(new Dimension(0, 10)));
            pnlEntries.add(new EntryPanel(this, todo));
        }

        scpEntries.revalidate();
        scpEntries.repaint();
        pnlEntries.revalidate();
        pnlEntries.repaint();
    }

    /**
     *
     * @param entry
     */
    public void deleteEntry(Entry entry) {
        this.entryManager.deleteEntry(entry);
        rerender();
    }

    public void saveEntries() {
        entryManager.saveEntries();
    }
}
