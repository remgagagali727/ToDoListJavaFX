package com.example.todolist;

import com.example.customItems.DoneButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TaskDoingOptionsController {
    private HelloController helloController;

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    @FXML
    protected void onBorrarTareaButtonClick(ActionEvent actionEvent) {
        Button borrarTarea = ((Button) actionEvent.getTarget());
        ((Stage) borrarTarea.getScene().getWindow()).close();
        HelloController.doingList.remove(HelloController.idDoing);
        helloController.resetAllVBox();
    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent actionEvent) {
        Button borrarTarea = ((Button) actionEvent.getTarget());
        ((Stage) borrarTarea.getScene().getWindow()).close();
    }

    @FXML
    protected void onFinalizarTareaClick(ActionEvent actionEvent) {
        Button borrarTarea = ((Button) actionEvent.getTarget());
        ((Stage) borrarTarea.getScene().getWindow()).close();
        DoneButton newDoneButton = Util.Clone.doneButton(HelloController.doingList.get(HelloController.idDoing));
        newDoneButton.endDate();
        HelloController.doingList.remove(HelloController.idDoing);
        HelloController.doneList.add(newDoneButton);
        helloController.resetAllVBox();
    }


}
