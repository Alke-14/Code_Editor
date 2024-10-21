package gallery.code_editor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class BraceChecker {
    private Stack<Character> braceStack;

    public BraceChecker() {
        this.braceStack = new Stack<>();
    }

    public boolean isPaired(String code) {
        braceStack.clear();
        boolean isBalanced = true;

        for (char c : code.toCharArray()) {
            if(isBalanced){
                // Push opening braces onto the stack
                if (c == '(' || c == '{' || c == '[') {
                    braceStack.push(c); // Push the opening brace onto the stack
                }
                // Handle closing braces
                else if (c == ')' || c == '}' || c == ']') {
                    if (braceStack.isEmpty()) {
                        isBalanced = false;
                        break;
                    } else {
                        char top = braceStack.pop();
                        isBalanced = isDelimPaired(top, c);
                        if (!isDelimPaired(top, c)){
                            break;
                        }
                    }
                }
            }

        }
//        System.out.println(Arrays.toString(braceStack.toArray()));

        if(!braceStack.isEmpty()){
            isBalanced = false;
        }
        return isBalanced;
    }

    public boolean isDelimPaired(char openDelim, char closingDelim){
        switch (openDelim) {
            case '(':
                return closingDelim == ')';
            case '[':
                return closingDelim == ']';
            case '{':
                return closingDelim == '}';
            default:
                return false;
        }
    }

    public boolean checkBraces(String code) throws IOException {
        return isPaired(code);
    }
}
