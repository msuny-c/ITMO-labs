public class Main {
        public static void main(String[] args) {
        int from = 3, to = 19;
        short[] c = new short[to - from + 1];
        for (int i = to; i >= from; i--) {
                c[to - i] = (short) i;
        }
        double[] x = new double[16];
        for (int i = 0; i < x.length; i++) {
                x[i] = Math.random() * 16 - 7;
        }
        int n = 17, m = 16;
        double[][] array = new double[n][m];
        for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
            switch (c[i]) {
                case 14 -> array[i][j] = func1(x[j]);
                case 3, 5, 8, 10, 12, 13, 15, 19 -> array[i][j] = func2(x[j]);
                default -> array[i][j] = func3(x[j]);
            }
                        System.out.printf("%.3f\t", array[i][j]);
                }
                System.out.println();
        }
}
        public static double pow(double a, double b) {
                return Math.pow(a, b);
        }
        public static double func1(double x) {
                var tempResult = pow((x - 1.0 / 3) / 3, x);
                tempResult = tempResult / (1 - Math.asin((x + 1) / 16));
                tempResult = pow(tempResult, 3);
                tempResult = Math.tan(tempResult);
                return tempResult;
        }
        public static double func2(double x) {
                var tempResult = 3 * pow(x, 2);
                tempResult = pow(tempResult, 2) - 2;
                var temp2 = pow((x - 1.0 / 2) / 3, 3);
                tempResult = pow(tempResult, pow(Math.E, temp2));
                return tempResult;

        }
        public static double func3(double x) {
                var tempResult = pow(Math.E, pow(2 * x, x));
                tempResult = pow(tempResult, 1.0 / 3);
                tempResult = pow(tempResult, 1.0 / 3);
                return tempResult;
        }
}


