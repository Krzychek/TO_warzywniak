package to_shop.view.cli;

import to_shop.controller.ShopController;

class ShopCLIView extends CLIView {
    private final ShopController controller;

    public ShopCLIView(ShopController controller) {
        super("Wykonaj akcję jako sklep: ");
        this.controller = controller;
    }

    @Override
    protected void setUpOptions() {
        optionList.add(new Option("Ustaw domyślny zestaw produktów", () -> {
            controller.toDefaultProductSet();
            return true;
        }
        ));

    }
}
