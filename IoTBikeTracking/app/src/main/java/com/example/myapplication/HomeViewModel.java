package com.example.myapplication;
public class HomeViewModel extends CurrentSessionFragment {
    private boolean isStarted;

    public boolean getIsStarted() {
        return isStarted;
    }

    public void setIsStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }
}