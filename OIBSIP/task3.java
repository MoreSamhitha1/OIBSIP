import java.util.Scanner;

class BankAccount {          
    private double balance;    //balance attribute here is used to store the current amount

    public BankAccount(double balance) {     // here BankAccount is a constructor that initializes the balance.
        this.balance = balance;
    }
//THE FOLLOWING ARE THE METHODS FOR DEPOSIT,WITHDRAWL AND TRANSFER

    public boolean deposit(double amount) {   

//Here deposit(double amount) method adds the specified amount to the balance if it's positive, and returns true for success.

        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            System.out.println("Invalid amount for deposit.");
            return false;
        }
    }

    public boolean withdraw(double amount) {

//here withdraw(double amount) method will withdraws the amount if it's positive and less than or equal to the balance. Returns true for success.

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount for withdrawal.");
            return false;
        }
    }

    public boolean transfer(BankAccount recipient, double amount) {

//here transfer(BankAccount recipient, double amount)method transfers the amount from this account to the recipient account if it's positive and less than or equal to the balance. Returns true for success. 
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount for withdrawal.");
            return false;
        }
    }

//and then it also calls recipient.deposit(amount) to update the recipient's balance.It shows the current balance 

    public double checkBalance() {
        return balance;
    }
}

class ATM {

//here ATM is a class which simulates the user iterface of ATM

    private BankAccount userAccount; //userAccount is a attribute is a reference of account being used
    private Scanner scanner; //here scanner is to take the input

    public ATM(BankAccount userAccount) {  //this constructor will initialise the userAccount
        this.userAccount = userAccount;
        this.scanner = new Scanner(System.in);
    }

    public void mainMenu() {  

//this mainMenu()method displays a menu with options for checking balance, deposit, withdrawal, transfer, and exit. It prompts the user for a choice and performs the corresponding action using helper methods.

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

//These methods display prompts for user input (amount for deposit/withdrawal/transfer, recipient's balance for transfer).They call the corresponding methods on the userAccount object to perform the actual transaction.Based on the success of the operation, they display messages indicating success or failure.

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void checkBalance() {
        double balance = userAccount.checkBalance();
        System.out.println("Your balance is: Rs." + balance);
    }

    public void deposit() {
        System.out.print("Enter the amount to deposit: Rs.");
        double amount = scanner.nextDouble();
        if (userAccount.deposit(amount)) {
            System.out.println("Deposit successful.");
        }
    }

    public void withdraw() {
        System.out.print("Enter the amount to withdraw: Rs.");
        double amount = scanner.nextDouble();
        if (userAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
        }
    }

    public void transfer() {
        System.out.print("Enter the recipient account balance: Rs.");
        double recipientBalance = scanner.nextDouble();
        BankAccount recipientAccount = new BankAccount(recipientBalance);

        System.out.print("Enter the amount to transfer: Rs.");
        double amount = scanner.nextDouble();

        if (userAccount.transfer(recipientAccount, amount)) {
            System.out.println("Transfer successful.");
        }
    }
}

public class task3 {       //this is the actual class where serves the entry point of the program
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // creates object which having initial balance Rs.1000
        ATM atm = new ATM(account); //creating ATM object and passing BankAccount as arguments 
        atm.mainMenu();
    }
}