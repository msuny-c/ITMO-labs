import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {
        int from = 3, to = 19;
        int[] c = new int[to - from + 1];
        for (int i = to; i >= from; i--) {
            c[to - i] = i;
        }
        double[] x = new double[16];
        double minX = -7.0, maxX = 9.0;
        for (int i = 0; i < x.length; i++) {
            x[i] = random() * (maxX - minX) + minX;
        }
        int n = 17, m = 16;
        double[][] array = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = switch (c[i]) {
                    case 14 -> firstFunction(x[j]);
                    case 3, 5, 8, 10, 12, 13, 15, 19 -> secondFunction(x[j]);
                    default -> thirdFunction(x[j]);
                };
                System.out.printf("%.3f\t ", array[i][j]);
            }
            System.out.println();
        }
    }

    public static double firstFunction(double x) {
        double result = pow((x - 1.0 / 3) / 3, x);
        result = result / (1 - asin((x + 1) / 16));
        result = pow(result, 3);
        result = tan(result);
        return result;
    }

    public static double secondFunction(double x) {
        var result = 3 * pow(x, 2);
        result = pow(result, 2) - 2;
        var temp = pow((x - 1.0 / 2) / 3, 3);
        result = pow(result, exp(temp));
        return result;

    }

    public static double thirdFunction(double x) {
        var result = exp(pow(2 * x, x));
        result = pow(result, 1.0 / 3);
        result = pow(result, 1.0 / 3);
        return result;
    }
}

