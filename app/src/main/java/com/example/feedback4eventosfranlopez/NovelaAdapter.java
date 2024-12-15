package com.example.feedback4eventosfranlopez;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NovelaAdapter extends RecyclerView.Adapter<NovelaAdapter.NovelaViewHolder> {

    private List<Novela> novelas = new ArrayList<>();
    private final OnNovelaClickListener listener;

    public interface OnNovelaClickListener {
        void onNovelaClick(Novela novela);
        void onNovelaEdit(Novela novela, int position);
    }

    public NovelaAdapter(OnNovelaClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NovelaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_novela, parent, false);
        return new NovelaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NovelaViewHolder holder, int position) {
        Novela novela = novelas.get(position);
        holder.titleTextView.setText(novela.getTitle());
        holder.authorTextView.setText(novela.getAuthor());

        holder.itemView.setOnClickListener(v -> listener.onNovelaClick(novela));
        holder.itemView.setOnLongClickListener(v -> {
            listener.onNovelaEdit(novela, position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return novelas.size();
    }

    public void setNovelas(List<Novela> novelas) {
        this.novelas = novelas;
        notifyDataSetChanged();
    }

    public void updateNovela(int position, String title, String author, double latitude, double longitude) {
        if (position >= 0 && position < novelas.size()) {
            Novela novela = novelas.get(position);
            novela.setTitle(title);
            novela.setAuthor(author);
            novela.setLatitude(latitude);
            novela.setLongitude(longitude);
            notifyItemChanged(position);
        }
    }

    static class NovelaViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView authorTextView;

        public NovelaViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textTitle);
            authorTextView = itemView.findViewById(R.id.textAuthor);
        }
    }
}