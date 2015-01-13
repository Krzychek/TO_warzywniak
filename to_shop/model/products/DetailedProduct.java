package to_shop.model.products;

public abstract class DetailedProduct extends Product {
    public abstract void setPrice(double price);
    public abstract int getAmount();
    public abstract void addAmount(int amount);
    public abstract void setAmount(int amount);
    public abstract Product getProduct();
    public abstract double getPrice();
    public abstract void setDiscount(double discount);

    @Override
    public abstract boolean equals(Object obj);
    @Override
    public abstract DetailedProduct clone();
    @Override
    public abstract String toString();
}
