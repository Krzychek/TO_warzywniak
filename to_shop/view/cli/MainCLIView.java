package to_shop.view.cli;

import to_shop.controller.MainController;

public class MainCLIView extends CLIView {
    private final MainController controller;

    public MainCLIView(MainController controller) {
        super("Co chcesz zrobić?");
        this.controller = controller;
    }

    @Override
    protected void setUpOptions() {
        optionList.add(new Option("Kontroluj sklep", () -> {
            new ShopCLIView(controller.getShopController()).run();
            return true;
        }));
        optionList.add(new Option("Kontroluj klienta", () -> {
            new ClientCLIView(controller.getClientController()).run();
            return true;
        }));
    }

}
