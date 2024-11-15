package com.example.feedback4eventosfranlopez;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class NovelasListFragment extends Fragment {

    private RecyclerView recyclerView;
    private NovelaAdapter novelaAdapter;
    private NovelaViewModel novelaViewModel;

    public interface OnNovelaSelectedListener {
        void onNovelaSelected(Novela novela);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_novelas_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        novelaAdapter = new NovelaAdapter(novela -> {
            if (getActivity() instanceof OnNovelaSelectedListener) {
                ((OnNovelaSelectedListener) getActivity()).onNovelaSelected(novela);
            }
        });

        recyclerView.setAdapter(novelaAdapter);

        novelaViewModel = new ViewModelProvider(requireActivity()).get(NovelaViewModel.class);
        novelaViewModel.getAllNovelas().observe(getViewLifecycleOwner(), new Observer<List<Novela>>() {
            @Override
            public void onChanged(List<Novela> novelas) {
                novelaAdapter.setNovelas(novelas);
            }
        });

        return view;
    }
}
