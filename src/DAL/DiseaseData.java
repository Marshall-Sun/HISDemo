package DAL;

import java.io.*;
import java.util.LinkedList;

public class DiseaseData {
    private LinkedList<Disease> diseaseList;

    private static DiseaseData ourInstance = new DiseaseData();

    public static DiseaseData getInstance() {
        return ourInstance;
    }

    private DiseaseData() {
    }

    public void addDisease(Disease disease) {
        diseaseList.add(disease);
    }

    public Disease getDisease(int index) {
        return diseaseList.get(index);
    }

    public int getListSize() {
        return diseaseList.size();
    }

    public void writeDisease() {
        //定义一个文件输出流对象FileOutputStream和对象输出流对象ObjectOutputStream
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //实例化上面定义的io流对象
            fos = new FileOutputStream("Disease.txt");
            oos = new ObjectOutputStream(fos);

            //使用处理流ObjectOutputStream的writeObject方法来进行序列化
            while (!this.diseaseList.isEmpty()) {
                oos.writeObject(this.diseaseList.pollFirst());
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

    public void loadDisease() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Disease.txt");
            ois = new ObjectInputStream(fis);

            Object c;
            while ((c = ois.readObject()) != null) {
                Disease c1 = (Disease) c;
                this.diseaseList.add(c1);
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
