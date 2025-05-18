public class XIsYPercentOfWhat implements Calculate {
    @Override
    public double calculate(double x, double y) { 
        double numerator = x;
        double denominator = (y * 0.01);
        
        if (denominator == 0) throw new ArithmeticException("Cannot divide by zero!");
        return numerator / (denominator); 
    }
}
