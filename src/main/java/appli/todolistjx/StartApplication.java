package appli.todolistjx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class StartApplication extends Application {
    private static Stage maStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("loginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("TodoList !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(String fxml , String nomScene) {
        maStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource(fxml+".fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            maStage.setTitle(nomScene);
            maStage.setScene(scene);
            maStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}