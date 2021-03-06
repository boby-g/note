package time2note.com.time2note;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities={Note.class},version=1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;
    public abstract NoteDao noteDao();
    public static synchronized  NoteDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return  instance;
    }

    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsyncTask(instance).execute();
        }




    };
    private static class PopulateDatabaseAsyncTask extends  AsyncTask<Void,Void,Void>{
    private NoteDao noteDao;

    private PopulateDatabaseAsyncTask(NoteDatabase db){
        this.noteDao=db.noteDao();
    }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.insert(new Note("Note 1","First note added",false));
            noteDao.insert(new Note("Note 2","First note added",false));
            noteDao.insert(new Note("Note 3","Second note added encripted",true));
            noteDao.insert(new Note("Note 4","Third note added",false));
            return null;
        }
    }
}
