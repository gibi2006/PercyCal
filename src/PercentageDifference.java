public class PercentageDifference implements Calculate {
    @Override  
    public double calculate(double x, double y) { return Math.abs(x - y) / ((x + y) / 2); }
}
