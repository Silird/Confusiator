package ru.SilirdCo.TemplateProject.Util;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import ru.SilirdCo.TemplateProject.Frames.JavaFXLaunch;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    private static FileChooser fileChooser;
    static {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    }
    private static Window window;

    public static File openFile() {
        File file = fileChooser.showOpenDialog(getWindow());
        if (file != null) {
            fileChooser.setInitialDirectory(file.getParentFile());
        }
        return file;
    }

    public static File getSaveFile() {
        File file = fileChooser.showSaveDialog(getWindow());
        if (file != null) {
            fileChooser.setInitialDirectory(file.getParentFile());
        }
        return file;
    }

    public static void writeText(File file, String content) throws FileNotFoundException {
        //PrintWriter обеспечит возможности записи в файл
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), StandardCharsets.UTF_8))) {
            //Записываем текст в файл
            out.print(content);
        }
    }

    private static Window getWindow() {
        if (window == null) {
            window = JavaFXLaunch.getInstance().getStage();
        }
        return window;
    }
}
