package com.example.customItems;

import javafx.scene.paint.Color;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskHistory {
    private final Double priority;
    private final Color color;
    private final LocalDateTime endDate;
    private final LocalDateTime startDate;
    private final String name;

    public TaskHistory(Double priority, Color color, LocalDateTime endDate, LocalDateTime startDate, String name) {
        this.priority = priority;
        this.color = color;
        this.endDate = endDate;
        this.startDate = startDate;
        this.name = name;
    }

    @Override
    public String toString() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yy");
        return "Tarea:\n" + name + '\n' +
                "prioridad = " + priority + '\n' +
                "fecha de finalizacion = " + sdf.format(endDate) + '\n' +
                "fecha de inicio = " + sdf.format(startDate) + '\n';
    }
}
