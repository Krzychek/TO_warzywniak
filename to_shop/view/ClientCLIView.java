package to_shop.view;

import to_shop.controller.ClientController;
import to_shop.model.products.Product;

import java.util.Map;

class ClientCLIView extends CLIView {
    private final ClientController controller;

    public ClientCLIView(ClientController controller) {
        super("Make action as client:");
        this.controller = controller;
    }

    @Override
    protected void setUpOptions() {
        optionList.add(getBuyOption());
        optionList.add(getStatisticsOption());
    }

    private Option getStatisticsOption() {
        return new Option("View Statistics", () -> {
            StringBuilder str = new StringBuilder();
            for(Map.Entry entry: controller.getStatistics().entrySet()) {
                str.append(entry.getKey());
                str.append(": ");
                str.append(entry.getValue());
                str.append("\n");
            }
            System.out.println(str);
            return true;
        });
    }

    private Option getBuyOption() {
        return new Option("Buy product", () -> {
            new CLIView("What do you want to buy?") {
                @Override
                protected void setUpOptions() {
                    for (Product item: controller.getAvailableProducts()) {
                        optionList.add(new Option(
                                item.getName() + " - " + controller.getPrice(item), () -> {
                                    new CLIView("How much do you want to buy?") {
                                        @Override
                                        protected void processAnswer(int answer) {
                                            if (controller.buy(item, answer))
                                                System.out.println("Bought succesfully");
                                            else {
                                                System.out.println("Bought failed");
                                            }
                                            running = false;
                                        }
                                    }.run();
                                    return false;
                                }
                        ));
                    }
                }
            }.run();
            return true;
        });
    }
}
