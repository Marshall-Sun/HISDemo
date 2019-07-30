package UI;

import BLL.AddNum;
import DAL.Department;
import DAL.DepartmentData;
import DAL.Patient;
import DAL.PatientData;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
// TODO: 2019/7/25 多次操作后发票号为0

public class AddNumUI extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout = new BorderPane();

    private double price;

    @FXML
    private TextField nameField;
    @FXML
    private TextField recordNumField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField personIDField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField invoiceField;
    @FXML
    private DatePicker checkInDatePicker;

    @FXML
    private ComboBox<String> cboGender = new ComboBox<>();
    @FXML
    private ComboBox<String> cboCash = new ComboBox<>();
    @FXML
    private ComboBox<String> cboLevel = new ComboBox<>();
    @FXML
    private ComboBox<String> cboDepartment = new ComboBox<>();
    @FXML
    private ComboBox<String> cboDoctor = new ComboBox<>();
    @FXML
    private ComboBox<String> cboPayment = new ComboBox<>();

    @FXML
    private Button ok;
    @FXML
    private CheckBox checkBox;

    private AddNum addNum;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("患者挂号");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginUI.class.getResource("AddNumUI.fxml"));
            AnchorPane LoginUI = loader.load();

            rootLayout.setCenter(LoginUI);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void initialize() {
        loadData();
        price = 0.0;
        checkBox.setSelected(false);
        priceField.setText("0.00");

        checkInDatePicker.setDisable(true);
        cboGender.setDisable(true);
        cboDoctor.setDisable(true);
        nameField.setDisable(true);
        ageField.setDisable(true);
        personIDField.setDisable(true);
        addressField.setDisable(true);

        String[] genderList = {"男", "女"};
        initializeComboBox(cboGender, genderList);
        String[] cashList = {"自费", "医保"};
        initializeComboBox(cboCash, cashList);
        String[] paymentList = {"现金", "银行卡"};
        initializeComboBox(cboPayment, paymentList);
        String[] levelList = {"普通门诊", "急诊"};
        initializeComboBox(cboLevel, levelList);
        handleDepartment();
        handleLevel();
        handleCheckBox();

        checkInDatePicker.setValue(LocalDate.now());
        ageField.textProperty().addListener((observableValue, s, s2) -> handleAgeChanged());
        recordNumField.textProperty().addListener((observableValue, s, s2) -> handleRecordNumBox(s2));

        ok.disableProperty().bind(
                nameField.textProperty().isEmpty()
                        .or(recordNumField.textProperty().isEmpty())
                        .or(ageField.textProperty().isEmpty())
                        .or(personIDField.textProperty().isEmpty())
                        .or(cboGender.valueProperty().isNull())
                        .or(cboCash.valueProperty().isNull())
                        .or(cboLevel.valueProperty().isNull())
                        .or(cboDepartment.valueProperty().isNull())
                        .or(cboDoctor.valueProperty().isNull())
                        .or(cboPayment.valueProperty().isNull())
                        .or(checkInDatePicker.valueProperty().isNull()));
    }

    public void loadData() {
        addNum = AddNum.getInstance();
    }

    private void initializeComboBox(ComboBox cbo, String[] strList) {
        cbo.getItems().clear();
        ObservableList<String> dbTypeList = FXCollections.observableArrayList();
        dbTypeList.addAll(Arrays.asList(strList));
        //noinspection unchecked
        cbo.setItems(dbTypeList);
    }

    @FXML
    public void handleDepartment() {
        DepartmentData departments = DepartmentData.getInstance();
        String[] departmentList = departments.getDepartmentList();
        initializeComboBox(cboDepartment, departmentList);

        cboDepartment.setOnAction(event -> {
            if (cboDepartment.getValue() != null) {
                cboDoctor.setDisable(false);
                for (int i = 0; i < departments.getListSize(); i++) {
                    if (cboDepartment.getValue().equals(departmentList[i])) {
                        Department curDepartment = departments.getDepartment(i);
                        initializeComboBox(cboDoctor, curDepartment.getDoctorList());
                    }
                }
            }
        });
    }

    @FXML
    public void handleLevel() {
        cboLevel.setOnAction(event -> {
            if (cboLevel.getValue() != null) {
                if (cboLevel.getValue().equals("普通门诊")) {
                    price = 20.00;
                }
                if (cboLevel.getValue().equals("急诊")) {
                    price = 50.00;
                }
            }
            if (checkBox.isSelected()) {
                price++;
            }
            priceField.setText(price + "");
        });
    }

    @FXML
    public void handleCheckBox() {
        EventHandler<ActionEvent> handler = event -> {
            if (checkBox.isSelected()) {
                price++;
                recordNumField.setDisable(true);
                recordNumField.setText(addNum.getNewNum() + "");

                checkInDatePicker.setDisable(false);
                cboGender.setDisable(false);
                nameField.setDisable(false);
                ageField.setDisable(false);
                personIDField.setDisable(false);
                addressField.setDisable(false);
            } else {
                recordNumField.setDisable(false);
                recordNumField.setText("");
                price = 0;
                cboLevel.setValue(null);

                checkInDatePicker.setDisable(true);
                cboGender.setDisable(true);
                nameField.setDisable(true);
                ageField.setDisable(true);
                personIDField.setDisable(true);
                addressField.setDisable(true);
            }
            priceField.setText(price + "");
        };
        checkBox.setOnAction(handler);
    }

    @FXML
    private void handleRecordNumBox(String s2) {
        if (s2 != null && s2.length() != 0) {
            try {
                int recordNum = Integer.parseInt(s2);
                if (s2.length() == 6) {
                    Patient curPatient = addNum.checkNum(recordNum);
                    if (curPatient != null) {
                        setPatientData(curPatient);
                    } else {
                        if (!checkBox.isSelected()) {
                            throw new NumberFormatException();
                        }
                    }
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("输入错误");
                alert.setHeaderText(null);
                alert.setContentText("您输入的病历号不在数据库中！");

                alert.showAndWait();
            }
        }
    }

    private void setPatientData(Patient curPatient) {
        nameField.setText(curPatient.getName());
        ageField.setText(curPatient.getAge() + "");
        personIDField.setText(curPatient.getPersonID() + "");
        if (curPatient.getAddress() != null) {
            addressField.setText(curPatient.getAddress());
        }
        if (curPatient.getGender().equals("男")) {
            cboGender.getSelectionModel().select(0);
        } else {
            cboGender.getSelectionModel().select(1);
        }
    }

    @FXML
    public void handleAgeChanged() {
        if (ageField.getText() != null && ageField.getText().length() != 0) {
            try {
                int age = Integer.parseInt(ageField.getText());
                if (age <= 0) {
                    throw new NumberFormatException();
                }

                checkInDatePicker.setValue(LocalDate.now().minusYears(age));

            } catch (Exception e) {
                ageField.setText("");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("输入错误");
                alert.setHeaderText(null);
                alert.setContentText("请重新输入正确数据！");

                alert.showAndWait();
            }
        }
    }

    @FXML
    public void handleDateChanged() {
        LocalDate date = checkInDatePicker.getValue();
        LocalDate now = LocalDate.now();
        Period age = Period.between(date, now);
        ageField.setText(age.getYears() + "");
    }

    @FXML
    private void handleClean() {
        price = 0.0;
        priceField.setText(price + "");
        nameField.setText("");
        addressField.setText("");
        ageField.setText("");
        personIDField.setText("");
        cboDoctor.getItems().clear();
        cboDoctor.setDisable(true);
        cboGender.setValue(null);
        cboPayment.setValue(null);
        cboDepartment.setValue(null);
        cboCash.setValue(null);
        cboLevel.setValue(null);
        if (!checkBox.isSelected()) {
            recordNumField.setText("");
        }
    }

    @FXML
    private void handleOk() {
        try {
            if (addNum.isFull(cboDepartment.getValue(), cboDoctor.getValue())) {
                throw new NoSuchFieldException();
            } else {
                Patient curPatient;
                if (checkBox.isSelected()) {
                    if (addressField.getText() == null || addressField.getText().length() == 0) {
                        curPatient = addNum.addStatus(nameField.getText(), cboGender.getValue(),
                                Integer.parseInt(recordNumField.getText()),
                                Integer.parseInt(ageField.getText()),
                                Integer.parseInt(personIDField.getText()));
                    } else {
                        curPatient = addNum.addStatusAddress(nameField.getText(), cboGender.getValue(),
                                Integer.parseInt(recordNumField.getText()),
                                Integer.parseInt(ageField.getText()),
                                Integer.parseInt(personIDField.getText()),
                                addressField.getText());
                    }
                    PatientData.getInstance().addPatient(curPatient);
                } else {
                    curPatient = addNum.checkNum(Integer.parseInt(recordNumField.getText()));
                }
                invoiceField.setText(addNum.printInvoice(curPatient,
                        Double.parseDouble(priceField.getText()),
                        cboDepartment.getValue(), cboDoctor.getValue()) + "");
                checkBox.setSelected(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("挂号成功");
                alert.setHeaderText(null);
                alert.setContentText("请保管好您的发票");

                alert.showAndWait();
            }

        } catch (NoSuchFieldException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("人数挂满");
            alert.setHeaderText(null);
            alert.setContentText("请更换医生！");

            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("输入错误");
            alert.setHeaderText(null);
            alert.setContentText("请输入正确的数据！");

            alert.showAndWait();
        }
    }
}
