package com.hexacode;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EntryManager {
    private ArrayList<Entry> entryList;
    private final File entriesFile;

    EntryManager() {
        entriesFile = new File(
                System.getProperty("user.home") +
                        File.separator +
                        "entries.ser");

        // reference:
        // https://www.w3schools.com/java/java_files_create.asp
        // wow such nesting
        try {
            if (!entriesFile.exists()) {
                if (entriesFile.createNewFile()) {
                    System.out.println("Created entries file: " + entriesFile.getAbsolutePath());
                } else {
                    System.out.println("File already exists.");
                }
            } else {
                System.out.println("File exists. Skipping creation.");
            }
        } catch (IOException e) {
            System.err.println("Error creating entries file.");
        }

        readEntries();
    }

    @SuppressWarnings("unchecked")
    private void readEntries() {
        try {
            FileInputStream fileIn = new FileInputStream(entriesFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            if (entriesFile.length() == 0) {
                fileIn.close();
                in.close();
                return;
            }

            entryList = (ArrayList<Entry>) in.readObject();

            fileIn.close();
            in.close();
        } catch (FileNotFoundException e) {
            System.err.println("No entries file found.");
        } catch (IOException e) {
            System.err.println("Error reading from file.");
            System.err.println("Creating new empty list");
            entryList = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }
    }

    private void writeEntries() {
        ArrayList<Entry> newEntryList = new ArrayList<>();

        for (Entry entry : entryList) {
            if (!entry.isDone()) {
                newEntryList.add(entry);
            }
        }

        entryList = newEntryList;

        try {
            FileOutputStream fileOut = new FileOutputStream(entriesFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(entryList);

            fileOut.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.err.println("No file found: " + entriesFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing in file. " + e.getMessage());
        }
    }

    public void saveEntries() {
        writeEntries();
    }

    public void addEntry(Entry entry) {
        entryList.add(entry);
        entryList.sort(null);
        writeEntries();
    }

    public void deleteEntry(Entry entry) {
        entryList.remove(entry);
        entryList.sort(null);
        writeEntries();
    }

    public ArrayList<Entry> getEntries() {
        return this.entryList;
    }

    public ArrayList<Entry> getEntriesWithinWeek() {
        ArrayList<Entry> withinWeek = new ArrayList<>();

        for (Entry entry : entryList) {
            int difference = entry.getDeadline().getDayOfYear() - LocalDateTime.now().getDayOfYear();
            if ( difference <= 7) {
                withinWeek.add(entry);
                System.out.println("Difference: " + difference);
                System.out.println(entry.toString());
            }
        }

        return withinWeek;
    }
}
