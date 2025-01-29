package com.hexacode;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class for managing entry list. Contains operations for
 * reading and writing entries on user system, sorting entries,
 * insertion and deletion of entries.
 */
public class EntryManager {
    private ArrayList<Entry> entryList;
    private final File entriesFile;

    /**
     * Creates the {@link EntryManager} instance.
     * <p>
     *     Responsible for ensuring entry file existence and
     *     reading entries from the said file.
     * </p>
     */
    EntryManager() {
        entriesFile = new File(
                System.getProperty("user.home") +
                        File.separator +
                        "entries.ser");

        // reference:
        // https://www.w3schools.com/java/java_files_create.asp
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

    /**
     * Reads entries from an entry file
     */
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

    /**
     * Writes(saves) entries to an entry file
     */
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

    /**
     * Exposed method for saving files. Used by other panel instances
     */
    public void saveEntries() {
        writeEntries();
    }

    /**
     * Adds an entry to the entry list
     * @param entry Entry to add
     */
    public void addEntry(Entry entry) {
        entryList.add(entry);
        entryList.sort(null);
        writeEntries();
    }

    /**
     * Deletes and entry from the entry list
     * @param entry Entry to delete
     */
    public void deleteEntry(Entry entry) {
        entryList.remove(entry);
        entryList.sort(null);
        writeEntries();
    }

    /**
     * Gets entry list from the entry manager
     * @return {@link ArrayList<Entry>} entry list
     */
    public ArrayList<Entry> getEntries() {
        return this.entryList;
    }

    /**
     * Gets entry list whose entry deadlines are within a week
     * (7-day difference between now and entry deadline)
     * @return {@link ArrayList<Entry>} entry list
     */
    public ArrayList<Entry> getEntriesWithinWeek() {
        ArrayList<Entry> withinWeek = new ArrayList<>();

        for (Entry entry : entryList) {
            if (entry.isDone()) {
                continue;
            }

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
