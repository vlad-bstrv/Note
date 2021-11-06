package com.vladbstrv.note.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.vladbstrv.note.R;
import com.vladbstrv.note.domain.Note;

public class EditNoteBottomSheetDialogFragment extends BottomSheetDialogFragment {

    public static final String TAG = "EditBottomSheetDialogFragment";
    public static final String ARG_KEY = "ARG_KEY";

    public static final String KEY_RESULT = "KEY_RESULT";
    public static final String ARG_TITLE = "ARG_TITLE";
    public static final String ARG_DESCRIPTION = "ARG_DESCRIPTION";

    private Note note;

    public static EditNoteBottomSheetDialogFragment newInstance(Note note) {
        EditNoteBottomSheetDialogFragment fragment = new EditNoteBottomSheetDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_KEY, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_note_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_KEY);
        }

        EditText title = view.findViewById(R.id.title_bottom_sheet);
        title.setText(note.getTitle());

        EditText description = view.findViewById(R.id.description_bottom_sheet);
        description.setText(note.getDescription());

        view.findViewById(R.id.btn_edit_bottom_sheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString(ARG_TITLE, title.getText().toString());
                bundle.putString(ARG_DESCRIPTION, description.getText().toString());

                getParentFragmentManager().setFragmentResult(KEY_RESULT, bundle);
                dismiss();
            }
        });
    }
}

