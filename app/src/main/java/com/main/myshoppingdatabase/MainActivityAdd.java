package com.main.myshoppingdatabase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivityAdd extends AppCompatActivity {
    private NoteStorage noteStorage;
    private Context context;
    private TextView txtViewNote, txtViewAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Luodaan Note -säiliö
        noteStorage = NoteStorage.getInstance();

        context = MainActivityAdd.this;

        setContentView(R.layout.activity_main_add);
        txtViewNote = findViewById(R.id.textViewNote);
        txtViewAmount = findViewById(R.id.textViewAmount);


        noteStorage.printNoteData();

    }

    public void saveNewNote(View view){
        String noteLine = "", amountLine = "";
        Note note;

        if (!txtViewNote.getText().toString().isEmpty() ) {
            noteLine = txtViewNote.getText().toString();

            if (!txtViewAmount.getText().toString().isEmpty()) {
                amountLine = txtViewAmount.getText().toString();
                note = new Note(noteLine, Float.valueOf(amountLine), new Date());
            } else {
                note = new Note(noteLine, new Date());
            }


            noteStorage.addNote(note);
            noteStorage.printNoteData();
            System.out.println("(MainActivityAdd)Tallennus...");
            NoteStorage.getInstance().saveNoteData(context);
            Toast.makeText(context, context.getFilesDir().toString(), Toast.LENGTH_SHORT).show();
            switchToMainActivity(view);
        } else {
            System.out.println("Jotain meni pieleen!");
        }
    }

    public void switchToMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }
}