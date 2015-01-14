package to_shop.controller;

import to_shop.model.actors.Client;
import to_shop.model.actors.MainShop;
import to_shop.model.actors.Shop;
import to_shop.model.products.DetailedProduct;
import to_shop.model.products.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClientController {
    private Shop shop;
    private Client client;
    public ClientController(Client client) {
        this(client, MainShop.getInstance());
    }
    public ClientController(Client client, Shop shop) {
        this.client = client;
        this.shop = shop;
    }

    public void buy(Product item, int amount) throws Client.NotEnoughMoneyException, Shop.NotEnoughAmountException {
        client.buy(shop,item,amount);
    }

    public Collection<DetailedProduct> getProductCollection() {
        return client.getProductCollection();
    }

    public Collection<DetailedProduct> getAvailableProducts() {
        return shop.getProductCollection();
    }

    public Map<String, String> getStatistics() {
        HashMap<String, String> result = new HashMap<>();
        result.put("money",String.valueOf(client.getMoney()));
        result.put(
                "owned products",
                String.valueOf(client.getProductCollection().size())
        );
        return result;
    }

    public double getPrice(Product item) {
        return shop.getPrice(item);
    }
}
