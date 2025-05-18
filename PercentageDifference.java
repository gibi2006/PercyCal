public class PercentageDifference implements Calculate {
    @Override  
    public double calculate(double x, double y) { 
        double numerator = Math.abs(x - y);
        double denominator = (x + y) / 2;

        if (numerator == 0 && denominator == 0) {
            throw new ArithmeticException("Cannot compute percentage difference: both X and Y are zero.");
        }

        return (numerator / denominator) * 100;
    }
}
