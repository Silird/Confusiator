package ru.SilirdCo.TemplateProject.Frames;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileTextField {
    private final static Logger logger = LoggerFactory.getLogger(FileTextField.class);

    private TextField textField = null;

    private ObjectProperty<File> fileProp = null;

    public final ObjectProperty<File> fileProperty() {
        if (fileProp == null) {
            fileProp = new SimpleObjectProperty<>();
            fileProp.setValue(null);

        }
        return fileProp;
    }

    public FileTextField(TextField textField) {
        this.textField = textField;
        this.textField.setDisable(true);
        //initListeners();
    }

    /*
    private void initListeners() {
        if (textField != null) {

            textField.setAlignment(Pos.CENTER_RIGHT);

            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.equals("")) {
                    try {
                        Float result = Float.parseFloat(newValue);

                        fileProperty().setValue(VarUtils.roundFloat(result));
                    }
                    catch (NumberFormatException ex) {
                        textField.setText(oldValue);
                    }
                }
            });
        }
        else {
            logger.warn("Текстовое поле не назначено");
        }
    }
    */

    public File getFile() {
        return fileProperty().get();
    }

    public void setFile(File value) {
        fileProperty().set(value);
        if (value == null) {
            textField.setText("");
        }
        else {
            textField.setText(value.getAbsolutePath());
        }
    }
}
