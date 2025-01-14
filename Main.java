import java.util.Scanner;
import java.util.ArrayList;
class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Account> accounts = new ArrayList<Account>();
    public static void main(String[] args) {
        sequence();
        scanner.close();
    }
    public static void sequence() {
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("login/new account/quit");
            String in = scanner.nextLine();
            switch (in) {
                case "login":
                    if (login()) {
                        break;
                    }
                case "new account":
                    newAccount();
                case "quit":
                    break;
            }
        }
    }
    public static void newAccount() {
        while (true) {
            System.out.println("What do you want your username to be?");
            String name = scanner.nextLine();
            for (Account account: accounts) {
                if (account.name == name) {
                    System.out.println("That name is taken");
                    continue;
                }
            }
            System.out.println("What do you want your password to be?");
            accounts.add(new Account(name, scanner.nextLine()));
        }
    }
    public static boolean login() {
        while (true) {
            System.out.println("Username:");
            String name = scanner.nextLine();
            System.out.println("Password:");
            String pass = scanner.nextLine();
            Integer index = getIndex(name);
            if (index == null) {
                System.out.println("There is a mistake in your username or password");
                continue;
            }
            if (pass != accounts.get(index).password) {
                System.out.println("There is a mistake in your username or password");
                continue;
            }
            return accounts.get(index).sequence();
        }
    }
    public static Integer getIndex(String name) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).name == name) {
                return i;
            }
        }
        return null;
    }
}