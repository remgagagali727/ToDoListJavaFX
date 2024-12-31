package com.example.todolist;

import com.example.customItems.TaskHistory;
import com.example.todolist.Util.Clone;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HistorialController {
    @FXML
    private VBox vBox;

    private Label defaultLabel;

    @FXML
    protected void initialize() {
        defaultLabel = ((Label) vBox.getChildren().removeFirst());
        ObservableList<Node> children = vBox.getChildren();
        vBox.setPrefHeight(HelloController.historyList.size() * 120);
        vBox.setMaxHeight(vBox.getPrefHeight());
        for(TaskHistory taskHistory : HelloController.historyList) {
            Label newTask = Clone.label(defaultLabel);
            newTask.setText(taskHistory.toString());
            children.add(newTask);
        }
    }
}
