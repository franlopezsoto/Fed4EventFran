package com.example.feedback4eventosfranlopez;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NovelaDetailFragment extends Fragment {

    private static final String ARG_NOVELA = "novela";
    private Novela novela;

    public static NovelaDetailFragment newInstance(Novela novela) {
        NovelaDetailFragment fragment = new NovelaDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOVELA, novela);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            novela = getArguments().getParcelable(ARG_NOVELA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_novela_detail, container, false);

        TextView titleTextView = view.findViewById(R.id.textViewTitle);
        TextView authorTextView = view.findViewById(R.id.textViewAuthor);

        if (novela != null) {
            titleTextView.setText(novela.getTitle());
            authorTextView.setText(novela.getAuthor());
        }

        return view;
    }
}
