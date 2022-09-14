package strategy;

public class StrategyFunctions {
    private int a;
    private double b;
    private double c;
    private int d;

    public StrategyFunctions() {
        this.a = 20;
        this.b = 0.2;
        this.c = 2 * Math.PI;
        this.d = 30;
    }

    public double[] createStartPoint() {
        double[] population = new double[30];
        for (int i = 0; i < 30; i++) {
            population[i] = (Math.random() * 30) - 15;
        }
        return population;
    }

    // Calculate ackley function
    public double calculateFitness(double[] x) {
        double sum1 = 0;
        double sum2 = 0;

        for (int i = 0; i < d; i++) {
            sum1 += Math.pow(x[i], 2);
            sum2 += Math.cos(c * x[i]);
        }
        double term1 = -a * Math.exp(-b * Math.sqrt(sum1 / d));
        double term2 = -Math.exp(sum2 / d);

        return term1 + term2 + a + Math.exp(1);
    }

    public double[] calculateNormalDistribution(double[] x, double sigma) {
        double[] Z = new double[x.length];
        double normalization = 1 / (sigma * Math.sqrt(2 * Math.PI));
        double averageDistribution = 0;
        for (int i = 0; i < x.length; i++) {
            averageDistribution += x[i];
        }
        averageDistribution /= x.length;
        for (int i = 0; i < x.length; i++) {
            Z[i] = normalization * Math.exp(-Math.pow(x[i] - averageDistribution, 2) / (2 * Math.pow(sigma, 2)));
        }
        return Z;
    }

    public double[] mutate(double[] x, double sigma) {
        double[] Z = calculateNormalDistribution(x, sigma);
        double[] Y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            Y[i] = x[i] + Z[i];
        }
        return Y;
    }

    // Calculate new sigma based by the 1/5 rule
    public double calculateSigma(double sigma, double[] x) {
        double average = 0;
        for (int i = 0; i < x.length; i++) {
            average += x[i];
        }
        average /= x.length;
        if (average > 0.2) {
            return sigma * 1.2;
        } else if (average < 0.2) {
            return sigma * 0.8;
        } else {
            return sigma;
        }
    }

}