import java.util.Scanner;
import java.util.ArrayList;
class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Account> accounts = new ArrayList<Account>();
    public static void main(String[] args) {
        scanner.close();
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