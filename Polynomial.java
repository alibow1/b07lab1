public class Polynomial {
    private double[] coefficients;

    // No-arg constructor: sets polynomial to zero
    public Polynomial() {
        this.coefficients = new double[]{0.0};
    }

    // Constructor: accepts array of coefficients
    public Polynomial(double[] coefficients) {
        // Defensive copy to avoid aliasing
        this.coefficients = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            this.coefficients[i] = coefficients[i];
        }
    }

    // Add another Polynomial to this one
    public Polynomial add(Polynomial other) {
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        double[] sum = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double a = i < this.coefficients.length ? this.coefficients[i] : 0.0;
            double b = i < other.coefficients.length ? other.coefficients[i] : 0.0;
            sum[i] = a + b;
        }

        return new Polynomial(sum);
    }

    // Evaluate the polynomial at x
    public double evaluate(double x) {
        double result = 0.0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    // Check if x is a root (i.e., f(x) == 0)
    public boolean hasRoot(double x) {
        return evaluate(x) == 0.0;
    }
}
