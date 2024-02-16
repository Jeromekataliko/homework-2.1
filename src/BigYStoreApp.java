import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BigYStoreApp extends JFrame implements ActionListener {

    private JComboBox<String> sizeComboBox;
    private JCheckBox[] toppingsCheckBoxes;
    private JCheckBox extraCheeseCheckBox;
    private JButton calculateButton;
    private JLabel priceLabel;

    private final String[] pizzaSizes = {"Small", "Medium", "Large", "Super"};

    private static final double SMALL_PRICE = 5;
    private static final double MEDIUM_PRICE = 10;
    private static final double LARGE_PRICE = 15;
    private static final double SUPER_PRICE = 20;

    private static final int MAX_TOPPINGS = 3;

    public BigYStoreApp() {
        setTitle("BigY Store Order App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(7, 2)); // Adjust the grid size
        mainPanel.setBackground(Color.LIGHT_GRAY);

        JLabel sizeLabel = new JLabel("Pizza Size:");
        sizeLabel.setForeground(Color.RED);
        mainPanel.add(sizeLabel);

        sizeComboBox = new JComboBox<>(pizzaSizes);
        mainPanel.add(sizeComboBox);

        JLabel toppingsLabel = new JLabel("Toppings (Max 3):");
        toppingsLabel.setForeground(Color.RED);
        mainPanel.add(toppingsLabel);

        JPanel toppingsPanel = new JPanel(new GridLayout(1, 0));
        String[] toppings = {"Pepperoni", "Mushrooms", "Onions", "Black Olives"};
        toppingsCheckBoxes = new JCheckBox[toppings.length];
        for (int i = 0; i < toppings.length; i++) {
            toppingsCheckBoxes[i] = new JCheckBox(toppings[i]);
            toppingsCheckBoxes[i].setForeground(Color.RED);
            toppingsPanel.add(toppingsCheckBoxes[i]);
        }
        mainPanel.add(toppingsPanel);

        extraCheeseCheckBox = new JCheckBox("Extra Cheese (Free)");
        extraCheeseCheckBox.setForeground(Color.RED);
        mainPanel.add(extraCheeseCheckBox);

        calculateButton = new JButton("Calculate Price");
        calculateButton.addActionListener(this);
        mainPanel.add(calculateButton);

        priceLabel = new JLabel("");
        priceLabel.setForeground(Color.RED);
        mainPanel.add(priceLabel);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            double totalPrice = calculateTotalPrice();
            displayPrice(totalPrice);
        }
    }

    private double calculateTotalPrice() {
        double totalPrice = 0;
        int numToppings = 0;

        for (JCheckBox checkBox : toppingsCheckBoxes) {
            if (checkBox.isSelected()) {
                numToppings++;
                totalPrice += 0.5; // Each topping costs $0.50
            }
        }

        if (extraCheeseCheckBox.isSelected()) {
            // Extra cheese is free
        }

        if (numToppings > MAX_TOPPINGS) {
            totalPrice = -1; // Error code for  max toppings
        } else {
            switch (sizeComboBox.getSelectedIndex()) {
                case 0:
                    totalPrice += SMALL_PRICE;
                    break;
                case 1:
                    totalPrice += MEDIUM_PRICE;
                    break;
                case 2:
                    totalPrice += LARGE_PRICE;
                    break;
                case 3:
                    totalPrice += SUPER_PRICE;
                    break;
            }
        }

        return totalPrice;
    }

    private void displayPrice(double totalPrice) {
        if (totalPrice >= 0) {
            priceLabel.setText("Total Price: $" + String.format("%.2f", totalPrice));
        } else {
            priceLabel.setText("Error: Only " + MAX_TOPPINGS + " toppings allowed!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BigYStoreApp();
            }
        });
    }
}
