package com.hexacode;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

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
        FlatLightLaf.setup();

        StudentTaskPro application = new StudentTaskPro();
        application.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
