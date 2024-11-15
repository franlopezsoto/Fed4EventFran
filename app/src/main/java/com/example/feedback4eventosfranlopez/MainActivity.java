package com.example.feedback4eventosfranlopez;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements NovelasListFragment.OnNovelaSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadFragment(new NovelasListFragment());
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_view, fragment);
        transaction.commit();
    }

    @Override
    public void onNovelaSelected(Novela novela) {
        NovelaDetailFragment detailFragment = NovelaDetailFragment.newInstance(novela);
        loadFragment(detailFragment);
    }
}
