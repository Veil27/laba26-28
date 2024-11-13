package com.diplom.LR26;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WorkoutDetailFragment frag = (WorkoutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        int workoutId = getIntent().getIntExtra(EXTRA_WORKOUT_ID, -1);
        if (frag != null) {
            frag.setWorkout(workoutId);
        }
    }
}
