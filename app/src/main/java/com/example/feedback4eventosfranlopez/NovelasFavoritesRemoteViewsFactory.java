package com.example.feedback4eventosfranlopez;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

public class NovelasFavoritesRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context context;
    private List<String> favoriteNovelas;

    public NovelasFavoritesRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        // Inicializar lista de datos
        favoriteNovelas = new ArrayList<>();
    }

    @Override
    public void onDataSetChanged() {
        // Cargar datos actualizados
        favoriteNovelas.clear();
        favoriteNovelas.add("Cien años de soledad");
        favoriteNovelas.add("Don Quijote de la Mancha");
        favoriteNovelas.add("Crónica de una muerte anunciada");
        // Aquí puedes cargar datos desde SQLite o SharedPreferences
    }

    @Override
    public void onDestroy() {
        // Liberar recursos
        favoriteNovelas.clear();
    }

    @Override
    public int getCount() {
        return favoriteNovelas != null ? favoriteNovelas.size() : 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (position == RemoteViewsService.RemoteViewsFactory.INVALID_POSITION || favoriteNovelas == null || favoriteNovelas.isEmpty()) {
            return null;
        }

        // Obtener el título de la novela
        String novelaTitle = favoriteNovelas.get(position);

        // Configurar la vista del elemento
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        views.setTextViewText(R.id.widget_item_text, novelaTitle);

        // Configurar intent para manejar clics
        Intent fillInIntent = new Intent();
        fillInIntent.putExtra("novela_title", novelaTitle);
        views.setOnClickFillInIntent(R.id.widget_item_text, fillInIntent);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        // Vista de carga mientras se procesan los datos
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1; // Un solo tipo de vista
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
