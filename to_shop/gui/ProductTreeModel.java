package to_shop.gui;

import to_shop.model.products.Product;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ProductTreeModel extends DefaultTreeModel {
    HashMap<String, DefaultMutableTreeNode> categoryMap = new HashMap<>();
    HashMap<Product, DefaultMutableTreeNode> productMap = new HashMap<>();
    DefaultMutableTreeNode top;

    public ProductTreeModel() {
        super(new DefaultMutableTreeNode());
        top = (DefaultMutableTreeNode) root;
    }

    public void update(Collection<Product> data) {
        List<Product> toRemove = new ArrayList<>();
        productMap.keySet().forEach((key -> {
            if (!data.contains(key))
                toRemove.add(key);
        }));
        for (Product product : toRemove) {
            DefaultMutableTreeNode node = productMap.remove(product);
            DefaultMutableTreeNode category = (DefaultMutableTreeNode) node.getParent();
            if (category.getChildCount() <= 1) {
                categoryMap.remove(category.getUserObject().toString());
                removeNodeFromParent(category);
            }
            removeNodeFromParent(node);
        }
        for (Product item: data) {
            if (!productMap.containsKey(item)) {
                DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(item);
                productMap.put(item, itemNode);
                DefaultMutableTreeNode categoryNode = categoryMap.get(item.getCategory());
                if (categoryNode == null) {
                    categoryNode = new DefaultMutableTreeNode(item.getCategory());
                    categoryMap.put(item.getCategory(), categoryNode);
                    insertNodeInto(categoryNode, top, top.getChildCount());
                    if (top.getChildCount() == 1)
                        nodeStructureChanged(top);
                }
                insertNodeInto(itemNode, categoryNode, categoryNode.getChildCount());

            }
        }
    }
}
