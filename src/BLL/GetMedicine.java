package BLL;

public class GetMedicine {
    private static GetMedicine ourInstance = new GetMedicine();

    public static GetMedicine getInstance() {
        return ourInstance;
    }

    private GetMedicine() {
    }
}
