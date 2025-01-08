package com.example.todolist;

import com.example.customItems.DoingButton;
import com.example.customItems.ToDoButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
        String newName;
        //Exception
        try{
            newName = getTextFieldText(container.getChildren(), 4);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            newName = "Tarea Default";
        }
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

    private String getTextFieldText(ObservableList<Node> children, int i) throws IllegalArgumentException{
        if(!(children.get(i) instanceof TextField)) throw new IllegalArgumentException("No es un textField");
        TextField textField = ((TextField) children.get(i));
        String name = textField.getText();
        if(name.length() > 30) {
            throw new IllegalArgumentException("La longitud del nombre excede la permitida");
        }
        return name;
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
