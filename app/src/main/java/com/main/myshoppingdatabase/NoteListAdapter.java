package com.main.myshoppingdatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NoteListAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private Context context;
    private ArrayList<Note> notes = new ArrayList<>();


    public NoteListAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_main_note_list,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        StringBuilder sb = new StringBuilder();
        sb.append(notes.get(position).getName()+ " " + notes.get(position).getAmount() + " kpl");

        holder.txtNoteView.setText(sb.toString());
        holder.txtNoteEdit.setText(notes.get(position).getName());
        holder.txtNoteAmount.setText(Float.toString(notes.get(position).getAmount()));
      //  notifyItemChanged(position);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(notes.get(i).getName()+ " " + notes.get(i).getAmount() + "" + notes.get(i).getUnit());

        noteViewHolder.txtNoteView.setText(sb.toString());
        noteViewHolder.txtNoteEdit.setText(notes.get(i).getName());

        // This uses lambda clause
        noteViewHolder.imageViewRemove.setOnClickListener(view -> {
            int pos = noteViewHolder.getAdapterPosition();
            NoteStorage.getInstance().removeNote(0);
            notifyItemRemoved(pos);
        });

        noteViewHolder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = noteViewHolder.getAdapterPosition();
                if (noteViewHolder.txtNoteEdit.getVisibility() == View.VISIBLE
                && noteViewHolder.txtNoteAmount.getVisibility() == View.VISIBLE) {

                    Note note = NoteStorage.getInstance().getNotes().get(pos);

                    note.setName(noteViewHolder.txtNoteEdit.getText().toString());
                    note.setAmount(Float.parseFloat(noteViewHolder.txtNoteAmount.getText().toString()));

                    noteViewHolder.txtNoteEdit.setVisibility(View.GONE);
                    noteViewHolder.txtNoteAmount.setVisibility(View.GONE);
                    notifyItemChanged(pos);
                    NoteStorage.getInstance().printNoteData();
                    NoteStorage.getInstance().saveNoteData(context);
                    Toast.makeText(context, context.getFilesDir().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    noteViewHolder.txtNoteEdit.setVisibility(View.VISIBLE);
                    noteViewHolder.txtNoteAmount.setVisibility(View.VISIBLE);
                    Toast.makeText(context, "Muokkaa tuotetta...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
