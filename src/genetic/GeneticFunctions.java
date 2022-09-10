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

        for (int i = 0; i < d; i++) {
            sum1 += Math.pow(x[i], 2);
            sum2 += Math.cos(c * x[i]);
        }

        return -a * Math.exp(-b * Math.sqrt(sum1 / d)) - Math.exp(sum2 / d) + a + Math.exp(1);
    }

    // Sort population by fitness
    public double[][] sortPopulation(double[][] population) {
        double[][] sortedPopulation = new double[population.length][population[0].length];

        for (int i = 0; i < population.length; i++) {
            double[] individual = population[i];
            double fitness = calculateFitness(individual);

            for (int j = 0; j < population.length; j++) {
                if (calculateFitness(sortedPopulation[j]) < fitness) {
                    double[] temp = sortedPopulation[j];
                    sortedPopulation[j] = individual;
                    individual = temp;
                }
            }
        }

        return sortedPopulation;
    }

    // Select two parents by roulette wheel selection
    public double[][] selectParents(double[][] population) {
        double[][] parents = new double[2][population[0].length];
        double totalFitness = 0;

        for (int i = 0; i < population.length; i++) {
            totalFitness += calculateFitness(population[i]);
        }

        for (int i = 0; i < 2; i++) {
            double random = Math.random() * totalFitness;
            double sum = 0;

            for (int j = 0; j < population.length; j++) {
                sum += calculateFitness(population[j]);
                if (sum >= random) {
                    parents[i] = population[j];
                    break;
                }
            }
        }

        return parents;
    }

    // Crossover
    public double[][] crossover(double[][] parents) {
        double[][] children = new double[2][parents[0].length];
        double[][] parentsCopy = new double[2][parents[0].length];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < parents[0].length; j++) {
                parentsCopy[i][j] = parents[i][j];
            }
        }

        for (int i = 0; i < 2; i++) {
            double[] parent1 = parentsCopy[0];
            double[] parent2 = parentsCopy[1];
            double[] child = new double[parents[0].length];

            for (int j = 0; j < child.length; j++) {
                if (Math.random() < 0.5) {
                    child[j] = parent1[j];
                } else {
                    child[j] = parent2[j];
                }
            }

            children[i] = child;
        }

        return children;
    }

    // Mutation
    public double[][] mutate(double[][] children) {
        for (int i = 0; i < children.length; i++) {
            for (int j = 0; j < children[0].length; j++) {
                if (Math.random() < 0.1) {
                    children[i][j] = Math.random() * 30 - 15;
                }
            }
        }

        return children;
    }

    public double[][] replaceWorstIndividuals(double[][] population, double[][] children) {
        for (int i = 0; i < children.length; i++) {
            population[population.length - 1 - i] = children[i];
        }

        return population;
    }
}
