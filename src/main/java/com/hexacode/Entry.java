package com.hexacode;

import com.hexacode.exceptions.IllegalDateException;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Entry implements Serializable, Comparable<Entry> {
    private String name, subject;
    private boolean isDone;
    private TodoType type;
    private LocalDateTime deadline;

    // required to compare Entry objects for sorting purposes
    @Override
    public int compareTo(Entry o) {
        if (this.deadline == null && o.deadline == null) {
            return 0;
        } else if (this.deadline == null) {
            return -1;
        } else if (o.deadline == null) {
            return 1;
        } else {
            return this.deadline.compareTo(o.deadline);
        }
    }

    // enum for identifying entry types

    /**
     * TodoType identifiers.
     */
    public enum TodoType {
        REVIEW,
        ACTIVITY,
        EXAM,
        ASSIGNMENT,
        GROUPWORK,
    }

    // Constructor for entries, no empty parameter constructor since we want
    // our entries to always be valid (no empty fields)
    /**
     * Creates an entry object instance.
     * @param name Entry name
     * @param subject Entry subject
     * @param type Entry type. Refer to {@link com.hexacode.Entry.TodoType}.
     * @param deadline Entry deadline. Deadline must be after the current time.
     */
    Entry(String name, String subject, TodoType type, LocalDateTime deadline) {
        this.setName(name);
        this.setSubject(subject);
        this.setDone(false);
        this.setType(type);
        this.setDeadline(deadline);
    }

    // setters
    /**
     * Sets entry name
     * @param name entry name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets entry subject
     * @param subject entry subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Sets whether the entry is marked as done or not
     * @param done true when entry is done, false otherwise
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Sets entry type
     * @param type Refer to {@link TodoType}
     */
    public void setType(TodoType type) {
        this.type = type;
    }

    /**
     * Entry deadline setter. A valid deadline should be after the current
     * date and time.
     * @param deadline LocalDateTime type date.
     * @throws IllegalDateException Throws when an invalid date is passed
     * as an argument
     */
    public void setDeadline(LocalDateTime deadline) throws IllegalDateException {
        if (deadline.isBefore(LocalDateTime.now())) {
            throw new IllegalDateException("Todo Entry deadline is in the past.");
        }

        this.deadline = deadline;
    }

    // getters

    /**
     * Gets entry name
     * @return entry name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets entry subject
     * @return entry subject
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Gets entry done status
     * @return true if entry is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets entry type
     * @return entry type, refer to {@link TodoType}
     */
    public TodoType getType() {
        return this.type;
    }

    /**
     * Gets entry deadline
     * @return deadline as {@link LocalDateTime}
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }
}
