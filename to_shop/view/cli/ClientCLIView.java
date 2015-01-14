package to_shop.view.cli;

import to_shop.controller.ClientController;
import to_shop.model.actors.Client;
import to_shop.model.actors.Shop;

import java.util.Map;
import java.util.stream.Collectors;

class ClientCLIView extends CLIView {
    private final ClientController controller;

    public ClientCLIView(ClientController controller) {
        super("Wykonaj akcję jako klient:");
        this.controller = controller;
    }

    @Override
    protected void setUpOptions() {
        optionList.add(getBuyOption());
        optionList.add(getStatisticsOption());
    }
    private Option getStatisticsOption() {
        return new Option("Wyświetl statystyki", () -> {
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
        return new Option("Kup produkt", () -> {
            new CLIView("Co chcesz kupić?") {
                @Override
                protected void setUpOptions() {
                    optionList.addAll(controller.getAvailableProducts().stream().map(item -> new Option(item.toString(),
                            () -> {
                                new CLIView("Jaką ilość chcesz kupić?") {
                                    @Override
                                    protected void processAnswer(String answer) {
                                        try {
                                            controller.buy(item.unPack(), Integer.parseInt(answer));
                                            System.out.println("Bought succesfully");
                                        } catch (Shop.NotEnoughAmountException e) {
                                            System.out.println("Nie ma takiej ilości produktu na stanie");
                                        } catch (Client.NotEnoughMoneyException e) {
                                            System.out.println("Nie masz tyle pieniędzy");
                                        }
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
