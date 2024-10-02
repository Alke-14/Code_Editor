package gallery.code_editor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CodeEditorGUI extends Application {
    @Override
    public void start(Stage stage) {
        // Create a TextField
        TextField area = new TextField();
        area.setPrefColumnCount(15);
        area.setPrefHeight(120);
        area.setPrefWidth(450);

        // Create buttons
        GridPane buttonGrid = getGridPane();

        // Create a HBox for the TextField
        HBox textBox = new HBox();
        textBox.setSpacing(20);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(20, 50, 20, 50));
        textBox.getChildren().add(area);

        // Create a VBox to hold both the text field and the button grid
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(buttonGrid, textBox);

        // Create a scene with the combined layout
        Scene scene = new Scene(layout, 595, 395);
        stage.setTitle("Text Editor");
        stage.setScene(scene);
        stage.show();
    }

    private static GridPane getGridPane() {
        Button undo = new Button("Undo");
        Button redo = new Button("Redo");
        Button braces = new Button("Check Braces");
        Button open = new Button("Open");
        Button save = new Button("Save");
        Button exit = new Button("Exit");

        // Create a GridPane for the buttons
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setAlignment(Pos.TOP_CENTER);
        buttonGrid.setPadding(new Insets(10));
        buttonGrid.add(undo, 0, 0);
        buttonGrid.add(redo, 1, 0);
        buttonGrid.add(braces, 2, 0);
        buttonGrid.add(open, 3, 0);
        buttonGrid.add(save, 4, 0);
        buttonGrid.add(exit, 5, 0);
        return buttonGrid;
    }

    public static void main(String[] args) {
        launch();
    }
}