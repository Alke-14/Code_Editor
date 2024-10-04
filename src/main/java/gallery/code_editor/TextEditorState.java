package gallery.code_editor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.awt.*;

public class TextEditorState {
    //Pass in stuff loolllz
    area.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
            // this will run whenever text is changed
            System.out.println(area.getText());
        }
    });

//    public char textState(String text) {
//        for (int i = 0; i < text.length(); i++) {
//            char newState = text.charAt(i);
//            System.out.println(newState);
//        }
//        return 0;
//    };
}
