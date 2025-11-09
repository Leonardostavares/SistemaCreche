package main.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CrecheFXApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Carrega o arquivo FXML principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            VBox root = loader.load();
            
            // Configura a cena
            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            
            // Configura a janela principal
            primaryStage.setTitle("Sistema de Creche - Gestão de Matrículas");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(1000);
            primaryStage.setMinHeight(700);
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao inicializar a aplicação JavaFX: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}