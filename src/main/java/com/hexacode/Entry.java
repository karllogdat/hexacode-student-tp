package com.hexacode;

import com.hexacode.exceptions.IllegalDateException;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Entry implements Serializable, Comparable<Entry> {
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

    public enum TodoType {
        REVIEW,
        ACTIVITY,
        EXAM,
        ASSIGNMENT,
        GROUPWORK,
    }

    private String name, subject;
    private boolean isDone;
    private TodoType type;
    private LocalDateTime deadline;

    Entry(String name, String subject, TodoType type, LocalDateTime deadline) {
        this.setName(name);
        this.setSubject(subject);
        this.setDone(false);
        this.setType(type);
        this.setDeadline(deadline);
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public void setType(TodoType type) {
        this.type = type;
    }

    public void setDeadline(LocalDateTime deadline) throws IllegalDateException {
        if (deadline.isBefore(LocalDateTime.now())) {
            throw new IllegalDateException("Todo Entry deadline is in the past.");
        }

        this.deadline = deadline;
    }

    // getters
    // implement once needed
    public String getName() {
        return this.name;
    }

    public String getSubject() {
        return this.subject;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public TodoType getType() {
        return this.type;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }
}
