package com.hexacode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * Panel for user input in date and time selection
 */
public class DateSelectionPanel extends JPanel {
    private final JSpinner dateSpinner;
    private final JSpinner timeSpinner;

    // font used for most of the application text
    private static final Font CUSTOM_FONT = new Font("Inter", Font.PLAIN, 14);

    /**
     * Creates a {@link DateSelectionPanel} instance.
     * <p>
     * Only used in {@link NewEntryPanel}
     */
    public DateSelectionPanel() {
        setLayout(new GridLayout(2, 2, 10, 5));

        // Date Picker
        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setFont(CUSTOM_FONT);
        add(dateLabel);

        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateEditor.getTextField().setFont(CUSTOM_FONT);
        dateSpinner.setEditor(dateEditor);
        add(dateSpinner);

        // Time Picker
        JLabel timeLabel = new JLabel("Select Time:");
        timeLabel.setFont(CUSTOM_FONT);
        add(timeLabel);

        SpinnerDateModel timeModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.MINUTE);
        timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeEditor.getTextField().setFont(CUSTOM_FONT);
        timeSpinner.setEditor(timeEditor);
        add(timeSpinner);
    }


    /**
     * Gets the {@link LocalDateTime} selected in both spinners.
     *
     * @return {@link LocalDateTime} spinner values
     */
    public LocalDateTime getSelectedDateTime() {
        Date selectedDate = (Date) dateSpinner.getValue();
        Date selectedTime = (Date) timeSpinner.getValue();

        LocalDateTime dateTime = LocalDateTime.ofInstant(selectedDate.toInstant(), java.time.ZoneId.systemDefault());
        LocalTime time = LocalTime.ofInstant(selectedTime.toInstant(), java.time.ZoneId.systemDefault());
        return dateTime.withHour(time.getHour()).withMinute(time.getMinute());
    }

    // required for the panel to grow in size for layout purposes
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
}
