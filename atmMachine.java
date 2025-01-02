import java.util.Scanner;


class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance = balance -  amount;
            System.out.println("Withdrew: " + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance for this withdrawal!");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void showMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }

    public void checkBalance() {
        System.out.println("Current Balance: " + account.getBalance());
    }

    public void depositMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter deposit amount: ");
        double amount = sc.nextDouble();
        account.deposit(amount);
    }

    public void withdrawMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter withdrawal amount: ");
        double amount = sc.nextDouble();
        account.withdraw(amount);
    }
}

// Main class to run the ATM interface
public class atmMachine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankAccount userAccount = new BankAccount(1000.0);  

        ATM atm = new ATM(userAccount);

        boolean exit = false;

        while (!exit) {
            atm.showMenu();
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    atm.depositMoney();
                    break;
                case 3:
                    atm.withdrawMoney();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you for using the ATM!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }

        sc.close();
    }
}

