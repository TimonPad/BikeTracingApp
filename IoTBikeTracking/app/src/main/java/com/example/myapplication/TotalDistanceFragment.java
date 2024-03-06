package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.myapplication.sessions.SessionsUser;
import java.util.List;

public class TotalDistanceFragment extends Fragment {
    double totalDistance = 0.0;

    public TotalDistanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_total_distance, container, false);
        TextView totalDistanceTextView = view.findViewById(R.id.amountTotalCycled);
        List<SessionsUser> sessions = MainActivity.database.sessionsUserDAO().getAllSessionsUser();

        // Calculate the total distance
        for (SessionsUser session : sessions) {
            String sessionDistance = session.getSessionDistance();
            if (sessionDistance != null) {
                try {
                    double sessionDistanceValue = Double.parseDouble(sessionDistance.replaceAll("[^\\d.]+", ""));
                    totalDistance += sessionDistanceValue;
                } catch (NumberFormatException e) {
                    Log.e("Error", "Error parsing distance value: " + sessionDistance);
                }
            }
        }
        totalDistanceTextView.setText(String.format("%.2f km", totalDistance));
        return view;
    }
}
