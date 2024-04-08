package com.example.notex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import androidx.recyclerview.widget.RecyclerView;


public class NotesAdapter extends FirebaseRecyclerAdapter<DataModel,NotesAdapter.NoteViewHolder> {



    public NotesAdapter(@NonNull FirebaseRecyclerOptions<DataModel> options) {
        super(options);

    }



    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position,DataModel model) {
        holder.title.setText(model.getTitle());
        holder.notesdata.setText(model.getNotesdata());
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return  new NotesAdapter.NoteViewHolder(view);
    }


    public  class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView notesdata;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.field1TextView); // Adjust IDs as needed
            notesdata = itemView.findViewById(R.id.field2TextView);
        }
    }
}
