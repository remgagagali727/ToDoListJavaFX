package com.example.todolist;

import com.example.customItems.DoingButton;
import com.example.customItems.DoneButton;
import com.example.customItems.TaskHistory;
import com.example.customItems.ToDoButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.todolist.Util.lowPriority;

public class HelloController {

    private String sortingStyle;
    public static Button classicToDoButton;
    public static Button classicDoingButton;
    public static Button classicDoneButton;
    private Stage modifyTaskStage = null;
    public static int visualizationValue = 0;
    public static int idToDo;
    public static int idDoing;
    public static int idDone;
    public static List<ToDoButton> toDoList = new ArrayList<>();
    public static List<DoingButton> doingList = new ArrayList<>();
    public static List<DoneButton> doneList = new ArrayList<>();
    public static List<TaskHistory> historyList = new ArrayList<>();

    @FXML
    public void initialize() {
        classicToDoButton = (Button) toDoVBox.getChildren().remove(0);
        classicDoingButton = ((Button) doingVBox.getChildren().remove(0));
        classicDoneButton = ((Button) doneVBox.getChildren().remove(0));
        goUpButton.setVisible(false);
        goDownButton.setVisible(false);
        var items = sortComboBox.getItems();
        while (!items.isEmpty()) items.removeLast();
        items.add("Ordenar Nombre ▼");
        items.add("Ordenar Nombre ▲");
        items.add("Ordenar Prioridad ▼");
        items.add("Ordenar Prioridad ▲");
        sortComboBox.setValue("Ordenar Nombre ▼");
        sortingStyle = sortComboBox.getValue();
    }

