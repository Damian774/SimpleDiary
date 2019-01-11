package app.com.simplediary.views;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.com.simplediary.R;
import app.com.simplediary.model.Note;
import app.com.simplediary.viewmodel.ApplicationViewModel;


/**
 * A fragment representing a single Note detail screen.
 * This fragment is either contained in a {@link ShowNotesActivity}
 * in two-pane mode (on tablets) or a {@link NoteDetailActivity}
 * on handsets.
 */
public class NoteDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    ApplicationViewModel applicationViewModel;
    public static final String ARG_ITEM_ID = "item_id";
    int noteId;
    /**
     * The dummy content this fragment is presenting.
     */
    private Note note;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(getString(R.string.grade_details_bar_title));
            }
        }
    }


    private void loadData(){
        applicationViewModel = ViewModelProviders.of(getActivity()).get(ApplicationViewModel.class);
        noteId = getArguments().getInt(ARG_ITEM_ID);
        note = applicationViewModel.getNoteById(noteId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.note_detail, container, false);
        // Show the dummy content as text in a TextView.
        loadData();
        if (note != null) {
            applicationViewModel.setPickedNote(note);
            String detailInfo = note.getNote();
            ((TextView) rootView.findViewById(R.id.grade_detail)).setText(detailInfo);
        }

        return rootView;
    }
}
