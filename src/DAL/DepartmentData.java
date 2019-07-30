package DAL;

import java.io.*;
import java.util.LinkedList;

public class DepartmentData {

    private LinkedList<Department> departmentList = new LinkedList<>();

    private static DepartmentData ourInstance = new DepartmentData();

    public static DepartmentData getInstance() {
        return ourInstance;
    }

    private DepartmentData() {
        loadDepartment();
    }

    public void addDepartment(Department department) {
        departmentList = new LinkedList<>();
        loadDepartment();
        departmentList.add(department);
        writeDepartment();
    }

    public Department getDepartment(int index) {
        return departmentList.get(index);
    }

    public int getListSize() {
        return departmentList.size();
    }

    public String[] getDepartmentList() {
        String[] list = new String[getListSize()];
        for (int i = 0; i < getListSize(); i++) {
            list[i] = getDepartment(i).getName();
        }
        return list;
    }

    public void writeDepartment() {
        //定义一个文件输出流对象FileOutputStream和对象输出流对象ObjectOutputStream
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //实例化上面定义的io流对象
            fos = new FileOutputStream("Department.txt");
            oos = new ObjectOutputStream(fos);

            //使用处理流ObjectOutputStream的writeObject方法来进行序列化
            while (!this.departmentList.isEmpty()) {
                oos.writeObject(this.departmentList.pollFirst());
            }
            //强制io流输出到文件
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

    private void loadDepartment() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Department.txt");
            ois = new ObjectInputStream(fis);

            Object c;
            while ((c = ois.readObject()) != null) {
                Department c1 = (Department) c;
                this.departmentList.add(c1);
            }
        } catch (EOFException e) {
            System.err.println("读取科室信息...\n读取医生信息...");
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
