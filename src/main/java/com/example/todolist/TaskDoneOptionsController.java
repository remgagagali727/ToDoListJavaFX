package com.example.todolist;

import com.example.customItems.DoneButton;
import com.example.customItems.TaskHistory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TaskDoneOptionsController {
    private HelloController helloController;

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    @FXML
    protected void onMandarAlHistorialButtonClick(ActionEvent actionEvent) {
        Button borrarTarea = ((Button) actionEvent.getTarget());
        ((Stage) borrarTarea.getScene().getWindow()).close();
        DoneButton doneTask = HelloController.doneList.remove(HelloController.idDone);
        HelloController.historyList.add(new TaskHistory(
                doneTask.getPriority(), doneTask.getColor(), doneTask.getEndDate(), doneTask.getStartedDate(), doneTask.getText()
        ));
        helloController.resetAllVBox();
    }

    @FXML
    protected void onBorrarTareaButtonClick(ActionEvent actionEvent) {
        Button borrarTarea = ((Button) actionEvent.getTarget());
        ((Stage) borrarTarea.getScene().getWindow()).close();
        HelloController.doneList.remove(HelloController.idDone);
        helloController.resetAllVBox();
    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent actionEvent) {
        Button borrarTarea = ((Button) actionEvent.getTarget());
        ((Stage) borrarTarea.getScene().getWindow()).close();
    }
}
