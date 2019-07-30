package DAL;

import java.io.Serializable;
import java.util.LinkedList;

public class Department implements Serializable {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private LinkedList<Doctor> doctorList = new LinkedList<>();

    public void addDoctor(Doctor doctor) {
        doctorList.add(doctor);
    }

    public Doctor getDoctor(int index) {
        return doctorList.get(index);
    }

    public int getListSize() {
        return doctorList.size();
    }

    public String[] getDoctorList() {
        String[] list = new String[getListSize()];
        for (int i = 0; i < getListSize(); i++) {
            list[i] = getDoctor(i).getName();
        }
        return list;
    }
}
