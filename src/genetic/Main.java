package genetic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GeneticFunctions geneticFunctions = new GeneticFunctions();
        double[][] population = geneticFunctions.generatePopulation(200, 30);
        int i = 1;
        try {
            File myObj = new File("fitness.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        FileWriter writer = new FileWriter("fitness.txt");
        while (geneticFunctions.calculateFitness(population[0]) > 0.001 && i < 200000) {
            System.out.println("\nGeneration " + i);
            population = geneticFunctions.sortPopulation(population);
            System.out.println();
            // Select parents for crossover
            double[][] parents = geneticFunctions.selectParents(population);
            double[][] children = geneticFunctions.crossover(parents);
            double[][] mutatedChildren = geneticFunctions.mutate(children);
            // Sort population and replace the worst 2 with the children
            population = geneticFunctions.sortPopulation(population);
            population = geneticFunctions.replaceWorstIndividuals(population,
                    mutatedChildren);
            System.out.println("Best fitness: " +
                    geneticFunctions.calculateFitness(population[0]));
            try {
                writer.write(geneticFunctions.calculateFitness(population[0]) + "\n");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            i++;
        }
        writer.close();
    }
}
