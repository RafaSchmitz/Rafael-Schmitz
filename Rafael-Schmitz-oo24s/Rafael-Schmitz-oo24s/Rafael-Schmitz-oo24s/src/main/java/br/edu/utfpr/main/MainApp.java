package br.edu.utfpr.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = (VBox) FXMLLoader.load(
                this.getClass()
                        .getResource("/fxml/FXMLTLPrincipal.fxml")
        );
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/bootstrap.css");

        primaryStage.setTitle("Controle de reservas e cadastros");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
