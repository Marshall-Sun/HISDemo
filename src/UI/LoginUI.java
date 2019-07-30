package UI;

import BLL.Login;
import DAL.Account;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginUI extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    @FXML
    private TextField nameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button ok;

    private Stage dialogStage = new Stage();

    private boolean okClicked = false;

    private Account user;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("登陆页面");

        initRootLayout();

        showLoginUI();
    }

    @FXML
    private void initialize() {
        ok.disableProperty().bind(
                nameField.textProperty().isEmpty()
                        .or(passwordField.textProperty().isEmpty()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            user = new Account(nameField.getText(), passwordField.getText());
            okClicked = true;
            dialogStage.close();
            System.out.println("登录成功！");
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "密码为空!\n";
        }
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "用户名为空!\n";
        } else {
            errorMessage = Login.getInstance().loginStatus(nameField.getText(), passwordField.getText());
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入错误");
            alert.setHeaderText(null);
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }

    public void initRootLayout() {
        rootLayout = new BorderPane();

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showLoginUI() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginUI.class.getResource("LoginUI.fxml"));
            AnchorPane LoginUI = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(LoginUI);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
