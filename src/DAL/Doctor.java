package DAL;

import java.io.Serializable;

public class Doctor implements Serializable {
    private String name;
    private int watchAmount;

    public Doctor(String name, int watchAmount) {
        this.name = name;
        this.watchAmount = watchAmount;
    }

    public String getName() {
        return name;
    }

    public int getWatchAmount() {
        return watchAmount;
    }

    public void setWatchAmount(int watchAmount) {
        this.watchAmount = watchAmount;
    }
}
