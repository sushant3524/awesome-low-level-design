import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ATM {
    private final BankingService bankingService;
    private final CashDispenser cashDispenser;
    private static final AtomicLong transactionCounter = new AtomicLong(0);

    public ATM(BankingService bankingService, CashDispenser cashDispenser) {
        this.bankingService = bankingService;
        this.cashDispenser = cashDispenser;
    }

    public void authenticateUser(Card card) {
        // Authenticate user using card and PIN
        // ...
    }

    public double checkBalance(String accountNumber) {
        Account account = bankingService.getAccount(accountNumber);
        return account.getBalance();
    }

    public synchronized void withdrawCash(String accountNumber, double amount) {
        Account account = bankingService.getAccount(accountNumber);
        Transaction transaction = new WithdrawalTransaction(generateTransactionId(), account, amount);
        bankingService.processTransaction(transaction);
        cashDispenser.dispenseCash((int) amount);
    }

    public synchronized void depositCash(String accountNumber, double amount) {
        Account account = bankingService.getAccount(accountNumber);
        Transaction transaction = new DepositTransaction(generateTransactionId(), account, amount);
        bankingService.processTransaction(transaction);
    }

    private String generateTransactionId() {
        // Generate a unique transaction ID
        long transactionNumber = transactionCounter.incrementAndGet();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "TXN" + timestamp + String.format("%010d", transactionNumber);
    }

    public class BankingService {
        private final Map<String, Account> accounts = new ConcurrentHashMap<>();

        public void createAccount(String accountNumber, double initialBalance) {
            accounts.put(accountNumber, new Account(accountNumber, initialBalance));
        }

        public Account getAccount(String accountNumber) {
            return accounts.get(accountNumber);
        }

        public void processTransaction(Transaction transaction) {
            transaction.execute();
        }
    }

    public class CashDispenser {
        private int cashAvailable;

        public CashDispenser(int initialCash) {
            this.cashAvailable = initialCash;
        }

        public synchronized void dispenseCash(int amount) {
            if (amount > cashAvailable) {
                throw new IllegalArgumentException("Insufficient cash available in the ATM.");
            }
            cashAvailable -= amount;
            System.out.println("Cash dispensed: " + amount);
        }
    }

    public class DepositTransaction extends Transaction {
        public DepositTransaction(String transactionId, Account account, double amount) {
            super(transactionId, account, amount);
        }

        @Override
        public void execute() {
            account.credit(amount);
        }
    }

    public class WithdrawalTransaction extends Transaction {
        public WithdrawalTransaction(String transactionId, Account account, double amount) {
            super(transactionId, account, amount);
        }

        @Override
        public void execute() {
            account.debit(amount);
        }
    }

    public abstract class Transaction {
        protected final String transactionId;
        protected final Account account;
        protected final double amount;

        public Transaction(String transactionId, Account account, double amount) {
            this.transactionId = transactionId;
            this.account = account;
            this.amount = amount;
        }

        public abstract void execute();
    }

    public class Account {
        private final String accountNumber;
        private double balance;
        List<Transaction> transactionList;

        public Account(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public double getBalance() {
            return balance;
        }

        public void debit(double amount) {
            balance -= amount;
        }

        public void credit(double amount) {
            balance += amount;
        }
    }

    public class Card {
        private final String cardNumber;
        private final String pin;

        public Card(String cardNumber, String pin) {
            this.cardNumber = cardNumber;
            this.pin = pin;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public String getPin() {
            return pin;
        }
    }

}