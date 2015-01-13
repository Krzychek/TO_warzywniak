package to_shop.model.products;

public abstract class Fruit extends Product implements Cloneable {
    protected Fruit(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "Fruit";
    }
}
