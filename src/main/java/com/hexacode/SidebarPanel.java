package com.hexacode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Sidebar panel responsible for UI-related tasks
 * <p>
 * Acts as a Sidebar manager
 */
public class SidebarPanel extends JPanel {
    private static final Font CUSTOM_FONT = new Font("Inter", Font.PLAIN, 14);
    private ArrayList<Entry> withinWeek;
    private final JPanel pnlWithinWeek;
    private final EntryManager entryManager;

    /**
     * Creates a {@link SidebarPanel} instance
     * @param entryManager {@link EntryManagerPanel} Entry manager parent
     */
    SidebarPanel(EntryManager entryManager) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.entryManager = entryManager;
        setWithinWeek();

        JLabel lblWithinWeek = new JLabel("Due this week");
        lblWithinWeek.setFont(CUSTOM_FONT.deriveFont(Font.BOLD));

        pnlWithinWeek = new JPanel();
        pnlWithinWeek.setLayout(new BoxLayout(pnlWithinWeek, BoxLayout.Y_AXIS));
        for (Entry entry : withinWeek) {
            pnlWithinWeek.add(Box.createRigidArea(new Dimension(0, 5)));
            pnlWithinWeek.add(new SideEntryPanel(entry));
        }

        add(lblWithinWeek);
        add(pnlWithinWeek);

        pnlWithinWeek.revalidate();
        pnlWithinWeek.repaint();
        revalidate();
        repaint();

        setPreferredSize(new Dimension(200, 600));
    }

    /**
     * Sets the array list containing the entries whose deadlines
     * are within the week from the related {@link EntryManager}.
     */
    public void setWithinWeek() {
        withinWeek = entryManager.getEntriesWithinWeek();
    }

    /**
     * UI-related rendering update
     */
    public void update() {
        setWithinWeek();
        pnlWithinWeek.removeAll();
        for (Entry entry : withinWeek) {
            pnlWithinWeek.add(Box.createRigidArea(new Dimension(0, 5)));
            pnlWithinWeek.add(new SideEntryPanel(entry));
        }

        pnlWithinWeek.revalidate();
        pnlWithinWeek.repaint();
    }

    /**
     * Panel for individual entries located in the sidebar
     */
    private static class SideEntryPanel extends JPanel {
        /**
         * Creates a {@link SideEntryPanel} instance from the given entry
         * @param entry Entry
         */
        SideEntryPanel(Entry entry) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setAlignmentX(LEFT_ALIGNMENT);

            JLabel lblName = new JLabel(entry.getName());
            add(lblName);

            JLabel lblType = new JLabel(entry.getType().toString());
            lblType.setFont(lblType.getFont().deriveFont(Font.ITALIC));
            add(lblType);

            setPreferredSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
        }
    }
}
