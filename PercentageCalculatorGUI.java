import javax.swing.*;

import java.awt.*;

public class PercentageCalculatorGUI extends JFrame {
    JTextField inputX, inputY;
    JTextArea resultArea;
    JButton calculateButton, clearButton;
    JComboBox<String> modeBox;

    public PercentageCalculatorGUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/percy.png")));
        setTitle("Percentage Calculator");
        setSize(420, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Mode Selector
        String[] modes = {
            "What is X% of Y?",
            "X is what % of Y?",
            "X is Y% of what?"
        };

        modeBox = new JComboBox<>(modes);
        modeBox.setBounds(50, 20, 300, 25);
        add(modeBox);

        JLabel labelX = new JLabel("X:");
        labelX.setBounds(50, 60, 80, 25);
        add(labelX);

        inputX = new JTextField();
        inputX.setBounds(120, 60, 200, 25);
        add(inputX);

        JLabel labelY = new JLabel("Y:");
        labelY.setBounds(50, 100, 80, 25);
        add(labelY);

        inputY = new JTextField();
        inputY.setBounds(120, 100, 200, 25);
        add(inputY);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(120, 140, 100, 30);
        add(calculateButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(230, 140, 90, 30);
        add(clearButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setBounds(50, 190, 300, 40);
        add(resultArea);

        calculateButton.addActionListener(e -> calculate());
        clearButton.addActionListener(walanakokasabotanihuhuhuhuuz -> {
            inputX.setText("");
            inputY.setText("");
            resultArea.setText("");
            inputX.requestFocusInWindow();
        });

        setVisible(true);
    }

    private void calculate() {
        try {
            String mode = (String) modeBox.getSelectedItem();
            String output;

            switch (mode) {
                case "What is X% of Y?":
                    new XPercentOfY();
                case "X is what % of Y?":
                    new XIsWhatPercentOfY();
                case "X is Y% of what?":
                    new XIsYPercentOfWhat();
                default:
                    output = "Unknown mode.";
            }

            resultArea.setText(output);
        } catch (NumberFormatException e) {
            resultArea.setText("Please enter valid numbers for X and Y.");
        } catch (ArithmeticException e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new PercentageCalculatorGUI();
    }
}

