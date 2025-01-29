package com.hexacode;

import javax.swing.*;

import com.formdev.flatlaf.FlatLaf;
import com.hexacode.themes.Hexacode;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Main application class
 */
public class StudentTaskPro extends JFrame {
    StudentTaskPro() {
        super("Student TaskPro");
        Container container = getContentPane();

        // initialize entry manager panel for application
        EntryManagerPanel entryManagerPanel = new EntryManagerPanel();

        container.add(entryManagerPanel);

        // window listener for saving on application close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                entryManagerPanel.saveEntries();
            }
        });

        setSize(800, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        // initialize application theme
        FlatLaf.registerCustomDefaultsSource("themes");
        Hexacode.setup();

        StudentTaskPro application = new StudentTaskPro();
        application.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
