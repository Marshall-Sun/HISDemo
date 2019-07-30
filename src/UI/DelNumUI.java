package UI;

import BLL.DelNum;
import DAL.Invoice;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DelNumUI extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout = new BorderPane();

    private DelNum delNum;

    @FXML
    private TableView<Invoice> invoiceTableView;
    @FXML
    private TableColumn<Invoice, Integer> recordNumColumn;
    @FXML
    private TableColumn<Invoice, Integer> invoiceNumColumn;
    @FXML
    private TableColumn<Invoice, String> nameColumn;
    @FXML
    private TableColumn<Invoice, String> dateColumn;
    @FXML
    private TableColumn<Invoice, String> departmentColumn;
    @FXML
    private TableColumn<Invoice, String> doctorColumn;
    @FXML
    private TableColumn<Invoice, String> statusColumn;

    @FXML
    private Button del;

    @FXML
    private TextField searchField;

    @FXML
    public void initialize() {
        loadData();
        invoiceTableView.setItems(delNum.getInvoiceList());

        recordNumColumn.setCellValueFactory(
                cellData -> cellData.getValue().getRecordNumValue());
        invoiceNumColumn.setCellValueFactory(
                cellData -> cellData.getValue().getInvoiceNumValue());
        nameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNameValue());
        dateColumn.setCellValueFactory(
                cellData -> cellData.getValue().getDateValue());
        departmentColumn.setCellValueFactory(
                cellData -> cellData.getValue().getDepartmentValue());
        doctorColumn.setCellValueFactory(
                cellData -> cellData.getValue().getDoctorValue());
        statusColumn.setCellValueFactory(
                cellData -> cellData.getValue().getStatusValue());

        del.disableProperty().bind(
                Bindings.isEmpty(invoiceTableView.getSelectionModel().getSelectedItems()));

        searchField.textProperty().addListener((observableValue, s, s2) -> {
            if (searchField.getText() == null || searchField.getText().length() == 0) {
                handleRefreshButton();
            }
        });

//         Listen for selection changes and show the person details when changed.
//        invoiceTableView.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> {
//                    if (!newValue.isStatus()) {
//                        del.setDisable(true);
//                    }
//                });
    }

    public void loadData() {
        delNum = DelNum.getInstance();
    }

    @FXML
    private void handleDeletePerson() {
        invoiceTableView.setItems(delNum.getInvoiceList());

        int selectedIndex = invoiceTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            double price = delNum.getPrice(selectedIndex);
            delNum.deleteInvoice(selectedIndex);
            invoiceTableView.getItems().remove(selectedIndex);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("退号成功");
            alert.setHeaderText(null);
            alert.setContentText("应退还挂号费：" + price + "元");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleSearchButton() {
        if (searchField.getText() != null && searchField.getText().length() != 0) {
            try {
                int input = Integer.parseInt(searchField.getText());
                if (input <= 0) {
                    throw new NumberFormatException();
                }
                invoiceTableView.setItems(delNum.search(input));

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("输入错误");
                alert.setHeaderText(null);
                alert.setContentText("请重新输入正确数据！");

                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleRefreshButton() {
        invoiceTableView.setItems(delNum.getInvoiceList());
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginUI.class.getResource("DelNumUI.fxml"));
            AnchorPane LoginUI = loader.load();

            rootLayout.setCenter(LoginUI);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
