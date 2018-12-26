package ru.SilirdCo.TemplateProject.Frames;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.TemplateProject.Util.Factories.FrameFactory;

import java.util.Stack;

public class JavaFXLaunch extends Application {
    private static final Logger logger = LoggerFactory.getLogger(JavaFXLaunch.class);

    private static JavaFXLaunch instance;

    private Stage stage = null;
    private AnchorPane pane = null;
    private Node activeNode = null;
    private Stack<Node> stackFrames = new Stack<>();

    public static JavaFXLaunch getInstance() {
        if (instance == null) {
            logger.warn("Главная форма не инициализирована");
        }
        return instance;
    }

    public static void show(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;

        Node mainFrameNode = FrameFactory.getInstance()
                .getMainPanel();

        stage = primaryStage;
        pane = new AnchorPane();
        showWindow(mainFrameNode);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Confusiator");
        primaryStage.show();

        primaryStage.setOnCloseRequest(we -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void showWindow(Node node) {
        if (Platform.isFxApplicationThread()) {
            addNodeSave(node);
        }
        else {
            Platform.runLater(() -> addNodeSave(node));
        }
    }

    private void addNodeSave(Node node) {
        stackFrames.push(node);
        addNode(node);
    }

    public Stage getStage() {
        return stage;
    }

    private void addNode(Node node) {
        if ((pane == null) || (stage == null)) {
            logger.warn("Главная форма не инициализирована");
            return;
        }

        if ((node instanceof Region) && !stage.isMaximized()) {
            Region region = (Region) node;

            if (region.getWidth() == 0) {
                stage.setWidth(region.getPrefWidth());
            }
            else {
                stage.setWidth(region.getWidth() + 16);
            }

            if (region.getHeight() == 0) {
                stage.setHeight(region.getPrefHeight());
            }
            else {
                stage.setHeight(region.getHeight() + 39);
            }
        }

        AnchorPane.setBottomAnchor(node, 0D);
        AnchorPane.setLeftAnchor(node, 0D);
        AnchorPane.setRightAnchor(node, 0D);
        AnchorPane.setTopAnchor(node, 0D);

        pane.getChildren().clear();
        activeNode = node;
        pane.getChildren().add(node);
    }

    public void closeWindow() {
        if (stackFrames.peek() == activeNode) {
            stackFrames.pop();
            addNode(stackFrames.peek());
        }
    }
}
