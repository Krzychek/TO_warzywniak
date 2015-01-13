package to_shop.model.products;

public abstract class DetailedProduct extends Product {
    public abstract int getAmount();

    public abstract void setAmount(int amount);

    public abstract void addAmount(int amount);

    public abstract double getPrice();

    public abstract void setPrice(double price);

    public abstract void setDiscount(double discount);

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract DetailedProduct clone();

    @Override
    public abstract String toString();
}
