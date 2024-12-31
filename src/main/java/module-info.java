module com.example.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.todolist to javafx.fxml;
    exports com.example.todolist;
}