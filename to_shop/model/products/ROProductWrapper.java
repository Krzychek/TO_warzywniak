package to_shop.model.products;

public class ROProductWrapper extends DetailedProduct {
    DetailedProduct detailedProduct;

    public ROProductWrapper(DetailedProduct detailedProduct) {
        this.detailedProduct = detailedProduct;
    }

    public ROProductWrapper(Product product, double price, int amount) {
        this.detailedProduct = new ProductWrapper(product.unPack(), price, amount);
    }


    @Override
    public Product unPack() {
        return detailedProduct.unPack();
    }

    @Override
    public double getPrice() {
        return detailedProduct.getPrice();
    }

    @Override
    public void setPrice(double price) {
    }

    @Override
    public int getAmount() {
        return detailedProduct.getAmount();
    }

    @Override
    public void setAmount(int amount) {
    }

    @Override
    public boolean equals(Object obj) {
        return detailedProduct.equals(obj);
    }

    @Override
    public boolean isRipe() {
        return detailedProduct.isRipe();
    }

    @Override
    public DetailedProduct clone() {
        return this;
    }

    @Override
    public String getCategory() {
        return detailedProduct.getCategory();
    }

    @Override
    public String toString() {
        return detailedProduct.toString();
    }

    @Override
    public void setDiscount(double discount) {
    }

    @Override
    public void addAmount(int amount) {
    }

    @Override
    public void ripe() {
        detailedProduct.ripe();
    }
}