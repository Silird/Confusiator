package ru.SilirdCo.TemplateProject.Util.Factories;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.TemplateProject.Frames.EmptyPanel;
import ru.SilirdCo.TemplateProject.Frames.MainFrameController;
import ru.SilirdCo.TemplateProject.Frames.ProgressFrameController;
import ru.SilirdCo.TemplateProject.Util.ExceptionHandler;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class PanelFactory {
    private final static Logger logger = LoggerFactory.getLogger(PanelFactory.class);

    private static PanelFactory instance;

    public static PanelFactory getInstance() {
        if (instance == null) {
            instance = new PanelFactory();
        }
        return instance;
    }

    public Node getMainPanel() {
        Node content;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/MainFrame.fxml"));
        MainFrameController controller = new MainFrameController();
        loader.setController(controller);
        try {
            content = loader.load();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
            content = EmptyPanel.get();
        }

        return content;
    }

    public Node getProgressPanel(DoubleProperty progressProperty, CompletableFuture<Void> completeFuture) {
        Node content;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/ProgressFrame.fxml"));
        ProgressFrameController controller = new ProgressFrameController(progressProperty, completeFuture);
        loader.setController(controller);
        try {
            content = loader.load();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
            content = EmptyPanel.get();
        }

        return content;
    }
}
