package BLL;

import DAL.Invoice;
import DAL.InvoiceData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DelNum {
    private InvoiceData invoiceData = InvoiceData.getInstance();
    private ObservableList<Invoice> invoiceList = FXCollections.observableArrayList();

    private static DelNum ourInstance = new DelNum();

    public static DelNum getInstance() {
        return ourInstance;
    }

    private DelNum() {
        invoiceList.addAll(invoiceData.getInvoiceList());
    }

    public ObservableList<Invoice> getInvoiceList() {
        invoiceList = FXCollections.observableArrayList();
        invoiceList.addAll(invoiceData.getInvoiceList());
        return invoiceList;
    }

    public void deleteInvoice(int index) {
        invoiceData.delInvoice(index);
    }

    public double getPrice(int index) {
        return invoiceList.get(index).getPrice();
    }

    public ObservableList<Invoice> search(int input) {
        ObservableList<Invoice> searchList = FXCollections.observableArrayList();
        for (Invoice item : invoiceList) {
            if (item.getRecordNum() == input) {
                searchList.add(item);
            }
        }
        return searchList;
    }
}
