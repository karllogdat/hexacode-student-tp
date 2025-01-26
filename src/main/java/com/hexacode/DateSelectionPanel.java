package com.hexacode;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class DateSelectionPanel extends JPanel {
    private JSpinner dateSpinner;
    private JSpinner timeSpinner;

    public DateSelectionPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Date Picker
        JLabel dateLabel = new JLabel("Select Date:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(dateLabel, gbc);

        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(dateSpinner, gbc);

        // Time Picker
        JLabel timeLabel = new JLabel("Select Time:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(timeLabel, gbc);

        SpinnerDateModel timeModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.MINUTE);
        timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(timeSpinner, gbc);

        // Button to retrieve date and time
        // JButton submitButton = new JButton("Get DateTime");
        // submitButton.addActionListener(_ -> {
        // LocalDateTime selectedDateTime = getSelectedDateTime();
        // JOptionPane.showMessageDialog(this, "Selected DateTime: " +
        // selectedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        // });
        // gbc.gridx = 0;
        // gbc.gridy = 2;
        // gbc.gridwidth = 2;
        // gbc.anchor = GridBagConstraints.CENTER;
        // add(submitButton, gbc);
    }

    public LocalDateTime getSelectedDateTime() {
        Date selectedDate = (Date) dateSpinner.getValue();
        Date selectedTime = (Date) timeSpinner.getValue();

        LocalDateTime dateTime = LocalDateTime.ofInstant(selectedDate.toInstant(), java.time.ZoneId.systemDefault());
        LocalTime time = LocalTime.ofInstant(selectedTime.toInstant(), java.time.ZoneId.systemDefault());
        return dateTime.withHour(time.getHour()).withMinute(time.getMinute());
    }
}
