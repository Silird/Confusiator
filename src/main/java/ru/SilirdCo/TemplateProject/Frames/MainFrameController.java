package ru.SilirdCo.TemplateProject.Frames;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.TemplateProject.Confuse.ConfuseException;
import ru.SilirdCo.TemplateProject.Confuse.ConfuseStarter;
import ru.SilirdCo.TemplateProject.Util.Factories.FrameFactory;
import ru.SilirdCo.TemplateProject.Util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class MainFrameController implements Initializable {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MainFrameController.class);

    @FXML
    private Button butBrowse;
    @FXML
    private TextField textPath;
    private FileTextField filePath;
    @FXML
    private TextField textDepth;
    private PositiveIntegerField intDepth;
    @FXML
    private Label labelTime;

    @FXML
    private Button butStart;
    @FXML
    private Button butInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filePath = new FileTextField(textPath);
        intDepth = new PositiveIntegerField(textDepth);
        intDepth.setInteger(0);

        initListeners();
    }

    private void initListeners() {
        intDepth.integerProperty().addListener(((observable, oldValue, newValue) -> {
            labelTime.setText(calculateFindTime(newValue));
        }));

        butBrowse.setOnAction(event -> {
            File file = FileUtil.openFile();
            filePath.setFile(file);
        });

        butInfo.setOnAction(event -> showInfo());

        butStart.setOnAction(event -> {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ВНИМАНИЕ");
            alert.setHeaderText("Вы уверены, что готовы начать пранк?");
            alert.setContentText("Разработчик и дистрибьютер не несёт ответственности за действия этой программы, все действия вы проводите на свой страх и риск.");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && (option.get() == ButtonType.OK)) {
                File file = filePath.getFile();
                int depth = intDepth.getInteger();

                ConfuseStarter confuseStarter = new ConfuseStarter(file, depth);
                CompletableFuture<Void> completeFuture = new CompletableFuture<>();
                FrameFactory.getInstance().getProgressFrame(confuseStarter.progressProperty(), completeFuture);
                CompletableFuture.runAsync(() -> {
                    try {
                        confuseStarter.start();
                    }
                    catch (IOException | ConfuseException ex) {
                        JavaFXLaunch.getInstance().sendWarn(ex.getMessage());
                    }

                    JavaFXLaunch.getInstance().sendInfo("======================================\n" +
                            "======================================\n" +
                            "ВНИМАНИЕ!\n" +
                            "======================================\n" +
                            "======================================\n" +
                            "Запомните ключ, по которому только вы сможете найти файл, иначе пранк может выйти из под контроля!\n" +
                            "Ключ поиска: " + confuseStarter.getKey());
                    completeFuture.complete(null);
                });
            }
        });
    }

    private static final int PROCLICK = 67;
    private String calculateFindTime(int depth) {
        long seconds = 1;
        for (int i = 0; i < depth; i++) {
            seconds += seconds*PROCLICK;
        }

        String time = "";
        long minutes = seconds/60;
        seconds = seconds%60;
        long hours = minutes/60;
        minutes = minutes%60;
        long days = hours/24;
        hours = hours%24;
        long years = days/365;
        days = days%365;

        if (minutes > 0) {
            if (hours > 0) {
                if (days > 0) {
                    if (years > 0) {
                        time += years + " лет(года/год) ";
                    }
                    time += days + " дней(дня/день) ";
                }
                time += hours + " часов(часа/час) ";
            }
            time += minutes + " минут(минуты/минута) ";
        }
        time += seconds + " секунд(секунды/секунда)";

        return time;
    }

    private void showInfo() {
        JavaFXLaunch.getInstance().sendInfo("Важное отступление, разработчик и дистрибьютер не несёт ответственности за действия этой программы, все действия вы проводите на свой страх и риск.\n" +
                "Эта программа чисто для пранков и розыгрышей своих знакомых, всё что вам надо это получить доступ к компьютеру вашей цели, запустить эту программу, ввести необходимые данные и произвести запуск. После этого можете наслаждаться последствием пранка, только осторожней, вас могут уебать.");
    }
}
