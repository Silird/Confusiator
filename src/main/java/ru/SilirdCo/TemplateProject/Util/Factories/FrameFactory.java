package ru.SilirdCo.TemplateProject.Util.Factories;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.TemplateProject.Frames.EmptyPanel;
import ru.SilirdCo.TemplateProject.Frames.MainFrameController;
import ru.SilirdCo.TemplateProject.Util.ExceptionHandler;

import java.io.IOException;

public class FrameFactory {
    private final static Logger logger = LoggerFactory.getLogger(FrameFactory.class);

    private static FrameFactory instance;

    public static FrameFactory getInstance() {
        if (instance == null) {
            instance = new FrameFactory();
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
}
