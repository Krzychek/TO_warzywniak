package to_shop;

import to_shop.controller.ClientController;
import to_shop.controller.MainController;
import to_shop.controller.ShopController;
import to_shop.model.actors.Client;
import to_shop.model.actors.MainShop;
import to_shop.view.cli.CLIView;
import to_shop.view.cli.MainCLIView;

/**
 * Created by krzysiek on 14.12.14.
 */
public class Main {
    public static void main(String[] args) {
        CLIView view = new MainCLIView(
                new MainController(new ClientController(
                        new Client(200), MainShop.getInstance()),
                        new ShopController()
                )
        );
        view.run();
    }
}
