package demo.guidemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/demo/guidemo/hello-view.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        //Parent root = fxmlLoader.load();
        //Scene scene = new Scene(root);
        stage.setTitle("GUI Objects View");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}