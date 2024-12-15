package com.example.feedback4eventosfranlopez;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditNovelaDialogFragment extends DialogFragment {

    private static final String ARG_NOVELA = "novela";
    private EditText editTextTitle, editTextAuthor, editTextLatitude, editTextLongitude;
    private Button buttonSave, buttonCancel;
    private Novela novela;
    private OnNovelaUpdatedListener listener;

    public interface OnNovelaUpdatedListener {
        void onNovelaUpdated(Novela novela);
    }

    public static EditNovelaDialogFragment newInstance(Novela novela) {
        EditNovelaDialogFragment fragment = new EditNovelaDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOVELA, novela);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_novela, container, false);

        // Inicializar las vistas
        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextAuthor = view.findViewById(R.id.editTextAuthor);
        editTextLatitude = view.findViewById(R.id.editTextLatitude);
        editTextLongitude = view.findViewById(R.id.editTextLongitude);
        buttonSave = view.findViewById(R.id.buttonSave);
        buttonCancel = view.findViewById(R.id.buttonCancel);

        // Verificar argumentos
        if (getArguments() != null) {
            novela = getArguments().getParcelable(ARG_NOVELA);
            if (novela != null) {
                editTextTitle.setText(novela.getTitle());
                editTextAuthor.setText(novela.getAuthor());
                editTextLatitude.setText(String.valueOf(novela.getLatitude()));
                editTextLongitude.setText(String.valueOf(novela.getLongitude()));
            }
        }

        // Configurar eventos de botones
        buttonSave.setOnClickListener(v -> {
            if (novela != null) {
                novela.setTitle(editTextTitle.getText().toString());
                novela.setAuthor(editTextAuthor.getText().toString());
                novela.setLatitude(Double.parseDouble(editTextLatitude.getText().toString()));
                novela.setLongitude(Double.parseDouble(editTextLongitude.getText().toString()));
                if (listener != null) {
                    listener.onNovelaUpdated(novela);
                }
            }
            dismiss(); // Cerrar el diálogo
        });

        buttonCancel.setOnClickListener(v -> dismiss()); // Cerrar sin guardar

        return view;
    }

    public void setOnNovelaUpdatedListener(OnNovelaUpdatedListener listener) {
        this.listener = listener;
    }
}