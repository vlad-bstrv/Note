package com.vladbstrv.note.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vladbstrv.note.R;
import com.vladbstrv.note.domain.Note;
import com.vladbstrv.note.domain.NoteSource;
import com.vladbstrv.note.domain.NoteSourceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ListNoteFragment extends Fragment {

    private RecyclerView notesListRecyclerView;
    private ListNoteAdapter adapter;

    private NoteSource data;

    private int selectedPosition;


    public ListNoteFragment() {
        super(R.layout.fragment_list_note);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new NoteSourceImpl();
        adapter = new ListNoteAdapter(data.getList(), this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarOnFragment(view);

        notesListRecyclerView = view.findViewById(R.id.recycler_view_list_note);
        initRecyclerView();

        getParentFragmentManager().setFragmentResultListener(EditNoteBottomSheetDialogFragment.KEY_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString(EditNoteBottomSheetDialogFragment.ARG_TITLE);
                String description = result.getString(EditNoteBottomSheetDialogFragment.ARG_DESCRIPTION);
                data.updateNote(title, description, selectedPosition);
                adapter.notifyItemChanged(selectedPosition);
            }
        });
    }

    private void initRecyclerView() {

        notesListRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        notesListRecyclerView.setLayoutManager(layoutManager);

        notesListRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ListNoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DetailNoteFragment detailNoteFragment = DetailNoteFragment.newInstance(data.getNote(position));
                getParentFragmentManager().beginTransaction()
                        .addToBackStack("")
                        .replace(R.id.container, detailNoteFragment)
                        .commit();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                view.showContextMenu();
                selectedPosition = position;
            }
        });
    }

    private void setToolbarOnFragment(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar_list_fragment);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_add) {
                    AddNoteBottomSheetDialogFragment dialogFragment = new AddNoteBottomSheetDialogFragment();
                    dialogFragment.show(getParentFragmentManager(), AddNoteBottomSheetDialogFragment.TAG);

                    dialogFragment.SetOnClickListener(new AddNoteBottomSheetDialogFragment.OnBottomSheetDialogListener() {
                        @Override
                        public void onDialogListener(String title, String description) {
                            data.addNote(new Note(UUID.randomUUID().toString(), title, description));
                            adapter.notifyItemInserted(data.size() - 1);
                            notesListRecyclerView.scrollToPosition(data.size() - 1);
                        }
                    });

                    return true;
                }

                if (item.getItemId() == R.id.action_clear) {
                    data.clearNote();
                    adapter.notifyDataSetChanged();
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        requireActivity().getMenuInflater().inflate(R.menu.menu_note_list_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {

            data.deleteNote(selectedPosition);
            adapter.notifyItemRemoved(selectedPosition);

            return true;
        }

        if (item.getItemId() == R.id.action_edit) {
            EditNoteBottomSheetDialogFragment.newInstance(data.getNote(selectedPosition))
                    .show(getParentFragmentManager(), EditNoteBottomSheetDialogFragment.TAG);
            return true;
        }
        return super.onContextItemSelected(item);
    }
}