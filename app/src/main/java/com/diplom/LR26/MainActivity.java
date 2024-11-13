package com.diplom.LR26;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.Listener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        // Проверка на наличие фрагмента detail_frag
        WorkoutDetailFragment detailFragment = (WorkoutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);

        if (detailFragment != null) {
            // Если detail_frag присутствует, обновить контент внутри фрагмента
            detailFragment.setWorkout((int) id);
        } else {
            // Если detail_frag отсутствует, запускаем DetailActivity (для телефонов)
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int) id);
            startActivity(intent);
        }
    }
}
