package com.example.customItems;

import com.example.todolist.HelloController;
import com.example.todolist.Util;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import static com.example.todolist.Util.highPriority;
import static com.example.todolist.Util.lowPriority;

public class ToDoButton extends Button implements HasNextButton{

    public ToDoButton() {
        super();
    }

    public ToDoButton(String text) {
        super(text);
    }

    public ToDoButton(String text, Node node) {
        super(text, node);
    }

    private Double priority;
    private Color color;

    public Double getPriority() {
        return priority;
    }

    public Color getColor() {
        return color;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Button next() {
        DoingButton next = Util.Clone.doingButton(this);
        return next;
    }

    public void adjustColor() {
        color = new Color(highPriority[0] + (1 - priority / 100) * (lowPriority[0] - highPriority[0])
                , highPriority[1] + (1 - priority / 100) * (lowPriority[1] - highPriority[1])
                , highPriority[2] + (1 - priority / 100) * (lowPriority[2] - highPriority[2])
                , 1f);
    }
}
