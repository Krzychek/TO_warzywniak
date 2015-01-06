package to_shop.model.products;

public class ReadOnlyProductWrapper extends ProductWrapper {
    ProductWrapper productWrapper;

    public ReadOnlyProductWrapper(ProductWrapper productWrapper) {
        this.productWrapper = productWrapper;
    }

    @Override
    public double getPrice() {
        return productWrapper.getPrice();
    }

    @Override
    public void setPrice(double price) {}

    @Override
    public int getAmount() {
        return productWrapper.getAmount();
    }

    @Override
    public void addAmount(int amount) {}

    @Override
    public String getName() {
        return productWrapper.getName();
    }

    @Override
    public boolean equals(Object obj) {
        return productWrapper.equals(obj);
    }

    @Override
    public Product clone() {
        return productWrapper.clone();
    }

    @Override
    public String toString() {
        return productWrapper.toString();
    }
}