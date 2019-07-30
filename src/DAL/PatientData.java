package DAL;

import java.io.*;
import java.util.LinkedList;

public class PatientData {

    private static PatientData ourInstance = new PatientData();

    public static PatientData getInstance() {
        return ourInstance;
    }

    private PatientData() {
        loadPatient();
    }

    private LinkedList<Patient> patientList = new LinkedList<>();

    public void addPatient(Patient patient) {
        patientList = new LinkedList<>();
        loadPatient();
        patientList.add(patient);
        writePatient();
    }

    public Patient getPatient(int index) {
        return patientList.get(index);
    }

    public int getListSize() {
        return patientList.size();
    }

    public void writePatient() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("Patient.txt");
            oos = new ObjectOutputStream(fos);

            while (!this.patientList.isEmpty()) {
                oos.writeObject(this.patientList.pollFirst());
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

    private void loadPatient() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Patient.txt");
            ois = new ObjectInputStream(fis);

            Object c;
            while ((c = ois.readObject()) != null) {
                Patient c1 = (Patient) c;
                this.patientList.add(c1);
            }
        } catch (EOFException e) {
            System.err.println("读取病人信息...");
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
