package ru.SilirdCo.TemplateProject.Util.Factories;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.TemplateProject.Frames.JavaFXLaunch;

import java.util.concurrent.CompletableFuture;

public class FrameFactory {
    private final static Logger logger = LoggerFactory.getLogger(FrameFactory.class);

    private static FrameFactory instance;

    public static FrameFactory getInstance() {
        if (instance == null) {
            instance = new FrameFactory();
        }
        return instance;
    }

    public void getMainFrame() {
        Node content = PanelFactory.getInstance()
                .getMainPanel();

        JavaFXLaunch.getInstance()
                .showWindow(content);
    }

    public void getProgressFrame(DoubleProperty progressProperty, CompletableFuture<Void> completeFuture) {
        Node content = PanelFactory.getInstance()
                .getProgressPanel(progressProperty, completeFuture);

        JavaFXLaunch.getInstance()
                .showWindow(content);
    }
}
