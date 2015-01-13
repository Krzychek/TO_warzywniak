package to_shop.model.products;

public class ProductWrapper extends DetailedProduct  {
    private Product product;
    private double price;
    private int amount;
    private double discount;

    private ProductWrapper() {}
    public ProductWrapper(Product product) {
        this.product = product;
        this.price = Double.POSITIVE_INFINITY;
        this.amount = 0;
    }
    public ProductWrapper(Product product, double price, int amount) {
        this.product = product.unPack();
        this.price = price;
        this.amount = amount;
        this.price = Double.POSITIVE_INFINITY;
        this.amount = 0;
    }

    @Override
    public void setPrice(double price) { this.price = price; }
    @Override
    public int getAmount() { return amount; }
    @Override
    public void addAmount(int amount) { this.amount += amount; }
    @Override
    public void setAmount(int amount) { this.amount = amount; }
    @Override
    public Product getProduct() { return product; }
    @Override
    public double getPrice() { return price; }

    @Override
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DetailedProduct)
            if (product.equals(((DetailedProduct) obj).getProduct())
                && price == ((DetailedProduct) obj).getPrice()
                && amount == ((DetailedProduct) obj).getAmount()) {
              return true;
        } else if (obj instanceof Product)
            return product.equals(obj);
        return false;
    }

    @Override
    public DetailedProduct clone()  {
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
    public String toString() { return product.toString() + " - " + price + " x " + amount; }
}
