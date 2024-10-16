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

    public void stringToChar(String s) {
        for (char c : s.toCharArray()) {
            state.push(c);
        }
    }
    public void input(TextArea area) {
        area.setOnKeyPressed(e -> {
            if (e.getCode().isLetterKey() || e.getCode().isDigitKey() || e.getCode() == KeyCode.SPACE) {
                String text = area.getText();
                if (!text.isEmpty()) {
                    char lastAction = text.charAt(text.length() - 1);
                    state.push(lastAction);
                    System.out.println("Last Action Saved: " + lastAction);
                }
            }
            if (e.getCode() == KeyCode.BACK_SPACE) {
                if (!state.isEmpty()) {
                    char removedChar = state.pop(); // Remove the last character from the stack
                    System.out.println("Character removed: " + removedChar);
                }
            }
        });
    }
}
