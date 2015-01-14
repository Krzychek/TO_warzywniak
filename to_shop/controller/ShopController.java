package to_shop.controller;

import to_shop.model.actors.MainShop;
import to_shop.model.actors.Shop;
import to_shop.model.products.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ShopController {
    private Shop shop;
    public ShopController() {
        this.shop = MainShop.getInstance();
    }

    public void addProduct(Product item, int amount, double price) {
        shop.addProduct(item, amount, price);
    }

    public void addAmount(Product item, int amount) {
        shop.getDetailedProduct(item).addAmount(amount);
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
        shop.addProduct(new Carrot(Carrot.CarrotType.YELLOW), 5, 0.2);
    }

    public void addProduct(Product product) {
        shop.addProduct(product);
    }

    public void rmProduct(Product product) {
        shop.rmProduct(product);
    }

    public List<Product> getAvailableProducts() {
        return Product.getAvailableList().stream().filter((item) -> !shop.getProductCollection().contains(item))
                .collect(Collectors.toList());
    }
}
