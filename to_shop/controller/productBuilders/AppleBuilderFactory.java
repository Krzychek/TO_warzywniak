package to_shop.controller.productBuilders;

import to_shop.model.products.Apple;

public class AppleBuilderFactory {
    public AppleBuilder getAppleBuilder(Apple.AppleType type) {
        switch (type) {
            case BRAMEY: return new BramleyAppleBuilder();
            case FUJI: return new FujiAppleBuilder();
            default: return null;
        }
    }
}
