import java.lang.NumberFormatException;
public class Account {
    String name;
    double shmoney = 0.0;
    String password;
    public Account(String name, String password, double shmoney) {
        this.name = name;
        this.password = password;
        this.shmoney = shmoney;
    }
    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public void check() {
        System.out.println("You have $" + shmoney);
    }
    public void deposit() {
        while (true) {
            System.out.println("How much money do you want to deposit?");
            String in = Main.scanner.nextLine();
            try {
                double change = Double.parseDouble(in);
                if (change < 0) {
                    System.out.println("You can't deposit a negative amount");
                    continue;
                }
                shmoney += change;
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("That is not a number");
            }
        }
    }
    public void withdraw() {
        while (true) {
            System.out.println("How much money do you want to withdraw?");
            String in = Main.scanner.nextLine();
            try {
                double change = Double.parseDouble(in);
                if (change < 0) {
                    System.out.println("You can't withdraw a negative amount");
                    continue;
                }
                if (change > shmoney) {
                    System.out.println("You can't withdraw more money than you have");
                    continue;
                }
                shmoney -= change;
            }
            catch (NumberFormatException e) {
                System.out.println("That is not a number");
            }
        }
    }
    public void transfer() {
        int index;
        while (true) {
            System.out.println("Who do you want to transfer money to?");
            String in = Main.scanner.nextLine();
            Integer check = Main.getIndex(in);
            if (check == null) {
                System.out.println("That is not a valid username");
                continue;
            }
            index = check;
            break;
        }
        while (true) {
            System.out.println("How much do you want to transfer");
            String in = Main.scanner.nextLine();
            try {
                int amount = Integer.parseInt(in);
                if (amount < 0) {
                    System.out.println("You cannot transfer negative money");
                    continue;
                }
                if (amount > shmoney) {
                    System.out.println("You cannot transfer more money than you have");
                    continue;
                }
                Main.accounts.get(index).shmoney += amount;
                shmoney -= amount;
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("That is not a number");
            }
        }
    }
}
