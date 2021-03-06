package to_shop.model.products;

public class Apple extends Fruit implements Cloneable {
    AppleType type;

    public Apple(AppleType type) {
        super(type.name() + " apple");
        this.type = type;
    }

    public AppleType getType() {
        return type;
    }

    public static enum AppleType {BRAMEY, FUJI;}

}
