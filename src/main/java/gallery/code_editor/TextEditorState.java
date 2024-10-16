package gallery.code_editor;

import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class TextEditorState {
    private Stack<Character> state;

    public TextEditorState() {
        state = new Stack<>();
    }
    public Stack<Character> getState() {
        return state;
    }
    public void setState(Stack<Character> state) {
        this.state = state;
    }

    public void input(TextArea area) {
        area.setOnKeyTyped(e -> {
            String input = e.getCharacter();

            if (!input.isEmpty() && input.charAt(0) != '\b') {
                char edit = input.charAt(0);
                state.push(edit);
                System.out.println("Action Added: " + edit);

            }
        });

        area.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.BACK_SPACE) {
                if (!state.isEmpty()) {
                    char removedChar = state.pop();
                    System.out.println("Action Removed: " + removedChar);
                }
            }
        });
    }
}
