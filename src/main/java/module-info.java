module gallery.code_editor {
    requires javafx.controls;
    requires javafx.fxml;


    opens gallery.code_editor to javafx.fxml;
    exports gallery.code_editor;
}