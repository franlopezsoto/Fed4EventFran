package com.example.feedback4eventosfranlopez;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NovelaAdapter.OnNovelaClickListener {

    private Button buttonAddBook;
    private RecyclerView recyclerView;
    private NovelaAdapter novelaAdapter;
    private ArrayList<Novela> novelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddBook = findViewById(R.id.buttonAddBook);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        novelas = new ArrayList<>();
        novelaAdapter = new NovelaAdapter(this);
        novelaAdapter.setNovelas(novelas);
        recyclerView.setAdapter(novelaAdapter);

        loadNovelas();

        buttonAddBook.setOnClickListener(v -> addNovela());
    }

    private void loadNovelas() {
        novelas.add(new Novela(1, "Cien años de soledad", "Gabriel García Márquez", true));
        novelas.add(new Novela(2, "Don Quijote de la Mancha", "Miguel de Cervantes", false));
        novelas.add(new Novela(3, "La sombra del viento", "Carlos Ruiz Zafón", true));
        novelaAdapter.notifyDataSetChanged();
    }

    private void addNovela() {
        Novela nuevaNovela = new Novela(novelas.size() + 1, "Nueva Novela", "Autor Desconocido", false);
        novelas.add(nuevaNovela);
        novelaAdapter.notifyItemInserted(novelas.size() - 1);
        Toast.makeText(this, "Nueva novela añadida", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNovelaClick(Novela novela) {
        Toast.makeText(this, "Seleccionado: " + novela.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNovelaEdit(Novela novela, int position) {
        showEditDialog(novela, position);
    }

    private void showEditDialog(Novela novela, int position) {
        // Crear un cuadro de diálogo para editar la novela
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_novela, null);
        EditText editTitle = dialogView.findViewById(R.id.editTextTitle);
        EditText editAuthor = dialogView.findViewById(R.id.editTextAuthor);


        editTitle.setText(novela.getTitle());
        editAuthor.setText(novela.getAuthor());

        new AlertDialog.Builder(this)
                .setTitle("Editar Novela")
                .setView(dialogView)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    String newTitle = editTitle.getText().toString();
                    String newAuthor = editAuthor.getText().toString();
                    novelaAdapter.updateNovela(position, newTitle, newAuthor);
                    Toast.makeText(this, "Novela actualizada", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
