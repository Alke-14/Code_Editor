package gallery.code_editor;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.util.Arrays;

public class TextEditorState {
    public TextArea area;
    public String state;
    public String snapshot;
    public ActionManager actionManager;


    public TextEditorState(TextArea area) {
        this.area = area;
//        this.state = "";
//        this.snapshot = "";
        this.actionManager = new ActionManager(this);


        area.textProperty().addListener((observableValue, oldText, newText) -> {
            if (!actionManager.undoInProgress) {
                actionManager.redo.clear();
                state = newText;
                snapshot = oldText;
                System.out.println("undo stack: " + Arrays.toString(actionManager.undo.toArray()));
                System.out.println("redo stack: " + Arrays.toString(actionManager.redo.toArray()));
            }

        });

    }
}