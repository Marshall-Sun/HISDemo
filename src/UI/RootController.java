package UI;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class RootController {
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab DelNumUIPage;//foo
    @FXML
    private DelNumUI delNumUIPageController = new DelNumUI();

    @FXML
    private Tab AddNumUIPage;//bar
    @FXML
    private AddNumUI addNumUIPageController = new AddNumUI();

    @FXML
    public void initialize() {
        tabPane.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {
            if (newValue == DelNumUIPage) {
                System.out.println("DelNumUIPage");
            } else if (newValue == AddNumUIPage) {
                System.out.println("AddNumUIPage");
            }
        });
    }

    @FXML
    private void delNumInitialize() {
        delNumUIPageController.loadData();
    }

    private void addNumInitialize() {
        addNumUIPageController.loadData();
    }

}
