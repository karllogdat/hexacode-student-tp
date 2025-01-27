package com.hexacode;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.hexacode.themes.Hexacode;

import java.awt.*;

public class StudentTaskPro extends JFrame {
    StudentTaskPro() {
        super("Student TaskPro");
        Container container = getContentPane();

        container.add(new EntryManagerPanel());

        setSize(400, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        FlatLaf.registerCustomDefaultsSource("com.hexacode.themes");
        Hexacode.setup();
        StudentTaskPro application = new StudentTaskPro();
        application.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
