package com.hexacode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SidebarPanel extends JPanel {
    private static final Font CUSTOM_FONT = new Font("Inter", Font.PLAIN, 14);
    private ArrayList<Entry> withinWeek;
    private final JPanel pnlWithinWeek;
    private final EntryManager entryManager;

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

    public void setWithinWeek() {
        withinWeek = entryManager.getEntriesWithinWeek();
    }

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

    private static class SideEntryPanel extends JPanel {
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
