package to_shop.model.productBuilders;

import to_shop.model.products.Apple;

public class AppleBuilderFactory { // TODO change to factory methdo, use!!
    public AppleBuilder getAppleBuilder(Apple.AppleType type) {
        switch (type) {
            case BRAMEY: return new BramleyAppleBuilder();
            case FUJI: return new FujiAppleBuilder();
            default: return null;
        }
    }
}
