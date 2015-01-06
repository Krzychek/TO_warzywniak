package to_shop.model.products;

public class ProductWrapper extends Product {
    private Product simpleProduct;
    private double price;
    private int amount;

    ProductWrapper() {}

    public ProductWrapper(Product product) {
        simpleProduct = product;
        this.price = Double.POSITIVE_INFINITY;
        this.amount = 0;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    @Override
    public String getName() {
        return simpleProduct.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ProductWrapper
                && simpleProduct.equals(((ProductWrapper) obj).simpleProduct)
                && price == ((ProductWrapper) obj).getPrice()
                && amount == ((ProductWrapper) obj).getAmount()) {
            return true;
        } else if (obj instanceof Product)
            return simpleProduct.equals(obj);
        return false;
    }

    @Override
    public Product clone()  {
        Product simpleProduct = this.simpleProduct.clone();
        return this;
    }

    @Override
    public String toString() {
        return simpleProduct.toString();
    }
}
