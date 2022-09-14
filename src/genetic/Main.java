package genetic;

public class Main {
    public static void main(String[] args) {
        GeneticFunctions geneticFunctions = new GeneticFunctions();
        double[][] population = geneticFunctions.generatePopulation(300, 30);
        int i = 1;
        while (geneticFunctions.calculateFitness(population[0]) > 0.0001) {
            System.out.println("\nGeneration " + i);
            population = geneticFunctions.sortPopulation(population);
            System.out.println();
            for (int j = 0; j < 2; j++) {
                // Select parents for crossover
                double[][] parents = geneticFunctions.selectParents(population);
                double[][] children = geneticFunctions.crossover(parents);
                double[][] mutatedChildren = geneticFunctions.mutate(children);
                // Sort population and replace the worst 2 with the children
                population = geneticFunctions.sortPopulation(population);
                population = geneticFunctions.replaceWorstIndividuals(population,
                        mutatedChildren);
            }
            // Sort population
            population = geneticFunctions.sortPopulation(population);
            // Print best individual and fitness
            System.out.print("Best individual: ");
            for (int j = 0; j < population[0].length; j++) {
                System.out.print(population[0][j] + " ");
            }
            System.out.println();
            System.out.println("Fitness: " +
                    geneticFunctions.calculateFitness(population[0]));
            i++;
        }
    }
}
