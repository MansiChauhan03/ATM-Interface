import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }
}



public class ATMGUI extends JFrame {
    private BankAccount account;

    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton checkBalanceButton;

    public ATMGUI(BankAccount account) {
        this.account = account;

        // Set up the frame
        setTitle("ATM");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Initialize components
        balanceLabel = new JLabel("Balance: Rs." + account.getBalance());
        amountField = new JTextField();
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        checkBalanceButton = new JButton("Check Balance");

        // Add components to the frame
        add(new JLabel("Enter amount:"));
        add(amountField);
        add(withdrawButton);
        add(depositButton);
        add(checkBalanceButton);
        add(balanceLabel);

        // Add action listeners
        withdrawButton.addActionListener(new WithdrawListener());
        depositButton.addActionListener(new DepositListener());
        checkBalanceButton.addActionListener(new CheckBalanceListener());
    }

    private class WithdrawListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Enter a valid amount.");
                } else if (amount > account.getBalance()) {
                    JOptionPane.showMessageDialog(null, "Insufficient balance.");
                } else {
                    account.withdraw(amount);
                    balanceLabel.setText("Balance: Rs." + account.getBalance());
                    JOptionPane.showMessageDialog(null, "Withdraw successful.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Enter a valid number.");
            }
        }
    }

    private class DepositListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Enter a valid amount.");
                } else {
                    account.deposit(amount);
                    balanceLabel.setText("Balance: Rs." + account.getBalance());
                    JOptionPane.showMessageDialog(null, "Deposit successful.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Enter a valid number.");
            }
        }
    }

    private class CheckBalanceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            balanceLabel.setText("Balance: Rs." + account.getBalance());
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0); // Initial balance of $1000
        ATMGUI atmGUI = new ATMGUI(account);
        atmGUI.setVisible(true);
    }
}
