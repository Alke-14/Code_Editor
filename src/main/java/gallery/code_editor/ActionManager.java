package gallery.code_editor;

import java.util.Arrays;

// manages history of actions in textField
// should connect with TextEditorState as it has the main state
// history, and current text
public class ActionManager {
    public Stack<String> undo;
    public Stack<String> redo;
    public boolean undoInProgress;
    public TextEditorState state;

    public ActionManager(TextEditorState state) {
        this.undo = new Stack<String>(10);
        this.redo = new Stack<String>(10);
        this.undoInProgress = false;
        this.state = state;
    }

    /**
     * This method undoes the last action in a given text area.
     * If {@link #undo} is not empty, pop last value from {@link #undo},
     * push current text area value to {@link #redo}, then set text area to the popped value.
     *
     * @return void
     */
    public void undoAction() {
        System.out.println("undo stack b4: " + Arrays.toString(undo.toArray()));
        undoInProgress = true;

        if (!undo.isEmpty()) {
            System.out.println("undo pressed: ");
            String poppedValue = undo.pop();
            redo.push(state.area.getText());
            System.out.println("undo stack af: " + Arrays.toString(undo.toArray()));
            System.out.println("UNDO redo stack: " + Arrays.toString(redo.toArray()));
            state.area.setText(poppedValue);
        } else {
            System.out.println("Stack empty.");
        }
        undoInProgress = false;

    }

    /**
     * This method undoes the last 10 actions recursively in a given text area.
     *
     * @param times Number of times for function {@link #undoAction()} to execute
     * @return void
     */
    public void batchUndoAction(int times) {
        if (undo.isEmpty() || times == 0) {
            return;
        }
        undoAction();
        batchUndoAction(times - 1);
    }

    /**
     * This method redoes the last action in a given text area.
     * If {@link #redo} is not empty, pop last value from {@link #redo},
     * push current text area value to {@link #undo}, then set text area to the popped value.
     *
     * @return void
     */
    public void redoAction() {
        System.out.println("redo stack b4: " + Arrays.toString(redo.toArray()));

        undoInProgress = true;
        if (!redo.isEmpty()) {
            String poppedValue = redo.pop();
            undo.push(state.area.getText());
            state.area.setText(poppedValue);
            System.out.println("redo stack af: " + Arrays.toString(redo.toArray()));
        } else {
            System.out.println("stack empty");
        }
        undoInProgress = false;

    }
}
