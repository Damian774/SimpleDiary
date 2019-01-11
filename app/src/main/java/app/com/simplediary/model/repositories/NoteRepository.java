package app.com.simplediary.model.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

import app.com.simplediary.model.AppDatabase;
import app.com.simplediary.model.Note;
import app.com.simplediary.model.NoteDao;


public class NoteRepository {

    private NoteDao noteDao;

    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNotes();
    }


    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public Note getNoteById(int noteId) {
        return noteDao.getNoteById(noteId);
    }

    public void insert(Note note) {
        noteDao.insert(note);
    }

    public void update(Note note) {
        noteDao.update(note.getId(),note.getNote());


    }

    public void deleteNote(Note note) {
        noteDao.deleteNote(note);
    }

}
