package DAL;

import java.io.File;

public class Serialization {
    public static void main(String[] args) {
        delete("Patient.txt");
        PatientData patientData = PatientData.getInstance();
        Patient xm = new Patient("小明", "男", 100001, 18, 101);
        patientData.addPatient(xm);
        Patient xh = new Patient("小红", "女", 100002, 17, 102);
        patientData.addPatient(xh);

        delete("Account.txt");
        AccountData accountData = AccountData.getInstance();
        accountData.addAccount(new Account("br", "0"));
        accountData.addAccount(new Account("ys", "0"));
        accountData.addAccount(new Account("hg", "0"));

        delete("Department.txt");
        DepartmentData departmentData = DepartmentData.getInstance();
        Department bone = new Department("骨科");
        bone.addDoctor(new Doctor("骨科李白", 1));
        bone.addDoctor(new Doctor("骨科扁鹊", 2));
        Department eye = new Department("眼科");
        eye.addDoctor(new Doctor("眼科华佗", 1));
        eye.addDoctor(new Doctor("眼科杜甫", 2));

        departmentData.addDepartment(bone);
        departmentData.addDepartment(eye);

        delete("Invoice.txt");
        InvoiceData invoiceData = InvoiceData.getInstance();
        invoiceData.addInvoice(new Invoice(2001, 10, xm, "骨科", "骨科李白"));
        invoiceData.addInvoice(new Invoice(2002, 10, xm, "骨科", "骨科扁鹊"));
        invoiceData.addInvoice(new Invoice(2003, 20, xh, "眼科", "眼科华佗"));
        invoiceData.addInvoice(new Invoice(2004, 20, xh, "眼科", "眼科杜甫"));
    }

    public static void delete(String name) {
        File f = new File(name);
        if (f.exists()) {
            System.out.println(name + "文件已存在，删除...");
            f.delete();
        }
    }
}
