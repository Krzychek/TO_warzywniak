package to_shop.view.gui;

import to_shop.model.products.Product;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Collection;
import java.util.HashMap;

public class ProductTreeModel extends DefaultTreeModel {
    private HashMap<String, DefaultMutableTreeNode> categoryMap = new HashMap<>();
    private HashMap<Product, DefaultMutableTreeNode> productMap = new HashMap<>();
    private DefaultMutableTreeNode top;

    public ProductTreeModel() {
        super(new DefaultMutableTreeNode());
        top = (DefaultMutableTreeNode) root;
    }

    public void update(Collection<Product> data) {
        productMap.keySet().stream().filter(key -> !data.contains(key)).forEach(product -> {
            DefaultMutableTreeNode node = productMap.remove(product);
            DefaultMutableTreeNode category = (DefaultMutableTreeNode) node.getParent();
            if (category.getChildCount() <= 1) {
                categoryMap.remove(category.getUserObject().toString());
                removeNodeFromParent(category);
            }
            removeNodeFromParent(node);
        });

        for (Product item: data) {
            if (!productMap.containsKey(item)) {
                DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(item);
                productMap.put(item, itemNode);
                DefaultMutableTreeNode categoryNode = categoryMap.get(item.getCategory());
                if (categoryNode == null) {
                    categoryNode = new DefaultMutableTreeNode(item.getCategory());
                    categoryMap.put(item.getCategory(), categoryNode);
                    insertNodeInto(categoryNode, top, top.getChildCount());
                    if (top.getChildCount() == 1) nodeStructureChanged(top);
                }
                insertNodeInto(itemNode, categoryNode, categoryNode.getChildCount());
            } else {
                nodeChanged(productMap.get(item));
            }
        }
    }
}
