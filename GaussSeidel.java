import java.util.Scanner;

public class GaussSeidel {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Digite o número de variáveis: ");
        int n = s.nextInt();

        double[][] A = new double[n][n];
        double[] b = new double[n];
        double[] x0 = new double[n];

        System.out.println("digite os coeficientes da matriz A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = s.nextDouble();
            }
        }

        System.out.println("digite os elementos do vetor b:");
        for (int i = 0; i < n; i++) {
            b[i] = s.nextDouble();
        }

        System.out.println("digite os valores iniciais do vetor x0:");
        for (int i = 0; i < n; i++) {
            x0[i] = s.nextDouble();
        }

        double tol = 1e-10;
        int maxIterations = 1000;

        long startTime = System.nanoTime();
        double[] solucao = gaussSeidel(A, b, x0, tol, maxIterations);
        long endTime = System.nanoTime();

        double execTime = (endTime - startTime) / 1e6;

        System.out.println("soluçao:");
        for (double v : solucao) {
            System.out.printf("%.6f ", v);
        }
        System.out.println("\nTempo de execuçao: " + execTime);

        s.close();
    }

    public static double[] gaussSeidel(double[][] A, double[] b, double[] x0, double tol, int maxIterations) {
        int n = b.length;
        double[] x = x0.clone();

        for (int k = 0; k < maxIterations; k++) {
            double[] xOld = x.clone();

            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum += A[i][j] * x[j];
                    }
                }
                x[i] = (b[i] - sum) / A[i][i];
            }
            if (normInf(x, xOld) < tol) {
                System.out.println("numero de iteraçoes " + (k + 1));
                return x;
            }
        }
        return x;
    }

    private static double normInf(double[] x, double[] xOld) {
        double max = 0;
        for (int i = 0; i < x.length; i++) {
            double diff = Math.abs(x[i] - xOld[i]);
            if (diff > max) {
                max = diff;
            }
        }
        return max;
    }
}