    @FXML
    protected void onHistorialButtonClick() throws IOException {
        if(modifyTaskStage != null) {
            modifyTaskStage.toFront();
        } else {
            modifyTaskStage = new Stage();
            modifyTaskStage.setTitle("Historial");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("task-historial.fxml"));
            Parent root = loader.load();
            Scene historial = new Scene(root, 400, 700);
            modifyTaskStage.setScene(historial);
            modifyTaskStage.setOnHidden(_ -> {
                modifyTaskStage = null;
            });
            modifyTaskStage.show();
        }
    }

    @FXML
    protected void onSortingComboBoxAction() {
        sortingStyle = sortComboBox.getValue();
        resetAllVBox();
    }

    @FXML
    protected void onAddTask() {
        ToDoButton newTask = Util.Clone.toDoButton(classicToDoButton);
        newTask.adjustColor();
        Util.setBackground(newTask, newTask.getColor());
        Util.setText(newTask, "Tarea Default");
        toDoList.add(newTask);
        sortAllList();
        visualizationValue = toDoList.indexOf(newTask) / 3;
        resetAllVBox();
    }

    @FXML
    protected void onGoDownButtonClick() {
        visualizationValue--;
        resetAllVBox();
    }

    @FXML
    protected void onGoUpButtonClick() {
        visualizationValue++;
        resetAllVBox();
    }

    public void validate() {
        goDownButton.setVisible(visualizationValue != 0);
        boolean upBool = toDoList.size() > (visualizationValue + 1) * 3
                || doingList.size() > (visualizationValue + 1) * 3
                || doneList.size() > (visualizationValue + 1) * 3;
        goUpButton.setVisible(upBool);
    }

    public void sortAllList() {
        switch (sortingStyle) {
            case "Ordenar Nombre ▼":
                toDoList.sort((o1, o2) -> { return o1.getText().compareTo(o2.getText()); });
                doingList.sort((o1, o2) -> { return o1.getText().compareTo(o2.getText()); });
                doneList.sort((o1, o2) -> { return o1.getText().compareTo(o2.getText()); });
                break;
            case "Ordenar Nombre ▲":
                toDoList.sort((o1, o2) -> { return o2.getText().compareTo(o1.getText()); });
                doingList.sort((o1, o2) -> { return o2.getText().compareTo(o1.getText()); });
                doneList.sort((o1, o2) -> { return o2.getText().compareTo(o1.getText()); });
                break;
            case "Ordenar Prioridad ▲":
                toDoList.sort((o1, o2) -> { return o1.getPriority().compareTo(o2.getPriority()); });
                doingList.sort((o1, o2) -> { return o1.getPriority().compareTo(o2.getPriority()); });
                doneList.sort((o1, o2) -> { return o1.getPriority().compareTo(o2.getPriority()); });
                break;
            case "Ordenar Prioridad ▼":
                toDoList.sort((o1, o2) -> { return o2.getPriority().compareTo(o1.getPriority()); });
                doingList.sort((o1, o2) -> { return o2.getPriority().compareTo(o1.getPriority()); });
                doneList.sort((o1, o2) -> { return o2.getPriority().compareTo(o1.getPriority()); });
                break;
        }
    }

    public void resetAllVBox() {
        sortAllList();
        if(visualizationValue * 3 >= toDoList.size()
            && visualizationValue * 3 >= doingList.size()
            && visualizationValue * 3 >= doneList.size())
            visualizationValue--;
        if(visualizationValue < 0) visualizationValue = 0;
        var children = toDoVBox.getChildren();
        while(!children.isEmpty()) children.removeLast();
        children = doingVBox.getChildren();
        while(!children.isEmpty()) children.removeLast();
        children = doneVBox.getChildren();
        while(!children.isEmpty()) children.removeLast();
        validate();
        showAllVBox();
    }

    public void showAllVBox() {
        for(int i = visualizationValue * 3; i < (visualizationValue + 1) * 3;i++) {
            if(i < toDoList.size()) toDoVBox.getChildren().add(toDoList.get(i));
            if(i < doingList.size()) doingVBox.getChildren().add(doingList.get(i));
            if(i < doneList.size()) doneVBox.getChildren().add(doneList.get(i));
        }
    }

    @FXML
    protected void onModifyTaskToDoClick(ActionEvent actionEvent) throws IOException {
        ToDoButton taskButton = ((ToDoButton) actionEvent.getSource());
        if(modifyTaskStage != null) {
            modifyTaskStage.toFront();
        } else {
            modifyTaskStage = new Stage();
            modifyTaskStage.setTitle(taskButton.getText() + " Options");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("task-options.fxml"));
            Parent root = loader.load();
            TaskOptionsController taskOptionsController = loader.getController();
            Scene taskOpcionsScene = new Scene(root, 600, 400);
            modifyTaskStage.setScene(taskOpcionsScene);

            // Configura el controlador principal en el secundario
            taskOptionsController.setHelloController(this);


            Slider mySlider = ((Slider) ((Pane) taskOpcionsScene.getRoot()).getChildren().get(2));
            TextField textField = ((TextField) ((Pane) taskOpcionsScene.getRoot()).getChildren().get(4));
            textField.setText("");
            idToDo = toDoList.indexOf(taskButton);
            mySlider.setValue(toDoList.get(idToDo).getPriority());
            modifyTaskStage.setOnHidden(_ -> {
                modifyTaskStage = null;
                resetAllVBox();
            });
            modifyTaskStage.show();
        }
    }

    @FXML
    protected void onModifyTaskDoingClick(ActionEvent actionEvent) throws IOException {
        DoingButton doingButton = ((DoingButton) actionEvent.getSource());
        if(modifyTaskStage != null) {
            modifyTaskStage.toFront();
        } else {
            modifyTaskStage = new Stage();
            modifyTaskStage.setTitle(doingButton.getText() + " Info");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("task-doing-options.fxml"));
            Parent root = loader.load();
            TaskDoingOptionsController taskDoingOptionsController = loader.getController();
            Scene taskOpcionsScene = new Scene(root, 600, 400);
            modifyTaskStage.setScene(taskOpcionsScene);

            Label prioridad = ((Label) ((Pane) taskOpcionsScene.getRoot()).getChildren().get(1));
            Label nombre = ((Label) ((Pane) taskOpcionsScene.getRoot()).getChildren().get(2));
            double prioridadInt = doingButton.getPriority();
            int x = (int) prioridadInt;
            prioridad.setText("Prioridad de la tarea: " + x);
            nombre.setText("Nombre de la tarea: " + doingButton.getText());

            taskDoingOptionsController.setHelloController(this);

            idDoing = doingList.indexOf(doingButton);
            modifyTaskStage.setOnHidden(_ -> {
                modifyTaskStage = null;
                resetAllVBox();
            });
            modifyTaskStage.show();
        }
    }

    @FXML
    protected void onModifyTaskDoneClick(ActionEvent actionEvent) throws IOException {
        DoneButton doneButton = ((DoneButton) actionEvent.getSource());
        if(modifyTaskStage != null) {
            modifyTaskStage.toFront();
        } else {
            modifyTaskStage = new Stage();
            modifyTaskStage.setTitle(doneButton.getText() + " Finished Info");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("task-done-options.fxml"));
            Parent root = loader.load();
            TaskDoneOptionsController taskDoingOptionsController = loader.getController();
            Scene taskOpcionsScene = new Scene(root, 600, 400);
            modifyTaskStage.setScene(taskOpcionsScene);

            Label prioridad = ((Label) ((Pane) taskOpcionsScene.getRoot()).getChildren().get(1));
            Label nombre = ((Label) ((Pane) taskOpcionsScene.getRoot()).getChildren().get(2));
            Label fechaInicio = ((Label) ((Pane) taskOpcionsScene.getRoot()).getChildren().get(3));
            Label fechaTermino = ((Label) ((Pane) taskOpcionsScene.getRoot()).getChildren().get(4));
            DateTimeFormatter sdf = DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yy");
            fechaInicio.setText("Fecha de inicio: " + sdf.format(doneButton.getStartedDate()));
            fechaTermino.setText("Fecha de finalizacion: " + sdf.format(doneButton.getEndDate()));
            double prioridadInt = doneButton.getPriority();
            int x = (int) prioridadInt;
            prioridad.setText("Prioridad de la tarea: " + x);
            nombre.setText("Nombre de la tarea: " + doneButton.getText());

            taskDoingOptionsController.setHelloController(this);

            idDone = doneList.indexOf(doneButton);
            modifyTaskStage.setOnHidden(_ -> {
                modifyTaskStage = null;
                resetAllVBox();
            });
            modifyTaskStage.show();
        }
    }

    @FXML
    private Button goUpButton;

    @FXML
    private Button goDownButton;

    @FXML
    private VBox toDoVBox;

    @FXML
    private VBox doingVBox;

    @FXML
    private VBox doneVBox;

    @FXML
    private ComboBox<String> sortComboBox;
}