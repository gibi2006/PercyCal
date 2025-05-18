import java.text.DecimalFormat;

public class CalculatorLogic {
    private final DecimalFormat plainFormat = new DecimalFormat("#,##0.00");
    private final DecimalFormat percentFormat = new DecimalFormat("#,##0.00'%'");

    public String[] calculate(String mode, double x, double y) {
        Calculate strategy;
        String output;
        String formula;

        switch (mode) {
            case "What is X% of Y?" -> {
                strategy = new XPercentOfY();
                formula = "Formula: (X / 100) * Y";
                double result = strategy.calculate(x, y);
                output = percentFormat.format(x) + " of " + plainFormat.format(y) + " is " + plainFormat.format(result);
            }
            case "X is what % of Y?" -> {
                strategy = new XIsWhatPercentOfY();
                formula = "Formula: (X / Y) * 100";
                double result = strategy.calculate(x, y);
                output = plainFormat.format(x) + " is " + percentFormat.format(result) + " of " + plainFormat.format(y);
            }
            case "X is Y% of what?" -> {
                strategy = new XIsYPercentOfWhat();
                formula = "Formula: X / (Y / 100)";
                double result = strategy.calculate(x, y);
                output = plainFormat.format(x) + " is " + percentFormat.format(y) + " of " + plainFormat.format(result) + ".";
            }
            case "% Difference between X and Y" -> {
                strategy = new PercentageDifference();
                formula = "Formula: |X - Y| / ((X + Y) / 2)";
                double result = strategy.calculate(x, y);
                output = "The percentage difference of " + plainFormat.format(x) + " and " + plainFormat.format(y) + " is " + percentFormat.format(result) + ".";
            }
            default -> {
                throw new IllegalArgumentException("Unknown mode: " + mode);
            }
        }

        return new String[] { output, formula };
    }
}
