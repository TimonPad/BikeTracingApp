package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.myapplication.sessions.SessionsUser;

import java.util.Locale;

public class CurrentSessionFragment extends Fragment {
    private Button startStopButton;
    // for button state
    private boolean isStarted;
    private Intent timerServiceIntent;
    private TextView timeView;
    private TextView distanceText;


    // For timer state
    private boolean isRunning = false;
    private long startTimeMillis = 0;
    private long elapsedTimeMillis = 0;

    public CurrentSessionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_session, container, false);
        startStopButton = view.findViewById(R.id.startStopButton);
        timeView = view.findViewById(R.id.time_view);
        distanceText = view.findViewById(R.id.distance_view);

        // Register a BroadcastReceiver to receive updates from TimerService
        BroadcastReceiver timerUpdateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(TimerService.TIMER_UPDATE_ACTION)) {
                    elapsedTimeMillis = intent.getLongExtra(TimerService.ELAPSED_TIME, 0);
                    updateUIWithElapsedTime();
                }
            }
        };

        // Register the BroadcastReceiver to listen for updates
        getActivity().registerReceiver(timerUpdateReceiver, new IntentFilter(TimerService.TIMER_UPDATE_ACTION));

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStarted = !isStarted;
                if (isStarted) {
                    timerServiceIntent = new Intent(getActivity(), TimerService.class);
                    if (!isRunning) {
                        startTimeMillis = System.currentTimeMillis();
                        getActivity().startService(timerServiceIntent);
                        isRunning = true;
                    }
                    startStopButton.setText("Stop");
                    startStopButton.setBackgroundResource(R.drawable.round_button_red);
                } else {
                    // Stops the TimerService
                    getActivity().stopService(timerServiceIntent);
                    if (isRunning) {
                        isRunning = false;
                        updateUIWithElapsedTime();
                    }
                    insertSessionIntoDatabase();
                    startStopButton.setText("Start");
                    startStopButton.setBackgroundResource(R.drawable.round_button_green);
                }
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the button state to restore it later
        outState.putBoolean("isStarted", isStarted);
    }

    // Update the UI with elapsed time
    private void updateUIWithElapsedTime() {
        long elapsedSeconds = elapsedTimeMillis / 1000;
        int hrs = (int) (elapsedSeconds / 3600);
        int mins = (int) ((elapsedSeconds % 3600) / 60);
        int secs = (int) (elapsedSeconds % 60);
        String time_t = String.format(Locale.getDefault(), "    %02d:%02d:%02d   ", hrs, mins, secs);
        timeView.setText(time_t);
    }

    private void insertSessionIntoDatabase() {
        // Convert elapsed time to a formatted string
        long elapsedSeconds = elapsedTimeMillis / 1000;
        int hrs = (int) (elapsedSeconds / 3600);
        int mins = (int) ((elapsedSeconds % 3600) / 60);
        int secs = (int) (elapsedSeconds % 60);
        String timeValue = String.format(Locale.getDefault(), "%02d:%02d:%02d", hrs, mins, secs);
        distanceText.setText("0.00 km");
        // Get the current distance value
        String distanceValue = distanceText.getText().toString();

        SessionsUser session = new SessionsUser();
        session.setSessionTime(timeValue);
        session.setSessionDistance(distanceValue); // You can set the distance if needed
        MainActivity.database.sessionsUserDAO().insertSessionUser(session);
    }
}