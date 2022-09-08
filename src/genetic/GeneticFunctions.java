package genetic;

public class GeneticFunctions {
    private int a;
    private double b;
    private double c;
    private double d;

    public GeneticFunctions() {
        this.a = 20;
        this.b = 0.2;
        this.c = 2 * Math.PI;
        this.d = 30;
    }

    // Generate population for ackley function
    public double[][] generatePopulation() {
        double[][] population = new double[30][30];

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                population[i][j] = Math.random();
            }
        }

        return population;
    }

    // Calculate fitness for ackley function
    public double[] calculateFitness(double[][] population) {
        double[] fitness = new double[30];

        for (int i = 0; i < 30; i++) {
            fitness[i] = this.ackleyFunction(population[i]);
        }

        return fitness;
    }

    // Calculate ackley function
    public double ackleyFunction(double[] x) {
        double sum1 = 0;
        double sum2 = 0;

        for (int i = 0; i < d; i++) {
            sum1 += Math.pow(x[i], 2);
            sum2 += Math.cos(c * x[i]);
        }

        return -a * Math.exp(-b * Math.sqrt(sum1 / d)) - Math.exp(sum2 / d) + a + Math.exp(1);
    }

    // Select parents for crossover
}
