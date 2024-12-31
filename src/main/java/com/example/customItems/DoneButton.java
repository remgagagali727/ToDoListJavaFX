package com.example.customItems;

import javafx.scene.Node;
import javafx.scene.control.Button;

import java.time.LocalDateTime;

public class DoneButton extends DoingButton{
    public DoneButton() {
        super();
    }

    public DoneButton(String text) {
        super(text);
    }

    public DoneButton(String text, Node node) {
        super(text, node);
    }

    private LocalDateTime endDate;

    public void endDate() {
        endDate = LocalDateTime.now();
    }

    public void setStartDate(LocalDateTime localDateTime) {
        startedDate = localDateTime;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
