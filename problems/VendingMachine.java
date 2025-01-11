import java.util.Map;
import java.util.Objects;

public class VendingMachine {
    private static VendingMachine INSTANCE;
    private Inventory inventory;
    private Product selectedProduct;
    private int amountAdded;
    private VMState currState;

    private VendingMachine() {
        inventory = new Inventory();
        currState = VMState.IDLE;
    }

    public static VendingMachine getInstance() {
        if (INSTANCE != null) {
            INSTANCE = new VendingMachine();
        }
        return INSTANCE;
    }

    public void addAmount(int val) {
        amountAdded += val;
    }

    public void selectProduct(Product product) {
        if (currState == VMState.IDLE) {
            if (inventory.getCount(product) > 0) {
                selectedProduct = product;
                currState = VMState.TRANSACTIONAL;
                return;
            }
            throw new RuntimeException("Product Out of Stock");
        }
        throw new RuntimeException("Current state of Vending Machine is " + currState);
    }

    public void insertCoin(Coin coin) {
        if (currState != VMState.TRANSACTIONAL) {
            throw new RuntimeException("Current state of Vending Machine is " + currState);
        }
        amountAdded += coin.getValue();
        if (amountAdded >= selectedProduct.getPrice()) {
            currState = VMState.DISPENSE;
        }
    }

    public void insertNote(Cash cash) {
        if (currState != VMState.TRANSACTIONAL) {
            throw new RuntimeException("Current state of Vending Machine is " + currState);
        }
        amountAdded += cash.getValue();
        if (amountAdded >= selectedProduct.getPrice()) {
            currState = VMState.DISPENSE;
        }
    }

    public void dispenseProduct() {
        if (currState != VMState.DISPENSE) {
            throw new RuntimeException("Current state of Vending Machine is " + currState);
        }
        if (amountAdded < selectedProduct.getPrice()) {
            currState = VMState.RETURN_CHANGE;
            throw new RuntimeException("Amount added insufficient");
        }
        if (inventory.decrement(selectedProduct)) {
            amountAdded -= selectedProduct.getPrice();
            // dispense product
        }
        currState = VMState.RETURN_CHANGE;
    }

    public int returnChange() {
        if (currState != VMState.RETURN_CHANGE) {
            throw new RuntimeException("Current state of Vending Machine is " + currState);
        }
        selectedProduct = null;
        int remaining = amountAdded;
        amountAdded = 0;
        return remaining;
    }

    public enum Coin {
        ONE(1), FIVE(5), TEN(10);
        private int value;

        Coin(int val) {
            value = val;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public enum Cash {
        TEN(10), HUNDRED(100), THOUSAND(10000);
        private int value;

        Cash(int val) {
            value = val;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public enum VMState {
        IDLE, TRANSACTIONAL, DISPENSE, RETURN_CHANGE
    }

    public class Product {
        private Integer productNum;
        private String productName;
        private int price;

        public Product(int productNum, String productName, int price) {
            this.productNum = productNum;
            this.productName = productName;
            this.price = price;
        }

        public Integer getProductNum() {
            return productNum;
        }

        public void setProductNum(Integer productNum) {
            this.productNum = productNum;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

    }

    public class Inventory {
        private Map<Product, Integer> productVsCount;

        public int getCount(Product product) {
            synchronized (product) {
                Integer count = productVsCount.get(product);
                return count != null ? count : 0;
            }
        }

        public boolean decrement(Product product) {
            synchronized (product) {
                Integer count = productVsCount.get(product);
                if (count != null && count > 0) {
                    productVsCount.put(product, count - 1);
                    return true;
                }
                return false;
            }
        }

        public Map<Product, Integer> getProductVsCount() {
            return productVsCount;
        }

        public void setProductVsCount(Map<Product, Integer> productVsCount) {
            this.productVsCount = productVsCount;
        }
    }

}
