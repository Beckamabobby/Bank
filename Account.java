import java.lang.NumberFormatException;
public class Account {
    String name;
    String password;
    String[][] history = new String[5][1];
//    public Account(String name, String password, double shmoney) {
//        this.name = name;
//        this.password = password;
//        this.shmoney = shmoney;
//    }
    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private void editMonee(double input) {
        Main.monee[Main.getIndex(name)] = input;
    }

    private double getMonee() {
        return Main.monee[Main.getIndex(name)];
    }
//    public String toString() {
//        String out = "";
//        out += "\nname: " + name;
//        out += "\nmoney: " + shmoney;
//        out += "\npassword: " + password + "\n";
//        return out;
//    }
    // true: quit, false: re-login
    public boolean sequence() {
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("check/deposit/withdraw/transfer/history/logout/quit");
            String in = Main.scanner.nextLine();
            switch (in) {
                case "check":
                    check();
                    break;
                case "deposit":
                    deposit();
                    break;
                case "withdraw":
                    withdraw();
                    break;
                case "transfer":
                    transfer();
                    break;
                case "history":
                    history();
                    break;
                case "quit":
                    return true;
                case "logout":
                    return false;
                default:
                    System.out.println("That is not a valid action");
            }
        }
    }
    public void check() {
        double display = Math.round(getMonee()*100.0)/100.0;
        System.out.println("You have $" + display);
    }
    public void deposit() {
        while (true) {
            System.out.println("How much money do you want to deposit?");
            String in = Main.scanner.nextLine();
            if (in.equals("quit")) {
                return;
            }
            try {
                double change = Double.parseDouble(in);
                if (change < 0) {
                    System.out.println("You can't deposit a negative amount");
                    continue;
                }
                String[] hist = new String[3];
                hist[0] = "Deposited";
                hist[1] = "$"+change;
                hist[2] = getMonee() + " -> " + (getMonee()+change);
                addHistory(hist);
                editMonee(getMonee() + change);
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
            if (in.equals("quit")) {
                return;
            }
            try {
                double change = Double.parseDouble(in);
                if (change < 0) {
                    System.out.println("You can't withdraw a negative amount");
                    continue;
                }
                if (change > getMonee()) {
                    System.out.println("You can't withdraw more money than you have");
                    continue;
                }
                String[] hist = new String[4];
                hist[0] = "Withdrew";
                hist[1] = "$"+change;
                hist[2] = getMonee() + " -> " + (getMonee() - change);
                addHistory(hist);
                editMonee(getMonee() - change);
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("That is not a number");
            }
        }
    }
    public void transfer() {
        int index;
        String target;
        while (true) {
            System.out.println("Who do you want to transfer money to?");
            String in = Main.scanner.nextLine();
            if (in.equals("quit")) {
                return;
            }
            if (in.equals(name)) {
                System.out.println("You can't send money to yourself");
                continue;
            }
            Integer check = Main.getIndex(in);
            if (check == null) {
                System.out.println("That is not a valid username");
                continue;
            }
            index = check;
            target = in;
            break;
        }
        while (true) {
            System.out.println("How much do you want to transfer");
            String in = Main.scanner.nextLine();
            if (in.equals("quit")) {
                return;
            }
            try {
                double amount = Double.parseDouble(in);
                if (amount < 0) {
                    System.out.println("You cannot transfer negative money");
                    continue;
                }
                if (amount > getMonee()) {
                    System.out.println("You cannot transfer more money than you have");
                    continue;
                }
                String[] hist = new String[5];
                hist[0] = "Transfered";
                hist[1] = "$"+amount;
                hist[2] = "to " + target;
                hist[3] = getMonee() + " -> " + (getMonee()-amount);
                hist[4] = Main.monee[index] + " -> " + (Main.monee[index] + amount);
                addHistory(hist);
                String[] otherHist = new String[4];
                otherHist[0] = "Recieved";
                otherHist[1] = "$"+amount;
                otherHist[2] = "From " + name;
                otherHist[3] = Main.monee[index] + " -> " + (Main.monee[index] + amount);
                Main.accounts.get(index).addHistory(otherHist);
                Main.monee[index] += amount;
                editMonee(getMonee() - amount);
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("That is not a number");
            }
        }
    }
    public void addHistory(String[] add) {
        history[4] = history[3];
        history[3] = history[2];
        history[2] = history[1];
        history[1] = history[0];
        history[0] = add;
    }
    public void history() {
        for (String[] hist: history) {
            for (String item: hist) {
                System.out.print(item+" ");
            }
            System.out.println();
        }
    }
}
