package BLL;

import DAL.*;

public class AddNum {
    private PatientData patientData = PatientData.getInstance();
    private InvoiceData invoiceData = InvoiceData.getInstance();
    private DepartmentData departmentData = DepartmentData.getInstance();

    private static AddNum ourInstance = new AddNum();

    public static AddNum getInstance() {
        return ourInstance;
    }

    private AddNum() {
    }

    public Patient addStatus(String name, String gender, int recordNum, int age, int personID) {
        Patient cache = new Patient(name, gender, recordNum, age, personID);
        patientData.addPatient(cache);
        return cache;
    }

    public Patient addStatusAddress(String name, String gender, int recordNum,
                                    int age, int personID, String address) {
        Patient cache = new Patient(name, gender, recordNum, age, personID, address);
        patientData.addPatient(cache);
        return cache;
    }

    public int getNewNum() {
        int newNum = 0;
        for (int i = 0; i < patientData.getListSize(); i++) {
            Patient cache = patientData.getPatient(i);
            if (newNum <= cache.getRecordNum()) {
                newNum = cache.getRecordNum() + 1;
            }
        }
        return newNum;
    }

    public Patient checkNum(int input) {
        for (int i = 0; i < patientData.getListSize(); i++) {
            Patient cache = patientData.getPatient(i);
            if (input == cache.getRecordNum()) {
                return cache;
            }
        }
        return null;
    }

    public int printInvoice(Patient patient, double money, String department, String doctor) {
        int curID = 0;
//        invoiceData = InvoiceData.getInstance();
        for (int i = 0; i < invoiceData.getListSize(); i++) {
            if (curID <= invoiceData.getInvoice(i).getId()) {
                curID = invoiceData.getInvoice(i).getId() + 1;
            }
        }
        invoiceData.addInvoice(new Invoice(curID, money, patient, department, doctor));
        return curID;
    }

    public boolean isFull(String department, String doctor) {
        for (int i = 0; i < departmentData.getListSize(); i++) {
            Department cache = departmentData.getDepartment(i);
            if (cache.getName().equals(department)) {
                for (int j = 0; j < cache.getListSize(); j++) {
                    Doctor curDoctor = cache.getDoctor(j);
                    if (curDoctor.getName().equals(doctor)) {
                        if (curDoctor.getWatchAmount() <= 0) {
                            return true;
                        } else {
                            curDoctor.setWatchAmount(curDoctor.getWatchAmount() - 1);
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }
}
