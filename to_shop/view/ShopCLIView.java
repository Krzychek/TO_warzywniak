package to_shop.view;

import to_shop.controller.ShopController;

class ShopCLIView extends CLIView {
    private final ShopController controller;

    public ShopCLIView(ShopController controller) {
        super("Make action on shop: ");
        this.controller = controller;
    }

    @Override
    protected void setUpOptions() {
        optionList.add(new Option("Set default product set", () -> {
            controller.toDefaultProductSet();
            return true;
        }
        ));

    }
}
