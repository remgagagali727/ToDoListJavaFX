package com.example.todolist;

import com.example.customItems.DoingButton;
import com.example.customItems.DoneButton;
import com.example.customItems.ToDoButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;

public class Util {

    public static final double[] highPriority = {0,0.5294117647,0.5058823529};
    public static final double[] lowPriority = {0.4274509804, 0.8588235294, 0.8431372549};

    public static Button setBackground(Button tb, Color c) {
        String customStyle = tb.getStyle();
        String[] arrayStyle = customStyle.split("; ");
        int size = arrayStyle.length;
        if(!arrayStyle[size - 1].isEmpty())
            arrayStyle[size - 1] = arrayStyle[size - 1].substring(0, arrayStyle[size - 1].length() - 1);
        List<String> arrayListStyle = List.of(arrayStyle);
        arrayListStyle = arrayListStyle.stream()
                .filter(S -> {
                    if(S.length() > 20)
                        return !S.startsWith("-fx-background-color:");
                    return true;
                })
                .toList();
        int red = (int) (c.getRed() * 255);
        int green = (int) (c.getGreen() * 255);
        int blue = (int) (c.getBlue() * 255);
        StringBuilder style = new StringBuilder().append("-fx-background-color: ").append(String.format("#%02X%02X%02X", red, green, blue)).append(";");
        for(String s : arrayListStyle) {
            if(s.isEmpty()) continue;
            style.append(" ");
            style.append(s);
            style.append(";");
        }
        tb.setStyle(style.toString());
        return tb;
    }

    public static Button setText(Button tb, String t) {
        if(!t.isEmpty()) tb.setText(t);
        return tb;
    }

    public static class Clone {
        public static ToDoButton toDoButton(Button o) {
            ToDoButton c;
            c = new ToDoButton(o.getText());
            c.setPriority(0.0);
            c.setColor(new Color(lowPriority[0],lowPriority[1],lowPriority[2],1f));

            //Size
            c.setPrefWidth(o.getPrefWidth());
            c.setPrefHeight(o.getPrefHeight());
            c.setMinWidth(o.getMinWidth());
            c.setMinHeight(o.getMinHeight());
            c.setMaxWidth(o.getMaxWidth());
            c.setMaxHeight(o.getMaxHeight());

            //Eventos
            c.setOnAction(o.getOnAction());

            // Estilo y apariencia
            c.setStyle(o.getStyle());
            c.setFont(o.getFont());
            c.setTextFill(o.getTextFill());
            c.setAlignment(o.getAlignment());
            c.setContentDisplay(o.getContentDisplay());
            c.setGraphic(o.getGraphic());
            c.setGraphicTextGap(o.getGraphicTextGap());
            c.setUnderline(o.isUnderline());
            c.setWrapText(o.isWrapText());
            return c;
        }

        public static DoingButton doingButton(ToDoButton o) {
            DoingButton c = new DoingButton(o.getText());

            c.setColor(o.getColor());
            c.setPriority(o.getPriority());
            c.startDate();

            //Size
            c.setPrefWidth(o.getPrefWidth());
            c.setPrefHeight(o.getPrefHeight());
            c.setMinWidth(o.getMinWidth());
            c.setMinHeight(o.getMinHeight());
            c.setMaxWidth(o.getMaxWidth());
            c.setMaxHeight(o.getMaxHeight());

            //Eventos
            c.setOnAction(HelloController.classicDoingButton.getOnAction());

            // Estilo y apariencia
            c.setStyle(o.getStyle());
            c.setFont(o.getFont());
            c.setTextFill(o.getTextFill());
            c.setAlignment(o.getAlignment());
            c.setContentDisplay(o.getContentDisplay());
            c.setGraphic(o.getGraphic());
            c.setGraphicTextGap(o.getGraphicTextGap());
            c.setUnderline(o.isUnderline());
            c.setWrapText(o.isWrapText());
            return c;
        }

        public static DoneButton doneButton(DoingButton o) {
            DoneButton c = new DoneButton(o.getText());
            c.setColor(o.getColor());
            c.setPriority(o.getPriority());
            c.endDate();
            c.setStartDate(o.getStartedDate());

            //Size
            c.setPrefWidth(o.getPrefWidth());
            c.setPrefHeight(o.getPrefHeight());
            c.setMinWidth(o.getMinWidth());
            c.setMinHeight(o.getMinHeight());
            c.setMaxWidth(o.getMaxWidth());
            c.setMaxHeight(o.getMaxHeight());

            //Eventos
            c.setOnAction(HelloController.classicDoneButton.getOnAction());

            // Estilo y apariencia
            c.setStyle(o.getStyle());
            c.setFont(o.getFont());
            c.setTextFill(o.getTextFill());
            c.setAlignment(o.getAlignment());
            c.setContentDisplay(o.getContentDisplay());
            c.setGraphic(o.getGraphic());
            c.setGraphicTextGap(o.getGraphicTextGap());
            return c;
        }

        public static Label label(Label o) {
            Label c = new Label();

            c.setStyle(o.getStyle());
            c.setAlignment(o.getAlignment());
            c.setPrefSize(o.getPrefWidth(), o.getPrefHeight());
            c.setMinSize(o.getMinWidth(), o.getMinHeight());
            c.setMaxSize(o.getMaxWidth(), o.getMaxHeight());
            c.setId(o.getId());
            c.setVisible(o.isVisible());
            c.setGraphic(o.getGraphic());
            c.setEffect(o.getEffect());
            return c;
        }
    }
}
