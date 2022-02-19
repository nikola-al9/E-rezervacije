//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sample;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application {
    public Program() {
    }

    public void start(Stage stage) throws IOException {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("view/login.fxml"));
        Scene scene = new Scene(root, 370.0D, 250.0D);
        stage.setTitle("Prijava");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
