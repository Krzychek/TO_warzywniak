package to_shop.controller.productBuilders;

import to_shop.model.products.Banana;

public class BananaBuilderFactory {
    public BananaBuilder getBananaBuilder(Banana.BananaType type) {
        switch (type) {
            case GREEN: return new GreenBananaBuilder();
            case YELLOW:return new YellowBananaBuilder();
            default: return null;
        }
    }
}
