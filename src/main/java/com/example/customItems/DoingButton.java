package com.example.customItems;

import com.example.todolist.Util.Clone;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.time.LocalDateTime;

public class DoingButton extends ToDoButton{
    public DoingButton() {
        super();
    }

    public DoingButton(String text) {
        super(text);
    }

    public DoingButton(String text, Node node) {
        super(text, node);
    }

    protected LocalDateTime startedDate;

    public void startDate() {
        startedDate = LocalDateTime.now();
    }

    public LocalDateTime getStartedDate() {
        return startedDate;
    }

    //Sobreescritura de metodos
    @Override
    public Button next() {
        DoneButton db = Clone.doneButton(this);
        db.endDate();
        return db;
    }
}
