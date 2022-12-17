package org.Menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application  {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root, 582,404);
        Image icon = new Image("icon.jpg");
        stage.getIcons().add(icon);
        stage.setTitle("Tourist Trips");
        stage.setScene(scene);
        stage.show();
    }
    public void Launch(){
        launch();
    }
}