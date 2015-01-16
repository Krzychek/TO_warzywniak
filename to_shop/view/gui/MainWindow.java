package to_shop.view.gui;

import to_shop.controller.ClientController;
import to_shop.controller.ShopController;
import to_shop.model.actors.Client;
import to_shop.model.actors.EventHistory;
import to_shop.model.actors.Shop;
import to_shop.model.products.DetailedProduct;
import to_shop.model.products.Product;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Collection;

public class MainWindow {
    private final ClientController clientController;
    private final ShopController shopController;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel shopPanel;
    private JTree shopInTree;
    private JTree shopAvailableTree;
    private JButton shopAddBtn;
    private JButton shopRmBtn;
    private JPanel clientPanel;
    private JTree clientAvailableTree;
    private JTree clientOwnedTree;
    private JButton clientBuyBtn;
    private JFormattedTextField clientAmountField;
    private JList historyList;
    private JPanel historyPanel;

    public MainWindow(ClientController clientController, ShopController shopController) {
        this.clientController = clientController;
        this.shopController = shopController;

        clientAmountField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        initializeListeners();
        clientAvailableTree.setModel(new ProductTreeModel());
        clientOwnedTree.setModel(new ProductTreeModel());
        shopAvailableTree.setModel(new ProductTreeModel());
        shopInTree.setModel(new ProductTreeModel());
        refreshShop();
        refreshClient();
        refreshHistory();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow(new ClientController(new Client()), new ShopController()).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initializeListeners() {
        // client panel
        clientPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                refreshClient();
            }
        });
        clientBuyBtn.addActionListener(e -> {
            Object obj = ((DefaultMutableTreeNode) clientAvailableTree.getSelectionModel()
                    .getSelectionPath().getLastPathComponent()).getUserObject();
            if (obj instanceof Product) {
                try {
                    clientController.buy((Product) obj, Integer.parseInt(clientAmountField.getText()));
                } catch (Client.NotEnoughMoneyException e1) {
                    JOptionPane.showMessageDialog(panel1, "Nie ma tyle artykułów na stanie.");
                } catch (Shop.NotEnoughAmountException e1) {
                    JOptionPane.showMessageDialog(panel1, "Nie masz wystarczająco dużo pieniędzy.");
                }
                refreshClient();
            }
        });
        // shop panel
        shopPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                refreshShop();
            }
        });
        shopInTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Object obj = ((DefaultMutableTreeNode) shopInTree.getSelectionModel()
                            .getSelectionPath().getLastPathComponent()).getUserObject();
                    if (obj instanceof DetailedProduct) {
                        ProductDialog dialog = new ProductDialog((DetailedProduct) obj);
                        dialog.pack();
                        dialog.setVisible(true);
                        refreshShop();
                    }
                }
            }
        });
        shopAddBtn.addActionListener(e -> {
            Object obj = ((DefaultMutableTreeNode) shopAvailableTree.getSelectionModel()
                    .getSelectionPath().getLastPathComponent()).getUserObject();
            if (obj instanceof Product) {
                shopController.addProduct((Product) obj);
                refreshShop();
            }
        });
        shopRmBtn.addActionListener(e -> {
            Object obj = ((DefaultMutableTreeNode) shopInTree.getSelectionModel()
                    .getSelectionPath().getLastPathComponent()).getUserObject();
            if (obj instanceof Product) {
                shopController.rmProduct((Product) obj);
                refreshShop();
            }
        });
        // history panel
        historyPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                refreshHistory();
            }
        });
    }

    public void refreshClient() {
        ((ProductTreeModel) clientOwnedTree.getModel()).update((Collection) clientController.getProductCollection());
        ((ProductTreeModel) clientAvailableTree.getModel()).update((Collection) clientController.getAvailableProducts());

    }

    public void refreshShop() {
        ((ProductTreeModel) shopInTree.getModel()).update((Collection) shopController.getProductCollection());
        ((ProductTreeModel) shopAvailableTree.getModel()).update(shopController.getAvailableProducts());
    }

    public void refreshHistory() {
        historyList.setListData(EventHistory.getInstance().getHistoryArray());
    }
}
