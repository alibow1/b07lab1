public class Polynomial {
    double[] coefficients;

    public Polynomial() {
        coefficients = new double[1];
        coefficients[0] = 0.0;
    }

    public Polynomial(double[] inputCoefficients) {
        coefficients = new double[inputCoefficients.length];
        for (int i = 0; i < inputCoefficients.length; i++) {
            coefficients[i] = inputCoefficients[i];
        }
    }

    public Polynomial add(Polynomial other) {
        int maxLength;
        if (coefficients.length > other.coefficients.length) {
            maxLength = coefficients.length;
        } else {
            maxLength = other.coefficients.length;
        }

        double[] sum = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double value1 = 0.0;
            if (i < coefficients.length) {
                value1 = coefficients[i];
            }

            double value2 = 0.0;
            if (i < other.coefficients.length) {
                value2 = other.coefficients[i];
            }

            sum[i] = value1 + value2;
        }

        return new Polynomial(sum);
    }

    public double evaluate(double x) {
        double result = 0.0;

        for (int i = 0; i < coefficients.length; i++) {
            double term = coefficients[i] * Math.pow(x, i);
            result = result + term;
        }
        return result;
    }

    public boolean hasRoot(double x) {
        double value = evaluate(x);
        if (value == 0.0) {
            return true;
        } else {
            return false;
        }
    }
}
