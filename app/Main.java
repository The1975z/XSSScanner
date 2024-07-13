package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import scanner.XSSScanner;

public class Main extends Application 
{

    @Override
    public void start(Stage primaryStage) {
        Label title = new Label("XSS Scanner");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.GREEN);

        Label subtitle = new Label("Coded by The1975");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        subtitle.setTextFill(Color.WHITE);

        TextField urlInput = new TextField();
        urlInput.setPromptText("Enter URL");

        Button scanButton = new Button("Scan");
        scanButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        scanButton.setStyle("-fx-background-color: #00FF00; -fx-text-fill: #000000;");

        scanButton.setOnAction(e -> {
            String targetSite = urlInput.getText();
            if (!targetSite.isEmpty()) 
            {
                XSSScanner.xssInj(targetSite);
            } 
            else
            {
                System.out.println("URL cannot be empty!");
            }
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #000000; -fx-padding: 20;");
        layout.getChildren().addAll(title, subtitle, urlInput, scanButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("XSS Scanner");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
