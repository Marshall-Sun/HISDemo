package DAL;

import java.io.*;
import java.util.LinkedList;

public class AccountData {
    private LinkedList<Account> accountList = new LinkedList<>();

    private static AccountData ourInstance = new AccountData();

    public static AccountData getInstance() {
        return ourInstance;
    }

    private AccountData() {
        loadAccount();
    }

    public void addAccount(Account account) {
        accountList = new LinkedList<>();
        loadAccount();
        accountList.add(account);
        writeAccount();
    }

    public Account getAccount(int index) {
        return accountList.get(index);
    }

    public int getListSize() {
        return accountList.size();
    }

    public void writeAccount() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("Account.txt");
            oos = new ObjectOutputStream(fos);

            while (!this.accountList.isEmpty()) {
                oos.writeObject(this.accountList.pollFirst());
            }
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

    private void loadAccount() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Account.txt");
            ois = new ObjectInputStream(fis);

            Object c;
            while ((c = ois.readObject()) != null) {
                Account c1 = (Account) c;
                this.accountList.add(c1);
            }
        } catch (EOFException e) {
            System.err.println("读取用户账号信息...");
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
