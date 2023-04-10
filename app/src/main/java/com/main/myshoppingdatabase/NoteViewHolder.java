package com.main.myshoppingdatabase;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NoteViewHolder extends  RecyclerView.ViewHolder {
    ImageView imageViewEdit, imageViewRemove;
    TextView txtNoteView, txtNoteEdit, txtNoteAmount;

    public NoteViewHolder(View itemView){
        super(itemView);
        imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
        imageViewEdit.setVisibility(View.VISIBLE);

        imageViewRemove = itemView.findViewById(R.id.imageViewRemove);
        imageViewRemove.setVisibility(View.VISIBLE);

        txtNoteView = itemView.findViewById(R.id.txtNoteView);
        txtNoteEdit = itemView.findViewById(R.id.txtNoteEdit);
        txtNoteAmount = itemView.findViewById(R.id.txtNoteAmount);

    }
}
