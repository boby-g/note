package time2note.com.time2note;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes=new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_layout,parent,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote=notes.get(position);
        if(currentNote.isCrypted()) {
            holder.title.setText(currentNote.getTitle());
            holder.title.setVisibility(View.VISIBLE);
            holder.description.setVisibility(View.GONE);
        } else {
            holder.title.setVisibility(View.GONE);
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(currentNote.getNote());
        }
        holder.createDate.setText(TimestampConverter.dateToTimestamp(currentNote.getCreated()));
        if(currentNote.isCrypted()) {
            holder.encrypted.setVisibility(View.VISIBLE);
        }else{
            holder.encrypted.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setItems(List<Note> notes) {
        this.notes=notes;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView description;
        private TextView createDate;
        private TextView encrypted;

        public NoteHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            createDate=itemView.findViewById(R.id.date);
            encrypted=itemView.findViewById(R.id.encript);
        }
    }
}
