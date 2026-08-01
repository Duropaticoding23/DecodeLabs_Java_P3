import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize a mock BankAccount object (Account No: 1001, Initial Balance: $1500.00, PIN: 4321)
        BankAccount myAccount = new BankAccount(1001, 1500.0, 4321);

        // Inject the account dependency into the ATM user interface[cite: 1]
        ATM atmSystem = new ATM(myAccount);

        // Start the application loop[cite: 1]
        atmSystem.run();
    }
}

// ==========================================
// 1. BankAccount Class (Data Vault & Business Logic)[cite: 1]
// ==========================================
class BankAccount {
    private int accountNumber;
    private double balance;
    private int pin;

    public BankAccount(int accountNumber, double balance, int pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// ==========================================
// 2. ATM Class (User Interface & Error Handling)[cite: 1]
// ==========================================
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== WELCOME TO DECODELABS ATM ===");
        
        if (!authenticateUser()) {
            System.out.println("Authentication failed. Exiting application.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getValidatedIntInput("Choose an option (1-4): ");

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleWithdrawal();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using DecodeLabs ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option between 1 and 4.");
            }
            System.out.println();
        }
    }

    private boolean authenticateUser() {
        System.out.print("Enter your 4-digit PIN: ");
        int enteredPin = getValidatedIntInput("");
        if (account.validatePin(enteredPin)) {
            System.out.println("PIN Verified Successfully!\n");
            return true;
        }
        System.out.println("Incorrect PIN.");
        return false;
    }

    private void displayMenu() {
        System.out.println("--- ATM Main Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Cash");
        System.out.println("3. Withdraw Cash");
        System.out.println("4. Exit");
    }

    private void checkBalance() {
        System.out.printf("Current Balance: $%.2f%n", account.getBalance());
    }

    private void handleDeposit() {
        double amount = getValidatedDoubleInput("Enter amount to deposit: $");
        if (account.deposit(amount)) {
            System.out.printf("Successfully deposited: $%.2f%n", amount);
            checkBalance();
        } else {
            System.out.println("Deposit failed. Amount must be greater than zero.");
        }
    }

    private void handleWithdrawal() {
        double amount = getValidatedDoubleInput("Enter amount to withdraw: $");
        if (account.withdraw(amount)) {
            System.out.printf("Successfully withdrew: $%.2f%n", amount);
            checkBalance();
        } else {
            System.out.println("Withdrawal failed. Insufficient funds or invalid amount.");
        }
    }

    // Error handling for integer inputs to prevent crashes[cite: 1]
    private int getValidatedIntInput(String prompt) {
        if (!prompt.isEmpty()) System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input format. Please enter a valid number: ");
            scanner.next(); 
        }
        return scanner.nextInt();
    }

    // Error handling for double/amount inputs to prevent crashes[cite: 1]
    private double getValidatedDoubleInput(String prompt) {
        if (!prompt.isEmpty()) System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input format. Please enter a valid numeric amount: ");
            scanner.next(); 
        }
        double value = scanner.nextDouble();
        while (value < 0) {
            System.out.print("Amount cannot be negative. Please enter again: ");
            value = scanner.nextDouble();
        }
        return value;
    }
}