import java.util.*;

// Class to represent the user's Bank Account
class BankAccount {
    private double balance;
    private String accountHolder;

    public BankAccount(String accountHolder) {
        this.balance = 0.0;  // default balance
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}

// Interface for ATM operations
interface AtmOperationInterf {
    void viewBalance();
    void withdrawAmount(double withdrawAmount);
    void depositAmount(double depositAmount);
    void viewMiniStatement();
    void transferAmount(double amount, BankAccount toAccount);
}

// Implementation of ATM operations (ATM Machine)
class AtmOperationImpl implements AtmOperationInterf {
    private BankAccount account;
    private Map<Double, String> ministmt = new LinkedHashMap<>();

    public AtmOperationImpl(BankAccount account) {
        this.account = account;
    }

    @Override
    public void viewBalance() {
        System.out.println("Available Balance is : " + account.getBalance());
    }

    @Override
    public void withdrawAmount(double withdrawAmount) {
        if (withdrawAmount <= 0) {
            System.out.println("Invalid amount! Please enter a positive number.");
            return;
        }
        if (withdrawAmount % 500 == 0) {
            if (withdrawAmount <= account.getBalance()) {
                ministmt.put(withdrawAmount, " Amount Withdrawn");
                System.out.println("Collect the Cash: " + withdrawAmount);
                account.setBalance(account.getBalance() - withdrawAmount);
                viewBalance();
            } else {
                System.out.println("Insufficient Balance !!");
            }
        } else {
            System.out.println("Please enter the amount in multiples of 500");
        }
    }

    @Override
    public void depositAmount(double depositAmount) {
        if (depositAmount <= 0) {
            System.out.println("Invalid deposit amount! Please enter a positive number.");
            return;
        }
        ministmt.put(depositAmount, " Amount Deposited");
        System.out.println(depositAmount + " Deposited Successfully !!");
        account.setBalance(account.getBalance() + depositAmount);
        viewBalance();
    }

    @Override
    public void viewMiniStatement() {
        if (ministmt.isEmpty()) {
            System.out.println("No transactions yet!");
        } else {
            System.out.println("----- Mini Statement -----");
            for (Map.Entry<Double, String> m : ministmt.entrySet()) {
                System.out.println(m.getKey() + " " + m.getValue());
            }
        }
    }

    @Override
    public void transferAmount(double amount, BankAccount toAccount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Please enter a positive number.");
            return;
        }
        if (amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            ministmt.put(amount, " Amount Transferred to " + toAccount.getAccountHolder());
            System.out.println("Transfer successful! Amount transferred: " + amount);
            viewBalance();
        } else {
            System.out.println("Insufficient Balance to transfer!");
        }
    }
}

// Main class for User Interface
public class ATMInterface {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Hardcoded ATM number & PIN
        int atmNumber = 12345;
        int atmPin = 123;

        // Create primary Bank Account object
        BankAccount account = new BankAccount("Primary Account");
        AtmOperationImpl op = new AtmOperationImpl(account);

        // Simulated recipient account for transfer
        BankAccount recipientAccount = new BankAccount("Recipient Account");

        System.out.println("Welcome to ATM Machine !!!");
        System.out.print("Enter ATM Number : ");
        int enteredNumber = in.nextInt();
        System.out.print("Enter Pin: ");
        int enteredPin = in.nextInt();

        if ((atmNumber == enteredNumber) && (atmPin == enteredPin)) {
            while (true) {
                System.out.println("\n===== ATM Menu =====");
                System.out.println("1. View Available Balance");
                System.out.println("2. Withdraw Amount");
                System.out.println("3. Deposit Amount");
                System.out.println("4. View Mini-statement");
                System.out.println("5. Transfer Amount");
                System.out.println("6. Exit");
                System.out.print("Enter Choice: ");
                int choice = in.nextInt();

                switch (choice) {
                    case 1:
                        op.viewBalance();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = in.nextDouble();
                        op.withdrawAmount(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = in.nextDouble();
                        op.depositAmount(depositAmount);
                        break;
                    case 4:
                        op.viewMiniStatement();
                        break;
                    case 5:
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = in.nextDouble();
                        op.transferAmount(transferAmount, recipientAccount);
                        break;
                    case 6:
                        System.out.println("Collect your ATM Card\nThank you for using ATM Machine!!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please enter a correct choice.");
                }
            }
        } else {
            System.out.println("Incorrect ATM Number or PIN");
            System.exit(0);
        }
    }
}
