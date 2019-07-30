package DAL;

import java.io.*;
import java.util.LinkedList;

public class MedicineData {

    private LinkedList<Medicine> medicineList;

    private static MedicineData ourInstance = new MedicineData();

    public static MedicineData getInstance() {
        return ourInstance;
    }

    private MedicineData() {
    }

    public void addMedicine(Medicine medicine) {
        medicineList.add(medicine);
    }

    public Medicine getMedicine(int index) {
        return medicineList.get(index);
    }

    public int getListSize() {
        return medicineList.size();
    }

    public void writeMedicine() {
        //定义一个文件输出流对象FileOutputStream和对象输出流对象ObjectOutputStream
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //实例化上面定义的io流对象
            fos = new FileOutputStream("Medicine.txt");
            oos = new ObjectOutputStream(fos);

            //使用处理流ObjectOutputStream的writeObject方法来进行序列化
            while (!this.medicineList.isEmpty()) {
                oos.writeObject(this.medicineList.pollFirst());
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

    public void loadMedicine() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Medicine.txt");
            ois = new ObjectInputStream(fis);

            Object c;
            while ((c = ois.readObject()) != null) {
                Medicine c1 = (Medicine) c;
                this.medicineList.add(c1);
            }
        } catch (EOFException e) {
            System.err.println("用户账号信息读取完毕");
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
