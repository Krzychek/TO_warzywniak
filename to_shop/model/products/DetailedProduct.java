package to_shop.model.products;

public abstract class DetailedProduct extends Product {
    @Override
    public abstract int getAmount();

    public abstract void setAmount(int amount);

    public abstract void addAmount(int amount);

    @Override
    public abstract void ripe();

    public abstract double getPrice();

    public abstract void setPrice(double price);

    public abstract void setDiscount(double discount);

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract boolean isRipe();

    @Override
    public abstract DetailedProduct clone();

    @Override
    public abstract String toString();
}
