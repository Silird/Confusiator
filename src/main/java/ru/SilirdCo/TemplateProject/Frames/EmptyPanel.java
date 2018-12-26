package ru.SilirdCo.TemplateProject.Frames;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class EmptyPanel {
    public static Node get() {
        AnchorPane result = new AnchorPane();
        result.getChildren().add(new Label("Форма не инициализирована, критическая ошибка"));

        return result;
    }
}
