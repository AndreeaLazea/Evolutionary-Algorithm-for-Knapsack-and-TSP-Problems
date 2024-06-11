import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<City> cities = TSPReader.readCities("berlin52.tsp");
            GeneticAlgorithm ga = new GeneticAlgorithm(cities, 100, 0.01, 0.9, 3);
            TSPSolution bestSolution = ga.run();
            System.out.println("Best solution found: " + bestSolution);
        } catch (FileNotFoundException e) {
            System.err.println("Fișierul nu a fost găsit.");
            e.printStackTrace();
        }
    }
}

