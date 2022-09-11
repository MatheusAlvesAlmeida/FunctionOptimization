package genetic;

public class GeneticFunctions {
    private int a;
    private double b;
    private double c;

    public GeneticFunctions() {
        this.a = 20;
        this.b = 0.2;
        this.c = 2 * Math.PI;
    }

    // Generate population for ackley function
    public double[][] generatePopulation(int populationSize, int chromosomeSize) {
        double[][] population = new double[populationSize][chromosomeSize];
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < chromosomeSize; j++) {
                population[i][j] = Math.random() * 30 - 15;
            }
        }

        return population;
    }

    // Calculate ackley function
    public double calculateFitness(double[] x) {
        double sum1 = 0;
        double sum2 = 0;
        int d = x.length;

        for (int i = 0; i < d; i++) {
            sum1 += Math.pow(x[i], 2);
            sum2 += Math.cos(c * x[i]);
        }
        double term1 = -a * Math.exp(-b * Math.sqrt(sum1 / d));
        double term2 = -Math.exp(sum2 / d);

        return term1 + term2 + a + Math.exp(1);
    }

    // Sort population by fitness using selection sort
    public double[][] sortPopulation(double[][] population) {
        for (int i = 0; i < population.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < population.length; j++) {
                double fitness1 = calculateFitness(population[j]);
                double fitness2 = calculateFitness(population[minIndex]);
                if (fitness1 < fitness2) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                double[] temp = population[i];
                population[i] = population[minIndex];
                population[minIndex] = temp;
            }
        }

        return population;
    }

    // Select two parents by roulette wheel selection
    public double[][] selectParents(double[][] population) {
        double[][] parents = new double[10][population[0].length];
        double totalFitness = 0;

        for (int i = 0; i < population.length; i++) {
            totalFitness += calculateFitness(population[i]);
        }

        for (int i = 0; i < 10; i++) {
            double random = Math.random() * totalFitness;

            for (int j = 0; j < population.length; j++) {
                if (calculateFitness(population[j]) >= random) {
                    parents[i] = population[j];
                    break;
                }
            }
        }

        // Get two best parents
        double[][] bestParents = this.sortPopulation(parents);
        bestParents[0] = parents[0];
        bestParents[1] = parents[1];

        return bestParents;
    }

    // Crossover
    public double[][] crossover(double[][] parents) {
        double[][] children = new double[2][parents[0].length];
        int crossoverPoint = (int) (Math.random() * (parents[0].length - 1)) + 1;

        for (int i = 0; i < crossoverPoint; i++) {
            children[0][i] = parents[0][i];
            children[1][i] = parents[1][i];
        }

        for (int i = crossoverPoint; i < parents[0].length; i++) {
            children[0][i] = parents[1][i];
            children[1][i] = parents[0][i];
        }

        return children;
    }

    // Mutation
    public double[][] mutate(double[][] children) {
        for (int i = 0; i < children.length; i++) {
            for (int j = 0; j < children[0].length; j++) {
                if (Math.random() < 0.4) {
                    children[i][j] = Math.random() * 30 - 15;
                }
            }
        }

        return children;
    }

    public double[][] replaceWorstIndividuals(double[][] population, double[][] children) {
        for (int i = 0; i < children.length; i++) {
            if (calculateFitness(children[i]) < calculateFitness(population[population.length - 1])) {
                population[population.length - 1] = children[i];
            }
        }

        return population;
    }
}
