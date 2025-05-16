import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class PercentageCalculatorGUI extends JFrame {
    JTextField inputX, inputY;
    JTextArea resultArea;
    JButton calculateButton, clearButton;
    JComboBox<String> modeBox;

    public PercentageCalculatorGUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/percy.png")));
        setTitle("Percycal: Percentage Calculator");
        setSize(460, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        // Colors
        Color bgColor = new Color(255, 245, 230); // light orange cream
        Color orange = new Color(255, 150, 75);   // soft orange
        Color darkOrange = new Color(230, 100, 50);

        // Font
        Font font = new Font("SansSerif", Font.PLAIN, 16);

        // Base panel
        JPanel basePanel = new JPanel();
        basePanel.setBackground(bgColor);
        basePanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));

        // Mode dropdown
        modeBox = new JComboBox<>(new String[] {
            "What is X% of Y?",
            "X is what % of Y?",
            "X is Y% of what?"
        });
        styleComponent(modeBox, font, orange);
        basePanel.add(centered(modeBox));
        basePanel.add(Box.createVerticalStrut(15));

        // Input X
        inputX = createTextField(font, orange);
        basePanel.add(centered(createLabeledField("X:", inputX, font, bgColor)));
        basePanel.add(Box.createVerticalStrut(10));

        // Input Y
        inputY = createTextField(font, orange);
        basePanel.add(centered(createLabeledField("Y:", inputY, font, bgColor)));
        basePanel.add(Box.createVerticalStrut(20));

        // Buttons
        calculateButton = createButton("Calculate", orange);
        clearButton = createButton("Clear", darkOrange);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(bgColor);
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        basePanel.add(buttonPanel);
        basePanel.add(Box.createVerticalStrut(20));

        // Result Area
        resultArea = new JTextArea(3, 25);
        resultArea.setFont(font);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setBackground(Color.WHITE);
        resultArea.setBorder(new LineBorder(orange, 1, true));
        resultArea.setMargin(new Insets(10, 10, 10, 10));
        resultArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultArea.setAlignmentY(Component.CENTER_ALIGNMENT);
        resultArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultArea.setCaretPosition(0);
        resultArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // Center-align text inside JTextArea
        resultArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(360, 70));
        scrollPane.setBorder(new LineBorder(orange, 1, true));
        basePanel.add(centered(scrollPane));

        // Add panel
        add(basePanel);
        setVisible(true);

        // Actions
        calculateButton.addActionListener(e -> calculate());
        clearButton.addActionListener(e -> {
            inputX.setText("");
            inputY.setText("");
            resultArea.setText("");
            inputX.requestFocusInWindow();
        });
    }

    private JTextField createTextField(Font font, Color borderColor) {
        JTextField tf = new JTextField(15);
        tf.setFont(font);
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf.setBorder(new LineBorder(borderColor, 1, true));
        tf.setMaximumSize(new Dimension(200, 30));
        return tf;
    }

    private JPanel createLabeledField(String labelText, JTextField textField, Font font, Color bg) {
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setPreferredSize(new Dimension(30, 30));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panel.setBackground(bg);
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(color.darker(), 1, true));
        button.setPreferredSize(new Dimension(120, 35));
        return button;
    }

    private void styleComponent(JComponent comp, Font font, Color borderColor) {
        comp.setFont(font);
        comp.setPreferredSize(new Dimension(300, 30));
        comp.setMaximumSize(new Dimension(300, 30));
        comp.setBorder(new LineBorder(borderColor, 1, true));
    }

    private Component centered(Component c) {
        JPanel p = new JPanel();
        p.setBackground(new Color(255, 245, 230));
        p.setLayout(new FlowLayout(FlowLayout.CENTER));
        p.add(c);
        return p;
    }

    private void calculate() {
        try {
            double x = Double.parseDouble(inputX.getText());
            double y = Double.parseDouble(inputY.getText());
            String mode = (String) modeBox.getSelectedItem();

            Calculate strategy;
            String output;

            switch (mode) {
                case "What is X% of Y?" -> strategy = new XPercentOfY();
                case "X is what % of Y?" -> strategy = new XIsWhatPercentOfY();
                case "X is Y% of what?" -> strategy = new XIsYPercentOfWhat();
                default -> {
                    resultArea.setText("Unknown mode.");
                    return;
                }
            }

            double result = strategy.calculate(x, y);
            output = switch (mode) {
                case "What is X% of Y?" -> x + "% of " + y + " is " + result;
                case "X is what % of Y?" -> x + " is " + result + "% of " + y;
                case "X is Y% of what?" -> x + " is " + y + "% of " + result;
                default -> "Unknown mode.";
            };

            resultArea.setText(output);

        } catch (NumberFormatException e) {
            resultArea.setText("Please enter valid numbers for X and Y.");
        } catch (ArithmeticException e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PercentageCalculatorGUI::new);
    }
}
