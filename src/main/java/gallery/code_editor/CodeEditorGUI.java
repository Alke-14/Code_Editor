package gallery.code_editor;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValueBase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;


import java.io.*;
import java.util.Scanner;

public class CodeEditorGUI extends Application {
    private static final String FILE_NAME = "output.txt";

    @Override
    public void start(Stage stage) {
        TextArea area = new TextArea();
        TextEditorState state = new TextEditorState(area);
        area.setPromptText("Enter text");
        area.setPrefColumnCount(15);
        area.setPrefHeight(120);
        area.setPrefWidth(450);

        Button undo = new Button("Undo");
        Button batchUndo = new Button("Batch Undo");
        Button redo = new Button("Redo");
        Button batchRedo = new Button("Batch Redo");
        Button braces = new Button("Check Braces");
        Button open = new Button("Open");
        Button save = new Button("Save");
        Button exit = new Button("Exit");

        undo.setOnAction(e -> {
            state.actionManager.undoAction();
        });

        redo.setOnAction(e -> {
            state.actionManager.redoAction();
        });

        batchUndo.setOnAction(e -> {
            state.actionManager.batchUndoAction(10);
        });

        batchRedo.setOnAction(e -> {
            state.actionManager.batchRedoAction(10);
        });

        BraceChecker checker = new BraceChecker();

        Label savedText = new Label("Saved");
        braces.setOnAction(e -> {
            String text = area.getText();
            try {
                if (checker.checkBraces(text)) {
                    savedText.setText("Braces are paired");
                } else {
                    savedText.setText("Braces are not paired");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        open.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Text File");
            fileChooser.setInitialDirectory(new File("src\\main\\java\\gallery\\code_editor"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                System.out.println("File Opened");
                try {
                    Scanner scanner = new Scanner(selectedFile);
                    String text = "";
                    while(scanner.hasNextLine()){
                        text += scanner.nextLine() + "\n";
                    }
                    area.setText(text);
                    state.actionManager.undo.clear();
                    state.actionManager.redo.clear();
                }
                catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        save.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Text File");
            fileChooser.setInitialDirectory(new File("src\\main\\java\\gallery\\code_editor"));
            File file = fileChooser.showSaveDialog(new Stage());
            if(file != null){
                writeFile(file.getPath(), area.getText());
            }
        });

        exit.setOnAction(e -> stage.close());

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setAlignment(Pos.TOP_CENTER);
        buttonGrid.setPadding(new Insets(10));
        buttonGrid.add(undo, 0, 0);
        buttonGrid.add(batchUndo, 2, 0);
        buttonGrid.add(redo, 3, 0);
        buttonGrid.add(batchRedo, 4, 0);
        buttonGrid.add(braces, 5, 0);
        buttonGrid.add(open, 6, 0);
        buttonGrid.add(save, 7, 0);
        buttonGrid.add(exit, 8, 0);

        // Create a HBox for the TextField
        HBox textBox = new HBox();
        textBox.setSpacing(20);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(20, 50, 20, 50));
        textBox.getChildren().add(area);

        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(buttonGrid, textBox, savedText);

        // Create a scene with the combined layout
        Scene scene = new Scene(layout, 595, 395);
        stage.setTitle("Text Editor");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Logic to save file and display into scene
     **/
    public void writeFile(String filename, String content) {
        try (FileWriter write = new FileWriter(filename)) {
            write.write(content);
            System.out.println("File saved: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}