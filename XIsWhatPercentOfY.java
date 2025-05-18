public class XIsWhatPercentOfY implements Calculate {
    @Override
    public double calculate(double x, double y) { 
        if (y == 0) throw new ArithmeticException("Cannot divide by zero!");
        return (x / y) * 100; 
    }
}
