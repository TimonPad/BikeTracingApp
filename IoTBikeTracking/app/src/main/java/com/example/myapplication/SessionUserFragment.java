package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.sessions.MyDatabase;
import com.example.myapplication.sessions.SessionsUser;

import java.util.Collections;
import java.util.List;

public class SessionUserFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    SessionUserAdapter sessionUserAdapter;

    public SessionUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_session_user, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        List<SessionsUser> sessionsUserList = MainActivity.database.sessionsUserDAO().getAllSessionsUser();
        Collections.reverse(sessionsUserList);
        sessionUserAdapter = new SessionUserAdapter(sessionsUserList);
        recyclerView.setAdapter(sessionUserAdapter);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.setupDrawerToggle();
        }
        return view;
    }
}

