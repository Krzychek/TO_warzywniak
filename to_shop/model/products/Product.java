package to_shop.model.products;

import java.util.ArrayList;
import java.util.List;

public abstract class Product implements Cloneable {
	String name;
	Product() {}
	private boolean isRipe;

	protected Product(String name) {
		this.name = name;
		isRipe = false;
	}

	public void ripe() {
		isRipe = true;
	}

	public String toString() {
		return (isRipe ? "ripe " : "unripe ") + name.toLowerCase();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DetailedProduct)
			return ((Product) obj).unPack().equals(this);
		if (obj instanceof Product)
			return ((Product) obj).toString().equals(this.toString());
		return false;
	}

	@Override
	public int hashCode() {
		return toString().length();
	}

	@Override
	public Product clone()  {
		try {
			return (Product) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Product unPack() {
		return this;
	}

	private static ArrayList<Product> availableList;
	public static List<Product> getAvailableList() {
		if (availableList == null) {
			availableList = new ArrayList<>();
			availableList.add(new Apple(Apple.AppleType.FUJI));
			availableList.add(new Apple(Apple.AppleType.BRAMEY));
			availableList.add(new Banana(Banana.BananaType.YELLOW));
			availableList.add(new Banana(Banana.BananaType.GREEN));
			availableList.add(new Carrot(Carrot.CarrotType.YELLOW));
			availableList.add(new Carrot(Carrot.CarrotType.GREEN));
		}
		return availableList;
	}

	public abstract String getCategory();
}
