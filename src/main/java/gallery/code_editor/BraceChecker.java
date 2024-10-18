package gallery.code_editor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BraceChecker {
    private Stack<Character> braceStack;

    public BraceChecker() {
        this.braceStack = new Stack<>();
    }

    public boolean isPaired(String code) {
        for (char c : code.toCharArray()) {
            // Push opening braces onto the stack
            if (c == '(' || c == '{' || c == '[') {
                braceStack.push(c); // Push the opening brace onto the stack
            }
            // Handle closing braces
            else if (c == ')' || c == '}' || c == ']') {
                if (braceStack.isEmpty()) {
                    return false;
                }
                char top = braceStack.pop();

                // Check for matching pairs
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return braceStack.isEmpty();
    }

    public boolean checkBraces(String code) throws IOException {
        return isPaired(code);
    }
}
