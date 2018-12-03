package time2note.com.time2note;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "simply_notes")
public class Note {
    @NonNull
    @PrimaryKey
    private String id;
    private String title;
    private String note;
    private boolean crypted;

    @ColumnInfo(name = "create_date")
    @TypeConverters({TimestampConverter.class})
    private Date created;

    public Note(String title, String note, boolean crypted) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.note = note;
        this.crypted = crypted;
        this.created = new Date();
    }


    @NonNull
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isCrypted() {
        return crypted;
    }

    public void setCrypted(boolean crypted) {
        this.crypted = crypted;
    }


    public Date getCreated() {
        return created;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreated(Date created) {
        this.created = new Date();
    }
}
