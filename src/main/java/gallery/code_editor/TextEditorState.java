package gallery.code_editor;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.util.Arrays;
import java.util.Stack;

public class TextEditorState {
    public TextArea area;
    public String state;
    public String snapshot;
    public Stack<String> undo;
    public Stack<String> redo;
    private boolean undoInProgress;


    public TextEditorState(TextArea area){
        this.area = area;
        this.state = "";
        this.snapshot = "";
        this.undo = new Stack<String>();
        this.redo = new Stack<String>();
        this.undoInProgress = false;



        area.textProperty().addListener(e -> {
            if(!undoInProgress) {

                state = area.getText();
                snapshot = area.getText();
                undo.push(snapshot);
                System.out.println("undo stack: " + Arrays.toString(undo.toArray()));
                System.out.println(state);
            };
        });
        area.setText(state);
    }

    public void undo(){
        undoInProgress = true;
        redo.push(undo.pop());
        area.setText(undo.peek());
        System.out.println("redo stack: " + Arrays.toString(redo.toArray()));
        undoInProgress = false;
    }
//            new ChangeListener<String>() {
//            @Override
//            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
//                // this will run whenever text is changed
//                state = area.getText();
//                area.setText(state);
//                System.out.println("Old Value: " + oldValue);
//                System.out.println("New Value: " + newValue);
//            }}



//    public char textState(String text) {
//        for (int i = 0; i < text.length(); i++) {
//            char newState = text.charAt(i);
//            System.out.println(newState);
//        }
//        return 0;
//    };
}
