import javax.swing.*;

public class PercentageCalculator extends JFrame {
    JTextField inputX, inputY;
    JTextArea resultArea;
    JButton calculateBtn;

    public PercentageCalculator() {
        setTitle("What is X% of Y?");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel labelX = new JLabel("X (%):");
        labelX.setBounds(50, 30, 80, 25);
        add(labelX);

        inputX = new JTextField();
        inputX.setBounds(120, 30, 200, 25);
        add(inputX);

        JLabel labelY = new JLabel("Y:");
        labelY.setBounds(50, 70, 80, 25);
        add(labelY);

        inputY = new JTextField();
        inputY.setBounds(120, 70, 200, 25);
        add(inputY);

        calculateBtn = new JButton("Calculate");
        calculateBtn.setBounds(140, 110, 100, 30);
        add(calculateBtn);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setBounds(50, 160, 300, 30);
        add(resultArea);

        calculateBtn.addActionListener(e -> calculate());

        setVisible(true);
    }

    private void calculate() {
        try {
            double x = Double.parseDouble(inputX.getText());
            double y = Double.parseDouble(inputY.getText());
            double result = (x * 0.01) * y;
            resultArea.setText(x + "% of " + y + " is " + result);
        } catch (NumberFormatException ex) {
            resultArea.setText("Enter numbers ma'am???");
        }
    }

    public static void main(String[] args) {
        new PercentageCalculator();
    }
}
