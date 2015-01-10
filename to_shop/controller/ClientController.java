package to_shop.controller;

import to_shop.model.actors.Client;
import to_shop.model.actors.Shop;
import to_shop.model.products.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClientController {
    private Shop shop;
    private Client client;
    public ClientController(Client client, Shop shop) {
        this.client = client;
        this.shop = shop;
    }

    public boolean buy(Product item, int amount) {
        return client.buy(shop,item,amount);
    }

    public Collection<Product> getProductCollection() {
        return client.getProductCollection();
    }

    public Collection<Product> getAvailableProducts() {
        return shop.getProductCollection();
    }

    public double getPrice(Product item) {
        return shop.getPrice(item);
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
}
