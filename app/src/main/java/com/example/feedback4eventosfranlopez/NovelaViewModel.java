package com.example.feedback4eventosfranlopez;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class NovelaViewModel extends ViewModel {

    private final MutableLiveData<List<Novela>> novelas;

    public NovelaViewModel() {
        novelas = new MutableLiveData<>();
        loadNovelas(); // Carga inicial de datos
    }

    // Método para obtener todas las novelas
    public LiveData<List<Novela>> getAllNovelas() {
        return novelas;
    }

    // Método para agregar una novela
    public void addNovela(Novela novela) {
        if (novelas.getValue() != null) {
            List<Novela> currentNovelas = new ArrayList<>(novelas.getValue());
            currentNovelas.add(novela);
            novelas.setValue(currentNovelas);
        } else {
            List<Novela> newNovelas = new ArrayList<>();
            newNovelas.add(novela);
            novelas.setValue(newNovelas);
        }
    }

    // Método para eliminar una novela
    public void removeNovela(Novela novela) {
        if (novelas.getValue() != null) {
            List<Novela> currentNovelas = new ArrayList<>(novelas.getValue());
            currentNovelas.remove(novela);
            novelas.setValue(currentNovelas);
        }
    }

    // Método para marcar o desmarcar como favorita
    public void toggleFavorite(Novela novela) {
        if (novelas.getValue() != null) {
            List<Novela> currentNovelas = new ArrayList<>(novelas.getValue());
            int index = currentNovelas.indexOf(novela);
            if (index != -1) {
                Novela updatedNovela = currentNovelas.get(index);
                updatedNovela.setFavorite(!updatedNovela.isFavorite());
                novelas.setValue(currentNovelas);
            }
        }
    }

    // Simula la carga inicial de datos
    private void loadNovelas() {
        List<Novela> initialNovelas = new ArrayList<>();
        initialNovelas.add(new Novela("Cien años de soledad", "Gabriel García Márquez", false));
        initialNovelas.add(new Novela("Don Quijote de la Mancha", "Miguel de Cervantes", true));
        initialNovelas.add(new Novela("Crónica de una muerte anunciada", "Gabriel García Márquez", false));
        novelas.setValue(initialNovelas);
    }

    public void updateNovela(Novela updatedNovela) {
    }
}
