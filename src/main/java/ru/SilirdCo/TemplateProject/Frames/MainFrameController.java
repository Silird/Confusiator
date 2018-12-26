package ru.SilirdCo.TemplateProject.Frames;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.TemplateProject.Util.ConfuseUtil;
import ru.SilirdCo.TemplateProject.Util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFrameController implements Initializable {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MainFrameController.class);

    @FXML
    private Button butTest;
    @FXML
    private Button butBrowse;
    @FXML
    private TextField textPath;
    private FileTextField filePath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filePath = new FileTextField(textPath);

        initListeners();
    }

    private void initListeners() {
        butBrowse.setOnAction(event -> {
            File file = FileUtil.openFile();
            filePath.setFile(file);
        });

        butTest.setOnAction(event -> {
            File file = filePath.getFile();
            if (file == null) {
                sendWarn("Файл для операции не выбран");
                return;
            }

            String shadeContent = ConfuseUtil.createShadeContent(file.length());
            File shadedFile = FileUtil.getSaveFile();
            try {
                FileUtil.writeText(shadedFile, shadeContent);
            }
            catch (FileNotFoundException ex) {
                sendError("Произошла ошибка записи файла");
                return;
            }

            sendInfo("Успех!");
        });
    }

    private void sendInfo(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, text);
        alert.showAndWait();
    }

    private void sendWarn(String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING, text);
        alert.showAndWait();
    }

    private void sendError(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR, text);
        alert.showAndWait();
    }
}
