import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSPSolution {
    private List<City> route;
    private double fitness = -1;

    public TSPSolution(List<City> cities) {
        // Inițializează ruta ca o nouă permutare a orașelor
        this.route = new ArrayList<>(cities);
        Collections.shuffle(this.route); // Amestecă ordinea orașelor pentru a crea o soluție inițială aleatoare
    }

    // Constructor de copiere pentru a crea noi soluții bazate pe soluții existente
    public TSPSolution(TSPSolution other) {
        this.route = new ArrayList<>(other.route);
        this.fitness = other.fitness;
    }

    // Calculează și returnează lungimea totală a rutei (fitness-ul)
    public double calculateFitness() {
        double totalDistance = 0;
        for (int i = 0; i < this.route.size(); i++) {
            City start = this.route.get(i);
            City end = this.route.get((i + 1) % this.route.size()); // Se întoarce la primul oraș după ultimul
            totalDistance += distance(start, end);
        }
        this.fitness = totalDistance;
        return this.fitness;
    }

    // Calculează distanța euclidiană între două orașe
    private double distance(City city1, City city2) {
        double dx = city1.getX() - city2.getX();
        double dy = city1.getY() - city2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public List<City> getRoute() {
        return route;
    }

    public double getFitness() {
        if (this.fitness == -1) {
            return calculateFitness();
        }
        return fitness;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route: ");
        for (City city : route) {
            sb.append(city.getIndex()).append(" -> ");
        }
        sb.append("Start");
        sb.append("\nTotal distance: ").append(String.format("%.2f", getFitness()));
        return sb.toString();
    }
}
