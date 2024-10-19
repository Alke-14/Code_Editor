package gallery.code_editor;

import javafx.scene.control.TextArea;

public class TextEditorState {
    public TextArea area;
    public String state;
    public String snapshot;
    public ActionManager actionManager;

    public TextEditorState(TextArea area) {
        this.area = area;
        this.actionManager = new ActionManager(this);

        area.textProperty().addListener((observableValue, oldText, newText) -> {
            if (!actionManager.undoInProgress) {
                actionManager.redo.clear();
                state = newText;
                snapshot = oldText;
                actionManager.undo.push(snapshot);
            }
        });
    }
}