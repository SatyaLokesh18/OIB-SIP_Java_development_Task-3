import java.util.*;

class BankAccount {
    static void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("___________________________");
        System.out.println("Please enter your name:");
        ATM.customerName = sc.nextLine();
        System.out.println("Please create a username:");
        String username = sc.nextLine();
        System.out.println("Please create a password:");
        String password = sc.nextLine();
        System.out.println("Please enter your Account number:");
        ATM.accountNumber = sc.nextLine();
        System.out.println("REGISTRATION SUCCESSFUL!");
        System.out.println("----------------------------");
        ATM.showOptions();

        while (true) {
            displayOptions(ATM.customerName);
            int choice = sc.nextInt();
            if (choice == 1) {
                login(username, password);
                break;
            } else if (choice == 2) {
                System.exit(0);
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    static void displayOptions(String name) {
        System.out.println("Welcome " + name + "!");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }

    static void login(String username, String password) {
        Scanner sc = new Scanner(System.in);
        System.out.println("___________________________");
        System.out.println("Please enter your username:");
        String inputUser = sc.nextLine();
        System.out.println("Please enter your password:");
        String inputPass = sc.nextLine();

        if (inputUser.equals(username) && inputPass.equals(password)) {
            System.out.println("Login Successful!");
            ATM.showOptions();
        } else {
            System.out.println("Invalid credentials, please try again.");
            login(username, password);
        }
    }
}

class Transactions {
    static void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("__________________________");
        System.out.println("Enter amount to withdraw:");
        int amount = sc.nextInt();
        if (amount <= ATM.balance) {
            ATM.balance -= amount;
            System.out.println("Amount Rs " + amount + "/- withdrawn successfully");
            System.out.println("____________________________");
            ATM.history.add("Withdraw: " + amount);
        } else {
            System.out.println("Insufficient balance to withdraw the cash");
            System.out.println("____________________________");
        }
        ATM.showOptions();
    }

    static void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.print("Enter amount to deposit: ");
        int amount = sc.nextInt();
        ATM.updateBalance(amount);
        ATM.history.add("Deposit: " + amount);
        System.out.println("Amount Rs." + amount + "/- deposited successfully!");
        System.out.println("________________________________");
        ATM.showOptions();
    }

    static void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the recipient's name:");
        String recipient = sc.nextLine();
        System.out.println("Enter the recipient's account number:");
        int accNumber = sc.nextInt();
        System.out.println("Enter the amount to transfer:");
        int amount = sc.nextInt();
        if (amount <= ATM.balance) {
            ATM.balance -= amount;
            ATM.history.add("Transfer: " + amount + " to " + recipient);
            System.out.println("Amount Rs." + amount + "/- transferred successfully");
            System.out.println("____________________________");
        } else {
            System.out.println("Insufficient balance to transfer the cash");
            System.out.println("____________________________");
        }
        ATM.showOptions();
    }
}

class CheckBalance {
    static void displayBalance() {
        System.out.println("___________________________");
        System.out.println("Available balance: " + ATM.balance);
        System.out.println("___________________________");
        ATM.showOptions();
    }
}

class TransactionHistory {
    static void displayHistory() {
        System.out.println("_____________________");
        System.out.println("Transaction History:");
        if (!ATM.history.isEmpty()) {
            for (String record : ATM.history) {
                System.out.println(record);
            }
        } else {
            System.out.println("Your account is empty");
        }
        System.out.println("_____________________");
        ATM.showOptions();
    }
}

public class ATM {
    public static String customerName;
    public static int balance = 0;
    public static String accountNumber;
    public static List<String> history = new ArrayList<>();

    static void updateBalance(int amount) {
        balance += amount;
    }

    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\033[H\033[2J");
        System.out.println("WELCOME TO THE ATM INTERFACE");
        System.out.println("__________________________");
        System.out.println("Select an option:");
        System.out.println("1. Register");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        if (choice == 1) {
            BankAccount.register();
        } else if (choice == 2) {
            System.exit(0);
        } else {
            System.out.println("Please select a valid option:");
            mainMenu();
        }
    }

    static void showOptions() {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME " + ATM.customerName + "! TO THE ATM SYSTEM");
        System.out.println("_______________________");
        System.out.println("Select an option:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer");
        System.out.println("4. Check Balance");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Transactions.withdraw();
                break;
            case 2:
                Transactions.deposit();
                break;
            case 3:
                Transactions.transfer();
                break;
            case 4:
                CheckBalance.displayBalance();
                break;
            case 5:
                TransactionHistory.displayHistory();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
                showOptions();
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
