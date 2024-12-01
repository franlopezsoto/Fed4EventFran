package com.example.feedback4eventosfranlopez;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

public class NovelasFavoritesRemoteViewsService implements RemoteViewsService.RemoteViewsFactory {

    private final Context context;
    private List<String> favoriteNovelas;

    public NovelasFavoritesRemoteViewsService(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        favoriteNovelas = new ArrayList<>();
        // Carga inicial para evitar listas vacías
        loadInitialData();
    }

    @Override
    public void onDataSetChanged() {
        // Actualiza los datos; en este caso, simula datos
        favoriteNovelas.clear();
        favoriteNovelas.add("Cien años de soledad");
        favoriteNovelas.add("Don Quijote de la Mancha");
        favoriteNovelas.add("Crónica de una muerte anunciada");
    }

    @Override
    public void onDestroy() {
        favoriteNovelas.clear();
    }

    @Override
    public int getCount() {
        return favoriteNovelas != null ? favoriteNovelas.size() : 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        // Validación robusta de la posición
        if (favoriteNovelas == null || favoriteNovelas.isEmpty() || position < 0 || position >= favoriteNovelas.size()) {
            return null;
        }

        String novelaTitle = favoriteNovelas.get(position);

        // Configura la vista para este elemento
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        views.setTextViewText(R.id.widget_item_text, novelaTitle);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null; // O podrías crear una vista de carga personalizada si lo prefieres
    }

    @Override
    public int getViewTypeCount() {
        return 1; // Solo hay un tipo de vista en este ejemplo
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true; // Los IDs no cambian
    }

    // Carga inicial para evitar listas vacías
    private void loadInitialData() {
        favoriteNovelas.add("Sin favoritos aún");
    }
}

