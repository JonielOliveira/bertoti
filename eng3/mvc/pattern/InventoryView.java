package pattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InventoryView implements ProductObserver, ActionListener {
    private ProductSubject model;
    private ControllerInterface controller;

    private JFrame frame;
    private JLabel lblStatus;
    private JButton btnSubscribe;
    private JButton btnUnsubscribe;

    public InventoryView(ProductSubject model, ControllerInterface controller) {
        this.model = model;
        this.controller = controller;
    }

    public void createView() {
        frame = new JFrame("Monitor de Estoque");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        lblStatus = new JLabel("Estoque: -");
        btnSubscribe = new JButton("Assinar");
        btnUnsubscribe = new JButton("Cancelar");

        btnSubscribe.addActionListener(this);
        btnUnsubscribe.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(lblStatus);
        panel.add(btnSubscribe);
        panel.add(btnUnsubscribe);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void update(String productName, int quantity) {
        lblStatus.setText(productName + " em estoque: " + quantity + " unidades");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubscribe) {
            controller.subscribe();
        } else if (e.getSource() == btnUnsubscribe) {
            controller.unsubscribe();
        }
    }
}
