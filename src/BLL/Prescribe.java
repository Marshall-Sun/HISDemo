package BLL;

public class Prescribe {
    private static Prescribe ourInstance = new Prescribe();

    public static Prescribe getInstance() {
        return ourInstance;
    }

    private Prescribe() {
    }
}
