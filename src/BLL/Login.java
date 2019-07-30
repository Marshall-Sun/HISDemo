package BLL;

import DAL.Account;
import DAL.AccountData;

public class Login {
    private static Login ourInstance = new Login();

    public static Login getInstance() {
        return ourInstance;
    }

    private Login() {
    }

    public String loginStatus(String name, String password) {
        AccountData accountData = AccountData.getInstance();
        String message = "";
        for (int i = 0; i < accountData.getListSize(); i++) {
            Account cache = accountData.getAccount(i);
            if (cache.getName().equals(name)) {
                if (cache.getPassword().equals(password)) {
                    return "";
                } else {
                    message = "密码错误!\n";
                }
            } else {
                message = "用户名错误!\n";
            }
        }
        return message;
    }
}
