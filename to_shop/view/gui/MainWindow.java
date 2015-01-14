package to_shop.view.gui;

import to_shop.controller.ClientController;
import to_shop.controller.ShopController;
import to_shop.model.actors.Client;
import to_shop.model.actors.EventHistory;
import to_shop.model.actors.Shop;
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
    private JTree shop_in_tree;
    private JTree shop_available_tree;
    private JButton shop_add;
    private JButton shop_rm;
    private JButton byuBtn;
    private JFormattedTextField clientAmountField;
    private JTree client_available_tree;
    private JTree client_owned_tree;
    private JList historyList;
    private JPanel shopPanel;
    private JPanel clientPanel;
    private JPanel historyPanel;

    public MainWindow(ClientController clientController, ShopController shopController) {
        this.clientController = clientController;
        this.shopController = shopController;

        clientAmountField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        initializeListeners();
        client_available_tree.setModel(new ProductTreeModel());
        client_owned_tree.setModel(new ProductTreeModel());
        shop_available_tree.setModel(new ProductTreeModel());
        shop_in_tree.setModel(new ProductTreeModel());
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
        clientPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                refreshClient();
            }
        });
        shopPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                refreshShop();
            }
        });
        historyPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                refreshHistory();
            }
        });
        shop_add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object obj = ((DefaultMutableTreeNode) shop_available_tree.getSelectionModel()
                        .getSelectionPath().getLastPathComponent()).getUserObject();
                if (obj instanceof Product) {
                    shopController.addProduct((Product) obj);
                    refreshShop();
                }
            }
        });
        shop_rm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object obj = ((DefaultMutableTreeNode) shop_in_tree.getSelectionModel()
                        .getSelectionPath().getLastPathComponent()).getUserObject();
                if (obj instanceof Product) {
                    shopController.rmProduct((Product) obj);
                    refreshShop();
                }
            }
        });
        byuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object obj = ((DefaultMutableTreeNode) client_available_tree.getSelectionModel()
                        .getSelectionPath().getLastPathComponent()).getUserObject();
                if (obj instanceof Product) {
                    try {
                        clientController.buy((Product) obj, Integer.parseInt(clientAmountField.getText()));
                    } catch (Client.NotEnoughMoneyException e1) {
                        // TODO check
                    } catch (Shop.NotEnoughAmountException e1) {
                        // TODO check
                    }
                    refreshClient();
                }
            }
        });
    }

    public void refreshClient() {
        ((ProductTreeModel) client_owned_tree.getModel()).update((Collection) clientController.getProductCollection());
        ((ProductTreeModel) client_available_tree.getModel()).update((Collection) clientController.getAvailableProducts());

    }

    public void refreshShop() {
        ((ProductTreeModel) shop_in_tree.getModel()).update((Collection) shopController.getProductCollection());
        ((ProductTreeModel) shop_available_tree.getModel()).update(shopController.getAvailableProducts());
    }

    public void refreshHistory() {
        historyList.setListData(EventHistory.getInstance().getHistoryArray());
    }
}
