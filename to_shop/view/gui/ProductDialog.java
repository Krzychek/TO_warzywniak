package to_shop.view.gui;

import to_shop.model.products.DetailedProduct;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProductDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner amount;
    private JSpinner price;
    private JLabel productName;
    private DetailedProduct product;

    public ProductDialog(DetailedProduct product) {
        this.product = product;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
        productName.setText(product.unPack().toString());
        amount.setValue(product.getAmount());
        price.setValue(product.getPrice());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }

}
