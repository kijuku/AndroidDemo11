package com.main.myshoppingdatabase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private NoteStorage noteStorage;
    private Context context;
    private RecyclerView recyclerView;
    private ImageView imageViewSortByDate, imageViewSortByUpward, imageViewSortByDownward;
    //private ImageView imageViewAdd, imageViewDecrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteStorage = NoteStorage.getInstance();


        //Note n = new Note("Maito",3.0f);
        //noteStorage.addNote(n);
        // Lisätään muutama tuote
        //addDefaultNotes();

        context = MainActivity.this;
        if (noteStorage.getNotes().size() == 0) {
            // Ladataan keskeneräinen lista
            noteStorage.loadUserData(context);
            System.out.println("Ladatiin lista...");
        }

        imageViewSortByDate = findViewById(R.id.imageSortByDate);
        imageViewSortByUpward = findViewById(R.id.imageSortByUpward);
        imageViewSortByDownward = findViewById(R.id.imageSortByDownward);
        System.out.println("MainActivity...");
        noteStorage.printNoteData();

        recyclerView = findViewById(R.id.NoteRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NoteListAdapter(getApplicationContext(), noteStorage.getNotes()));

        imageViewSortByDate.setOnClickListener(view -> {
            NoteStorage.getInstance().sortUserData(Values.DATE);
            noteStorage.printNoteData();

            recyclerView.setAdapter(new NoteListAdapter(getApplicationContext(), noteStorage.getNotes()));
         });

         imageViewSortByUpward.setOnClickListener(view -> {
             NoteStorage.getInstance().sortUserData(Values.UPWARDS);
             noteStorage.printNoteData();
             recyclerView.setAdapter(new NoteListAdapter(getApplicationContext(), noteStorage.getNotes()));
         });

       imageViewSortByDownward.setOnClickListener(view -> {
           NoteStorage.getInstance().sortUserData(Values.DOWNWARDS);
           noteStorage.printNoteData();
           recyclerView.setAdapter(new NoteListAdapter(getApplicationContext(), noteStorage.getNotes()));
        });


    }

    public void addDefaultNotes(){
        Note n = new Note("Maito",3.0f);
        noteStorage.addNote(n);
        n = new Note("Juusto",1.0f);
        noteStorage.addNote(n);
        n = new Note("Jugurtti",6.0f);
        noteStorage.addNote(n);
        n = new Note("Arla Keso",1.0f);
        noteStorage.addNote(n);
        n = new Note("Mämmi (Kymppi)",1.0f);
        noteStorage.addNote(n);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        recyclerView.setAdapter(new NoteListAdapter(getApplicationContext(), noteStorage.getNotes()));
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

    }

    public void switchAddNoteActivity(View view){
        Intent intent = new Intent(this, MainActivityAdd.class );
        startActivity(intent);
    }

    public void saveNoteStoreToFile(View view){
        System.out.println("Painoit tallenna painiketta...");
        noteStorage.saveNoteData(context);
        noteStorage.printNoteData();
        Toast.makeText(context, context.getFilesDir().toString(), Toast.LENGTH_SHORT).show();
    }


}