package com.example.todolist;

import com.example.customItems.DoingButton;
import com.example.customItems.ToDoButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskOptionsController {

    private HelloController helloController;

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    @FXML
    protected void onConfirmarButtonClick(ActionEvent actionEvent) {
        Button confirmar = ((Button) actionEvent.getTarget());
        ((Stage) confirmar.getScene().getWindow()).close();
        VBox container = ((VBox) confirmar.getParent().getParent());
        Slider slider = ((Slider) container.getChildren().get(2));
        TextField textField = ((TextField) container.getChildren().get(4));
        String newName = textField.getText();
        //Exception
        if(newName.length() >= 30) throw new RuntimeException("La longitud del nombre de la tarea es muy larga");
        ToDoButton currButton = HelloController.toDoList.get(HelloController.idToDo);
        currButton.setPriority(slider.getValue());
        currButton.adjustColor();
        //UpCAST
        Util.setBackground(currButton, currButton.getColor());
        if(!newName.isEmpty())
            Util.setText(currButton, newName);
        helloController.sortAllList();
        HelloController.idToDo = HelloController.toDoList.indexOf(currButton);
        HelloController.visualizationValue = HelloController.idToDo / 3;
        helloController.resetAllVBox();
    }

    @FXML
    protected void onBorrarTareaButtonClick(ActionEvent actionEvent) {
        Button borrarTarea = ((Button) actionEvent.getTarget());
        ((Stage) borrarTarea.getScene().getWindow()).close();
        HelloController.toDoList.remove(HelloController.idToDo);
        helloController.resetAllVBox();
    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent actionEvent) {
        Button borrarTarea = ((Button) actionEvent.getTarget());
        ((Stage) borrarTarea.getScene().getWindow()).close();
    }

    @FXML
    protected void onComenzarTareaButtonClick(ActionEvent actionEvent) {
        Button borrarTarea = ((Button) actionEvent.getTarget());
        ((Stage) borrarTarea.getScene().getWindow()).close();
        DoingButton doingButton = Util.Clone.doingButton(HelloController.toDoList.get(HelloController.idToDo));
        HelloController.toDoList.remove(HelloController.idToDo);
        HelloController.doingList.add(doingButton);
        helloController.resetAllVBox();
    }

}
