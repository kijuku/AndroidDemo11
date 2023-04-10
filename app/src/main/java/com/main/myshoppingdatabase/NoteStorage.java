package com.main.myshoppingdatabase;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NoteStorage extends Note implements Serializable {
    private static ArrayList<Note> notelist = new ArrayList<>();
    private static NoteStorage noteStorage = null;

    /**
     *
     */
    private NoteStorage() {
        super();
    }

    /**
     * @return
     */
    public static NoteStorage getInstance() {
        if (noteStorage == null) {
            noteStorage = new NoteStorage();
        }
        return noteStorage;
    }

    /**
     * @return
     */
    public ArrayList<Note> getNotes() {
        return notelist;
    }

    /**
     * @param notes
     */
    public static void setNotes(ArrayList<Note> notes) {
        NoteStorage.notelist = notes;
    }

    /**
     * @param note
     */
    public void addNote(Note note) {
        notelist.add(note);
    }

    public void removeNote(int position) {
        notelist.remove(position);
    }

    //  public int getId(){
    //      return notelist.get();
    //  }

    //*********************************************************************************************
    public void printNoteData() {
        System.out.println("NoteStoragen sisältö:");
        System.out.println("=====================");

        for (Note note : getNotes()) {
            System.out.println(note);
        }
        System.out.println("");
    }

    @Override
    public String toString() {
        return "{" + getNotes() +
                "}";
    }

    //*********************************************************************************************
    public void loadUserData(Context context) {
        try {
            ObjectInputStream dataReader = new ObjectInputStream(context.openFileInput("notedata.dat"));
            notelist = (ArrayList<Note>) dataReader.readObject();
            //userstorage = (UserStorage) dataReader.readObject();

            dataReader.close();
            //UserStorage.getInstance().sortUserData(Values.LASTNAME);
            noteStorage.printNoteData();
            System.out.println("NoteDatan lukeminen onnistui!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //*********************************************************************************************
    public void saveNoteData(Context context) {
        try {
            ObjectOutputStream dataWriter = new ObjectOutputStream(context.openFileOutput("notedata.dat", Context.MODE_PRIVATE));
            dataWriter.writeObject(notelist);
            dataWriter.close();
            System.out.println("NoteDatan tallentaminen onnistui!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("NoteDatan tallentaminen epäonnistui!");
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }


    //*************************************************
    // Lajittelu
    //*************************************************
    class SortByNameDownward implements Comparator<Note> {

        @Override
        public int compare(Note o1, Note o2) {

            return o1.getName().compareTo(o2.getName());
        }

        @Override
        public Comparator<Note> reversed() {
            return Comparator.super.reversed();
        }
    }

    class SortByNameUpward implements Comparator<Note> {

        @Override
        public int compare(Note o1, Note o2) {
            return o2.getName().compareTo(o1.getName());
        }

        @Override
        public Comparator<Note> reversed() {
            return Comparator.super.reversed();
        }
    }

    class SortByDate implements Comparator<Note> {

        @Override
        public int compare(Note o1, Note o2) {
            return o1.getDate().compareTo(o2.getDate());
        }

        @Override
        public Comparator<Note> reversed() {
            return Comparator.super.reversed();
        }
    }

    //*************************************************
    public void sortUserData(Values val) {
        Comparator c;
        switch (val) {
            case UPWARDS:
                c = Collections.reverseOrder(new SortByNameUpward());
                Collections.sort(notelist, Collections.reverseOrder(c));
                break;
            case DOWNWARDS:
                c = Collections.reverseOrder(new SortByNameDownward());
                Collections.sort(notelist, Collections.reverseOrder(c));
                break;
            case DATE:
                c = Collections.reverseOrder(new SortByDate());
                Collections.sort(notelist, Collections.reverseOrder(c));
                break;
            default:
                System.out.println("Oletuslajittelu (Default)..");
                break;
        }
    }
}
