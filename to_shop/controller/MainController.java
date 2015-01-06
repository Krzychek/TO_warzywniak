package to_shop.controller;

import to_shop.controller.actors.Client;
import to_shop.controller.actors.MainShop;

public class MainController {
    private ClientController clientController;
    private ShopController shopController;


    public MainController(ClientController clientController, ShopController shopController) {
        this.clientController = clientController;
        this.shopController = shopController;
    }
    public MainController() {
        this.clientController = new ClientController(new Client(200), MainShop.getInstance());
        this.shopController = new ShopController();
    }

    public ClientController getClientController() {
        return clientController;
    }
    public ShopController getShopController() {
        return shopController;
    }
}
