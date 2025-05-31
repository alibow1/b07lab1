import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {
    double[] coefficients; // Non-zero coefficients.
    int[] exponents; // Exponents.

    // Set to default (0).
    public Polynomial( ) {
        coefficients = new double[1];
        coefficients[0] = 0.0; // HOW TO EXPRESS 0 WITH THE COEFFICIENT BEING NON-ZERO?
        exponents = new int[1];
        exponents[0] = 0;
    }

    public Polynomial(double[] inputCoefficients, int[] inputExponents) {
        coefficients = new double[inputCoefficients.length];
        exponents = new int[inputExponents.length];
        for (int i = 0; i < inputCoefficients.length; i++) {
            coefficients[i] = inputCoefficients[i];
            exponents[i] = inputExponents[i];
        }
    }

    /*
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
    */

    public Polynomial multiply(Polynomial other) {
        double[] resultTerms = new double[200];

        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                int newExp = exponents[i] + other.exponents[j];
                double newCoef = coefficients[i] * other.coefficients[j];
                resultTerms[newExp] += newCoef;
            }
        }

        int count = 0;
        for (int k = 0; k < resultTerms.length; k++) {
            if (resultTerms[k] != 0) count++;
        }

        double[] finalCoeffs = new double[count];
        int[] finalExps = new int[count];
        int idx = 0;
        for (int k = 0; k < resultTerms.length; k++) {
            if (resultTerms[k] != 0) {
                finalCoeffs[idx] = resultTerms[k];
                finalExps[idx] = k;
                idx++;
            }
        }

        return new Polynomial(finalCoeffs, finalExps);
    }

    public Polynomial (File f) throws IOException {
        Scanner scanner = new Scanner(f);
        String line = scanner.nextLine();
        scanner.close();

        double[] terms = new double[100]; // Support up to x^99

        int i = 0;
        while (i < line.length()) {
            int sign = 1;
            if (line.charAt(i) == '+') i++;
            else if (line.charAt(i) == '-') {
                sign = -1;
                i++;
            }

            StringBuilder coefStr = new StringBuilder();
            while (i < line.length() && Character.isDigit(line.charAt(i))) {
                coefStr.append(line.charAt(i));
                i++;
            }

            double coef = coefStr.length() > 0 ? Double.parseDouble(coefStr.toString()) * sign : 1.0 * sign;
            int exp = 0;

            if (i < line.length() && line.charAt(i) == 'x') {
                i++;
                StringBuilder expStr = new StringBuilder();
                while (i < line.length() && Character.isDigit(line.charAt(i))) {
                    expStr.append(line.charAt(i));
                    i++;
                }
                exp = expStr.length() > 0 ? Integer.parseInt(expStr.toString()) : 1;
            }

            terms[exp] += coef;
        }

        int count = 0;
        for (int k = 0; k < terms.length; k++) {
            if (terms[k] != 0) count++;
        }

        coefficients = new double[count];
        exponents = new int[count];
        int idx = 0;
        for (int k = 0; k < terms.length; k++) {
            if (terms[k] != 0) {
                coefficients[idx] = terms[k];
                exponents[idx] = k;
                idx++;
            }
        }
    }

    public void saveToFile (String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        StringBuilder polynomialText = new StringBuilder();

        for (int i = 0; i < coefficients.length; i++) {
            double coef = coefficients[i];
            int exp = exponents[i];

            if (coef == 0) continue;

            if (i > 0 && coef > 0) {
                polynomialText.append("+");
            }

            if (exp == 0) {
                polynomialText.append(coef);
            }
            else if (coef == 1.0) {
                polynomialText.append("x").append(exp);
            }
            else if (coef == -1.0) {
                polynomialText.append("-x").append(exp);
            }
            else {
                polynomialText.append(coef).append("x").append(exp);
            }
        }

        writer.write((polynomialText.toString()));
        writer.close();
    }
}