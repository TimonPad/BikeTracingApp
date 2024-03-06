package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class TimerService extends Service {
    private boolean isRunning;
    private long startTime;
    private long elapsedTime;

    public static final String TIMER_UPDATE_ACTION = "com.example.myapplication.TimerUpdateAction";
    public static final String ELAPSED_TIME = "elapsedTime";

    public TimerService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            if (isRunning) {
            } else {
                startTime = System.currentTimeMillis() - elapsedTime;
                isRunning = true;

                // Send updates to the HomeFragment
                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isRunning) {
                            elapsedTime = System.currentTimeMillis() - startTime;

                            // Broadcast the elapsed time to the HomeFragment
                            Intent updateIntent = new Intent(TIMER_UPDATE_ACTION);
                            updateIntent.putExtra(ELAPSED_TIME, elapsedTime);
                            sendBroadcast(updateIntent);

                            handler.postDelayed(this, 1000);
                        }
                    }
                });
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}