package gallery.code_editor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class CodeEditorGUI extends Application {
    private TextArea area;
    private static final String FILE_NAME = "output.txt";
    @Override
    public void start(Stage stage) {
        TextArea area = new TextArea();
        area.setPromptText("Enter text");
        area.setPrefColumnCount(15);
        area.setPrefHeight(120);
        area.setPrefWidth(450);

        Button undo = new Button("Undo");
        Button redo = new Button("Redo");
        Button braces = new Button("Check Braces");
        Button open = new Button("Open");
        Button save = new Button("Save");
        Button exit = new Button("Exit");

        BraceChecker checker = new BraceChecker();
        TextEditorState state = new TextEditorState();

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
            String fileContent = readFile(FILE_NAME);
            showFile(fileContent);
        });

        save.setOnAction(e -> {
            String text = area.getText();
            savedText.setText("Saved changes: " + text);
            writeFile(FILE_NAME, text);
            for (int i = 0  ; i < text.length(); i++) {
                char newState = text.charAt(i);
                writeFile(FILE_NAME, text);
                System.out.println("Saved in " + FILE_NAME + ": "+ newState);
            }
        });

        exit.setOnAction(e -> stage.close());

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

        // Create a HBox for the TextField
        HBox textBox = new HBox();
        textBox.setSpacing(20);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(20, 50, 20, 50));
        textBox.getChildren().add(area);

        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(buttonGrid, textBox,savedText);

        // Create a scene with the combined layout
        Scene scene = new Scene(layout, 595, 395);
        stage.setTitle("Text Editor");
        stage.setScene(scene);
        stage.show();

        state.input(area);
    }

    public TextArea getTextArea() {
        return area;
    }
/**Logic to save file and display into scene**/
    public void writeFile(String filename, String content) {
        try (FileWriter write = new FileWriter(filename)) {
            write.write(content);
        } catch (IOException e) {
            System.err.println("Error writing to file " + e.getMessage());
        }
    }

    private String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return content.toString();
    }

    private void showFile (String fileContent) {
        TextArea content = new TextArea(fileContent);
        content.setEditable(false);

        Button exitTxt = new Button("Exit");


        Stage newStage = new Stage();
        newStage.setTitle("Opened File");

        exitTxt.setOnAction(e -> newStage.close());

        HBox savedBox = new HBox();
        savedBox.setSpacing(20);
        savedBox.setAlignment(Pos.BOTTOM_LEFT);
        savedBox.setPadding(new Insets(20));
        savedBox.getChildren().add(exitTxt);

        VBox textBox = new VBox();
        textBox.getChildren().addAll(content,savedBox);

        Scene scene = new Scene(textBox, 400, 300);
        newStage.setScene(scene);

        newStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}