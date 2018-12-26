package ru.SilirdCo.TemplateProject.Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.TemplateProject.Frames.JavaFXLaunch;
import ru.SilirdCo.TemplateProject.Util.ExceptionHandler;

public class LaunchView {
    private static final Logger logger = LoggerFactory.getLogger(LaunchView.class);

    public static boolean launch() {
        logger.info("\n\nЗапуск визуальной формы\n\n");

        try {
            logger.info("\n\nЗапуск формы..\n\n");
            JavaFXLaunch.show(new String[0]);
        }
        catch (Exception ex) {
            ExceptionHandler.handle(logger, ex);

            return false;
        }

        return true;
    }
}
