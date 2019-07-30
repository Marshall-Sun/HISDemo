package BLL;

public class Refund {
    private static Refund ourInstance = new Refund();

    public static Refund getInstance() {
        return ourInstance;
    }

    private Refund() {
    }
}
