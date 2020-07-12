package com.erp.distribution.sfa.master.customer2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.erp.distribution.R;
import com.erp.distribution.sfa.model.FCustomer;

public class NoteAdapter extends ListAdapter<FCustomer, NoteAdapter.NoteHolder> {
    private OnItemClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<FCustomer> DIFF_CALLBACK = new DiffUtil.ItemCallback<FCustomer>() {
        @Override
        public boolean areItemsTheSame(FCustomer oldItem, FCustomer newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(FCustomer oldItem, FCustomer newItem) {
            return oldItem.getCustno().equals(newItem.getCustno()) &&
                    oldItem.getCustname().equals(newItem.getCustname());
        }
    };


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        FCustomer currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getCustname());
        holder.textViewPriority.setText(String.valueOf(currentNote.getCustno()));
    }
//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }
//    public void setNotes(List<Note> notes) {
//        this.notes = notes;
//        notifyDataSetChanged();
//    }

    public FCustomer getNoteAt(int position) {
//        return notes.get(position);
        return getItem(position);
    }
    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;

        private TextView textViewPriority;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
//            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
//                        listener.onItemClick(notes.get(position));
                        listener.onItemClick(getItem((position)));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(FCustomer note);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}