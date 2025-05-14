import java.awt.*;
import javax.swing.*;

public class PercentageCalc extends JFrame {
    private JComboBox<String> calcType;
    private JTextField input1, input2;
    private JTextArea resultArea;
    private JButton calculateBtn;
    private JButton showFormulaBtn;

    public PercentageCalc() {
        setTitle("Percentage Calculator");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        calcType = new JComboBox<>(new String[] {
            "What is X% of Y?",
            "X is what % of Y?",
            "X is Y% of what?",
            "Percentage Difference",
            "Percentage Change"
        });

        topPanel.add(new JLabel("Calculation Type: "));
        topPanel.add(calcType);

        JPanel centerPanel = new JPanel(new GridLayout(4, 2));
        input1 = new JTextField();
        input2 = new JTextField();
        resultArea = new JTextArea(3, 30);
        resultArea.setEditable(false);
        centerPanel.add(new JLabel("Input 1 (X)"));
        centerPanel.add(input1);
        centerPanel.add(new JLabel("Input 2 (Y)"));
        centerPanel.add(input2);
        centerPanel.add(new JLabel("Result:"));
        centerPanel.add(new JScrollPane(resultArea));

        JPanel bottomPanel = new JPanel();
        calculateBtn = new JButton("Calculate");
        showFormulaBtn = new JButton("Show Formula");
        bottomPanel.add(calculateBtn);
        bottomPanel.add(showFormulaBtn);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        calculateBtn.addActionListener(e -> calculate());
        showFormulaBtn.addActionListener(e -> showFormula());

        setVisible(true);
    }

    private void calculate() {
        try {
            double x = Double.parseDouble(input1.getText());
            double y = Double.parseDouble(input2.getText());
            double result = 0;
            String selected = (String) calcType.getSelectedItem();

            switch (selected) {
                case "What is X% of Y?":
                    result = (x * 0.01) * y;
                    break;
                case "X is what % of Y?":
                    result = (x / y) * 100;
                    break;
                case "X is Y% of what?":
                    result = x / (y * 0.01);
                    break;
                case "Percentage Difference":
                    result = Math.abs(x - y) / ((x + y) / 2) * 100;
                    break;
                case "Percentage Change":
                    result = x * (1 + y / 100);
                    break;
            }

            resultArea.setText("Answer: " + result);
        } catch (ArithmeticException ex) {
            resultArea.setText("Divide by 0? 😱 Nadz shock face activated! Ataya gud.");
        } catch (Exception ex) {
            resultArea.setText("Error: Please enter valid numbers.");
        }
    }
    
    private void showFormula() {
        String selected = (String) calcType.getSelectedItem();
        String formula = switch (selected) {
            case "What is X% of Y?" -> "Z = (X * 0.01) * Y";
            case "X is what % of Y?" -> "Z = (X / Y) * 100";
            case "X is Y% of what?" -> "Z = X / (Y * 0.01)";
            case "Percentage Difference" -> "Z = |X - Y| / ((X + Y) / 2) * 100";
            case "Percentage Change" -> "Z = X * (1 ± Y / 100)";
            default -> "Unknown formula";
        };
        JOptionPane.showMessageDialog(this, "Formula:\n" + formula);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PercentageCalc::new);
    }
}