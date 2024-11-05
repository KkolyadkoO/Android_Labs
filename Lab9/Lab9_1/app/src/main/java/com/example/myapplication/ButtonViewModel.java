package com.example.myapplication;

import androidx.lifecycle.ViewModel;

public class ButtonViewModel extends ViewModel {
    private String lastButtonPressed;
    private String lastSavedButton;

    public String getLastButtonPressed() {
        return lastButtonPressed;
    }

    public void setLastButtonPressed(String lastButtonPressed) {
        this.lastButtonPressed = lastButtonPressed;
    }
    public String getLastSavedButton() {
        return lastSavedButton;
    }

    public void setLastSavedButton(String lastButtonSaved) {
        this.lastSavedButton = lastButtonSaved;
    }
}
