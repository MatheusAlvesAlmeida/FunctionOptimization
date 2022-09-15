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
        double[] population = new double[31];
        for (int i = 0; i < 30; i++) {
            population[i] = (Math.random() * 30) - 15;
        }
        population[30] = 1.5;
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

    public double[] mutate(double[] population) {
        double[] Y = new double[31];
        double newSigma = population[30] * Math.exp((Math.random() * 2 - 1) * (1 / Math.sqrt(2 * d)));
        for (int i = 0; i < 30; i++) {
            Y[i] = population[i] + ((Math.random() * 2 - 1) * newSigma);
        }
        if (newSigma > 0.001) {
            Y[30] = newSigma;
        } else {
            Y[30] = 0.001;
        }
        return Y;
    }
}