package ru.SilirdCo.TemplateProject.Frames;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class ProgressFrameController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(ProgressFrameController.class);

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button butCancel;

    private final DoubleProperty progressProperty;
    private final CompletableFuture<Void> completeFuture;

    public ProgressFrameController(DoubleProperty progressProperty, CompletableFuture<Void> completeFuture) {
        this.progressProperty = progressProperty;
        this.completeFuture = completeFuture;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();
    }

    private void initListeners() {
        progressBar.progressProperty().bind(progressProperty);

        butCancel.setOnAction(event -> {
            JavaFXLaunch.getInstance().sendInfo("Хех, ну кароч она не работает должным образом и всё продолжает работать, весь мусор почисти сам, давай, кабанчиком.");
            JavaFXLaunch.getInstance().closeWindow();
        });

        completeFuture.thenRun(JavaFXLaunch.getInstance()::closeWindow);
    }
}
