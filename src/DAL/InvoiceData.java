package DAL;

import java.io.*;
import java.util.LinkedList;

public class InvoiceData {
    private static InvoiceData ourInstance = new InvoiceData();

    public static InvoiceData getInstance() {
        return ourInstance;
    }

    private InvoiceData() {
        loadInvoice();
    }

    private LinkedList<Invoice> invoiceList = new LinkedList<>();

    public void addInvoice(Invoice invoice) {
        invoiceList.add(invoice);
        writeInvoice();
        loadInvoice();
    }

    public void delInvoice(int index) {
        invoiceList.remove(index);
        writeInvoice();
        loadInvoice();
    }

    public Invoice getInvoice(int index) {
        return invoiceList.get(index);
    }

    public LinkedList<Invoice> getInvoiceList() {
        invoiceList = new LinkedList<>();
        loadInvoice();
        return invoiceList;
    }

    public int getListSize() {
        return invoiceList.size();
    }

    private void writeInvoice() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("Invoice.txt");
            oos = new ObjectOutputStream(fos);

            while (!this.invoiceList.isEmpty()) {
                oos.writeObject(this.invoiceList.pollFirst());
            }
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadInvoice() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Invoice.txt");
            ois = new ObjectInputStream(fis);

            Object c;
            while ((c = ois.readObject()) != null) {
                Invoice c1 = (Invoice) c;
                this.invoiceList.add(c1);
            }
        } catch (EOFException e) {
            System.err.println("读取发票信息...");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
