package to_shop.model.products;

public class ProductWrapper extends DetailedProduct {
    private Product product;
    private double price;
    private int amount;
    private double discount;

    private ProductWrapper() {}

    public ProductWrapper(Product product) {
        this(product, Double.POSITIVE_INFINITY, 0);
    }

    public ProductWrapper(Product product, double price, int amount) {
        this.product = product.unPack();
        this.price = price;
        this.amount = amount;
        this.price = Double.POSITIVE_INFINITY;
        this.amount = 0;
        this.discount = 0;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void addAmount(int amount) {
        this.amount += amount;
    }

    @Override
    public void ripe() {
        product.ripe();
    }

    @Override
    public double getPrice() {
        return price * (1-discount);
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DetailedProduct)
            if (product.equals(((DetailedProduct) obj).unPack())
                    && price == ((DetailedProduct) obj).getPrice()
                    && amount == ((DetailedProduct) obj).getAmount()) {
                return true;
            } else if (obj instanceof Product)
                return product.equals(obj);
        return false;
    }

    @Override
    public boolean isRipe() {
        return product.isRipe();
    }

    @Override
    public DetailedProduct clone() {
        ProductWrapper result = new ProductWrapper();
        result.amount = amount;
        result.price = price;
        result.product = product.clone();
        return result;
    }

    @Override
    public String getCategory() {
        return product.getCategory();
    }

    @Override
    public Product unPack() {
        return product.unPack();
    }

    @Override
    public String toString() {
        if (price == Double.POSITIVE_INFINITY) return product.toString() + " x" + amount;
        return product.toString() + " : " + price + "zł x" + amount;
    }
}
