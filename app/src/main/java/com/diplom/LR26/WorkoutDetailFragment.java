package com.diplom.LR26;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WorkoutDetailFragment extends Fragment {

    private TextView workoutName;
    private TextView workoutDescription;
    private TextView timerText;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;

    private Handler handler;
    private long startTime = 0L;
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updatedTime = 0L;
    private boolean isRunning = false;

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_detail, container, false);

        workoutName = view.findViewById(R.id.workout_name);
        workoutDescription = view.findViewById(R.id.workout_description);
        timerText = view.findViewById(R.id.timer_text);
        startButton = view.findViewById(R.id.start_button);
        stopButton = view.findViewById(R.id.stop_button);
        resetButton = view.findViewById(R.id.reset_button);

        handler = new Handler();

        startButton.setOnClickListener(v -> startTimer());
        stopButton.setOnClickListener(v -> stopTimer());
        resetButton.setOnClickListener(v -> resetTimer());

        return view;
    }

    public void setWorkout(int workoutId) {
        Workout selectedWorkout = Workout.workouts[workoutId];
        workoutName.setText(selectedWorkout.getName());
        workoutDescription.setText(selectedWorkout.getDescription());
    }

    private void startTimer() {
        if (!isRunning) {
            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(updateTimer, 0);
            isRunning = true;
        }
    }

    private void stopTimer() {
        if (isRunning) {
            timeSwapBuff += timeInMilliseconds;
            handler.removeCallbacks(updateTimer);
            isRunning = false;
        }
    }

    private void resetTimer() {
        startTime = 0L;
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;
        timerText.setText("00:00:00");
        if (isRunning) {
            handler.removeCallbacks(updateTimer);
            isRunning = false;
        }
    }

    private final Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);

            timerText.setText(String.format("%02d:%02d:%02d", mins, secs, milliseconds / 10));
            handler.postDelayed(this, 10);
        }
    };
}
