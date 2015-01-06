package to_shop.view;

import to_shop.controller.MainController;

public class MainCLIView extends CLIView {
    private final MainController controller;

    public MainCLIView(MainController controller) {
        super("Make action");
        this.controller = controller;
    }

    @Override
    protected void setUpOptions() {
        optionList.add(new Option("control shop", () -> {
            new ShopCLIView(controller.getShopController()).run();
            return true;
        }));
        optionList.add(new Option("control client", () -> {
            new ClientCLIView(controller.getClientController()).run();
            return true;
        }));
    }

}
