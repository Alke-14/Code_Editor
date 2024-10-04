module gallery.code_editor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens gallery.code_editor to javafx.fxml;
    exports gallery.code_editor;
}