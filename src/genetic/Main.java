package genetic;

public class Main {
    public static void main(String[] args) {
        GeneticFunctions geneticFunctions = new GeneticFunctions();
        double[][] population = geneticFunctions.generatePopulation(200, 30);
        int i = 1;

        while (geneticFunctions.calculateFitness(population[0]) != 0) {
            System.out.println("Generation " + i);
            population = geneticFunctions.sortPopulation(population);
            // Select parents for crossover
            double[][] parents = geneticFunctions.selectParents(population);
            // Crossover
            double[][] children = geneticFunctions.crossover(parents);
            // Mutate
            double[][] mutatedChildren = geneticFunctions.mutate(children);
            // Replace worst individuals with children
            population = geneticFunctions.replaceWorstIndividuals(population, mutatedChildren);
            // Sort population
            population = geneticFunctions.sortPopulation(population);
            // Print best individual and fitness
            System.out.print("Best individual: ");
            for (int j = 0; j < population[0].length; j++) {
                System.out.print(population[0][j]);
            }
            System.out.println();
            System.out.println("Fitness: " + geneticFunctions.calculateFitness(population[0]));
            i++;
        }
    }
}
