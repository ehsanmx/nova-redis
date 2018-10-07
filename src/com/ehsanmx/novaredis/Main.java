package com.ehsanmx.novaredis;

import com.ehsanmx.novaredis.view.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewFactory factory = new ViewFactory();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/view/layout.fxml"));

        loader.setController(factory.createMainController());
        Parent root = (Parent)loader.load();

        primaryStage.setTitle("NovaRedis 1.0");
        Scene scene = new Scene(root, 700, 500);
        scene.setUserData(loader);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
