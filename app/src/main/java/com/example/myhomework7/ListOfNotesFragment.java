package com.example.myhomework7;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListOfNotesFragment extends Fragment implements Constants {

    private Notes note;


    public ListOfNotesFragment() {
    }

    public static ListOfNotesFragment newInstance() {
        return new ListOfNotesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_of_notes, container, false);
        LinearLayout linearLayout = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.noteNamesArray);

        for ( int i = 0; i < notes.length; i++) {
            String name = notes[i];
            int index = i;
            TextView textView = new TextView(requireContext());
            textView.setText(name);
            linearLayout.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showNote(index);
                }
            });
        }

        return view;
    }

    private void showNote(int i) {
        note = new Notes(getResources().getStringArray(R.array.noteNamesArray)[i],
                getResources().getStringArray(R.array.noteBodiesArray)[i],
                getResources().getStringArray(R.array.noteDataArray)[i]);

        if (isLandscape()) {
            showNoteLand();
        } else {
            showNotePort();
        }

    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void showNotePort() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notes_list_container, CurrentNoteFragment.newInstance())
                .addToBackStack(BACK_STACK_TAG)
                .commit();
    }

    private void showNoteLand() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.note_body_container, CurrentNoteFragment.newInstance())
                .commit();
    }
}