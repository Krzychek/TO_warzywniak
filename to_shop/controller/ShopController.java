package to_shop.controller;

import to_shop.model.actors.MainShop;
import to_shop.model.actors.Shop;
import to_shop.model.products.*;

import java.util.Collection;

public class ShopController {
    private Shop shop;
    public ShopController() {
        this.shop = MainShop.getInstance();
    }

    public void setPrice(Product item, double price) {
        shop.setPrice(item, price);
    }

    public double getPrice(Product item) {
        return shop.getPrice(item);
    }

    public void addProduct(Product item, int amount, double price) {
        shop.addProduct(item,amount,price);
    }

    public Collection<DetailedProduct> getProductCollection() {
        return shop.getProductCollection();
    }

    public void toDefaultProductSet() {
        shop.clearShop();
        shop.addProduct(new Apple(Apple.AppleType.BRAMEY), 40, 0.50);
        shop.addProduct(new Apple(Apple.AppleType.FUJI), 40, 0.40);
        shop.addProduct(new Banana(Banana.BananaType.YELLOW), 10, 1.0);
        shop.addProduct(new Carrot(Carrot.CarrotType.GREEN), 5, 0.2);
    }
}
