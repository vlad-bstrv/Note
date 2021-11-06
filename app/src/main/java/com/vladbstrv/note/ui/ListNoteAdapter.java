package com.vladbstrv.note.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.vladbstrv.note.R;
import com.vladbstrv.note.domain.Note;

import java.util.List;

public class ListNoteAdapter extends RecyclerView.Adapter<ListNoteAdapter.ListNoteViewHolder> {

    private List<Note> dataSource;
    private OnItemClickListener itemClickListener;
    private Fragment fragment;

    public ListNoteAdapter(List<Note> dataSource, Fragment fragment) {
        this.dataSource = dataSource;
        this.fragment = fragment;
    }


    @NonNull
    @Override
    public ListNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ListNoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNoteViewHolder holder, int position) {
        Note note = dataSource.get(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemLongClick(View view, int position);
    }

    public class ListNoteViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;


        public ListNoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            description = itemView.findViewById(R.id.item_description);

            fragment.registerForContextMenu(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemLongClick(v, getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }
}
