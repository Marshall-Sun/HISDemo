package DAL;

import java.io.Serializable;

public class Patient implements Serializable {
    private String name;
    private String gender;

    private int recordNum;//自动生成
    private int age;
    private int personID;
    private String address;

    public Patient(String name, String gender, int recordNum, int age, int personID) {
        this.name = name;
        this.gender = gender;
        this.recordNum = recordNum;
        this.age = age;
        this.personID = personID;
    }

    public Patient(String name, String gender, int recordNum, int age, int personID, String address) {
        this.name = name;
        this.gender = gender;
        this.recordNum = recordNum;
        this.age = age;
        this.personID = personID;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getRecordNum() {
        return recordNum;
    }

    public int getAge() {
        return age;
    }

    public int getPersonID() {
        return personID;
    }

    public String getAddress() {
        return address;
    }

}
