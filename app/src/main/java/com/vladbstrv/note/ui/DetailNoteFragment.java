package com.vladbstrv.note.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.TextView;
import com.vladbstrv.note.R;
import com.vladbstrv.note.domain.Note;


public class DetailNoteFragment extends Fragment {


    private static final String ARG_PARAM = "ARG_PARAM";

    private Note note;


    public DetailNoteFragment() {
        super(R.layout.fragment_detail_note);
    }


    public static DetailNoteFragment newInstance(Note note) {
        DetailNoteFragment fragment = new DetailNoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_PARAM);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView title = view.findViewById(R.id.detail_title);
        title.setText(note.getTitle());
        TextView description = view.findViewById(R.id.detail_description);
        description.setText(note.getDescription());
    }

}