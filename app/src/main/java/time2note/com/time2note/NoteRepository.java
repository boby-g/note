package time2note.com.time2note;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    public NoteRepository(Application application){
        NoteDatabase noteDatabase= NoteDatabase.getInstance(application);
        noteDao=noteDatabase.noteDao();
        allNotes=noteDao.getAlNotesl();
    }

    public  void insert(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }
    public  void update(Note note){
        new UpdatetNoteAsyncTask(noteDao).execute(note);
    }
    public  void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public  void deleteAllNotes(){
          new DeleteAllNotesAsyncTask(noteDao).execute();
    }
    public  LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    public static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private  NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return  null;
        }
    }
    public static class UpdatetNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private  NoteDao noteDao;

        private UpdatetNoteAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return  null;
        }
    }
    public static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        private  NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return  null;
        }
    }
    public static class DeleteAllNotesAsyncTask extends AsyncTask<Void,Void,Void>{
        private  NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return  null;
        }
    }
}