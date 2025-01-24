package com.hexacode;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class StudentTaskPro extends JFrame {
    StudentTaskPro() {
        super("Student TaskPro");
        Container container = getContentPane();

        container.add(new EntryPanel(new Entry(
                "name",
                "subject",
                Entry.TodoType.REVIEW,
                LocalDateTime.now()
        )));

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        StudentTaskPro application = new StudentTaskPro();
        application.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}