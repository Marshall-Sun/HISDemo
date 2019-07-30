package BLL;

public class Fund {
    private static Fund ourInstance = new Fund();

    public static Fund getInstance() {
        return ourInstance;
    }

    private Fund() {
    }
}
