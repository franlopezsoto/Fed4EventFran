package com.example.feedback4eventosfranlopez;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NovelasListFragment extends Fragment {

    private RecyclerView recyclerView;
    private NovelaAdapter novelaAdapter;
    private NovelaViewModel novelaViewModel;

    // Interfaz para manejar la selección de novelas
    public interface OnNovelaSelectedListener {
        void onNovelaSelected(Novela novela);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_novela_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);

        // Configuración del adaptador
        novelaAdapter = new NovelaAdapter(new NovelaAdapter.OnNovelaClickListener() {
            @Override
            public void onNovelaClick(Novela novela) {
                if (requireActivity() instanceof OnNovelaSelectedListener) {
                    ((OnNovelaSelectedListener) requireActivity()).onNovelaSelected(novela);
                }
            }

            @Override
            public void onNovelaEdit(Novela novela, int position) {
                // Abrir un diálogo para editar los detalles de la novela
                EditNovelaDialogFragment dialog = EditNovelaDialogFragment.newInstance(novela);
                dialog.setOnNovelaUpdatedListener(new EditNovelaDialogFragment.OnNovelaUpdatedListener() {
                    @Override
                    public void onNovelaUpdated(Novela updatedNovela) {
                        // Actualizar la novela en el adaptador y ViewModel
                        novelaAdapter.updateNovela(position, updatedNovela);
                        novelaViewModel.updateNovela(updatedNovela); // Asegúrate de tener un método en el ViewModel
                    }
                });
                dialog.show(getParentFragmentManager(), "EditNovelaDialog");
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
