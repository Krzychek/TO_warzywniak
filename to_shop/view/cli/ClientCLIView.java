package to_shop.view.cli;

import to_shop.controller.ClientController;

import java.util.Map;
import java.util.stream.Collectors;

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
                    optionList.addAll(controller.getAvailableProducts().stream().map(item -> new Option(item.toString(),
                            () -> {
                                new CLIView("How much do you want to buy?") {
                                    @Override
                                    protected void processAnswer(String answer) {
                                        if (controller.buy(item.unPack(), Integer.parseInt(answer)))
                                            System.out.println("Bought succesfully");
                                        else {
                                            System.out.println("Bought failed");
                                        }
                                        running = false;
                                    }
                                }.run();
                                return false;
                            }
                    )).collect(Collectors.toList()));
                }
            }.run();
            return true;
        });
    }
}
