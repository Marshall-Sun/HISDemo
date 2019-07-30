package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
// TODO: 2019/7/26 刷新按钮
public class RootLayout extends Application{
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new StackPane());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RootLayout.fxml"));
            scene.setRoot(loader.load());
//            RootController controller = loader.getController();
//            controller.initialize();

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
