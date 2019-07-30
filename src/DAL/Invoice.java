package DAL;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Invoice implements Serializable {
    private int id;
    private double price;
    private Patient patient;
    private String department;
    private String doctor;
    private boolean status = false;
    private Date time = new Date();

    public Invoice(int id, double price, Patient patient, String department, String doctor) {
        this.id = id;
        this.price = price;
        this.patient = patient;
        this.department = department;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getRecordNum() {
        return patient.getRecordNum();
    }

    public ObservableValue<Integer> getRecordNumValue() {
        return new SimpleIntegerProperty(patient.getRecordNum()).asObject();
    }

    public ObservableValue<Integer> getInvoiceNumValue() {
        return new SimpleIntegerProperty(id).asObject();
    }

    public StringProperty getNameValue() {
        return new SimpleStringProperty(patient.getName());
    }

    public StringProperty getDateValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        return new SimpleStringProperty(sdf.format(time));
    }

    public StringProperty getDepartmentValue() {
        return new SimpleStringProperty(department);
    }

    public StringProperty getDoctorValue() {
        return new SimpleStringProperty(doctor);
    }

    public StringProperty getStatusValue() {
        if (status) {
            return new SimpleStringProperty("已就诊");
        } else {
            return new SimpleStringProperty("未就诊");
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
