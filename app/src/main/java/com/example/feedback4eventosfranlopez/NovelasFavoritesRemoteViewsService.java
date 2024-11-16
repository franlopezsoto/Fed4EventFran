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
    }

    @Override
    public void onDataSetChanged() {
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
        return favoriteNovelas.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (position == RemoteViewsService.RemoteViewsFactory.INVALID_POSITION || favoriteNovelas.isEmpty()) {
            return null;
        }

        String novelaTitle = favoriteNovelas.get(position);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        views.setTextViewText(R.id.widget_item_text, novelaTitle);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
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
