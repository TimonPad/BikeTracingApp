package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.sessions.SessionsUser;

import java.util.List;

public class SessionUserAdapter extends RecyclerView.Adapter<SessionUserAdapter.SessionUserViewHolder> {
    List<SessionsUser> sessionsUserList;

    public SessionUserAdapter(List<SessionsUser>list){
        this.sessionsUserList = list;
    }

    public void setData(List<SessionsUser> sessionsUserList) {
        this.sessionsUserList = sessionsUserList;
        notifyDataSetChanged();
    }

    @Override
    public SessionUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new SessionUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SessionUserViewHolder holder, int position) {
        holder.timeTextView.setText(sessionsUserList.get(position).getSessionTime());
        holder.distanceTextView.setText(sessionsUserList.get(position).getSessionDistance());

    }

    @Override
    public int getItemCount() {
        return sessionsUserList != null ? sessionsUserList.size() : 0;
    }

    public static class SessionUserViewHolder extends RecyclerView.ViewHolder {
        TextView timeTextView;
        TextView distanceTextView;

        public SessionUserViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.your_time_text_view);
            distanceTextView = itemView.findViewById(R.id.your_time_distance_view);

        }
    }
}
