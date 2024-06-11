import java.util.*;

public class GeneticAlgorithm {
    private List<City> cities;
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int tournamentSize;
    private Random random;

    public GeneticAlgorithm(List<City> cities, int populationSize, double mutationRate, double crossoverRate, int tournamentSize) {
        this.cities = cities;
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.tournamentSize = tournamentSize;
        this.random = new Random();
    }

    public List<TSPSolution> initializePopulation() {
        List<TSPSolution> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            TSPSolution newSolution = new TSPSolution(cities);
            population.add(newSolution);
        }
        return population;
    }

    public void evaluatePopulation(List<TSPSolution> population) {
        for (TSPSolution solution : population) {
            solution.calculateFitness();
        }
    }

    public TSPSolution selectParent(List<TSPSolution> population) {
        Collections.shuffle(population);
        List<TSPSolution> tournament = population.subList(0, tournamentSize);
        return Collections.min(tournament, (solution1, solution2) -> {
            if (solution1.getFitness() < solution2.getFitness()) return -1;
            else if (solution1.getFitness() > solution2.getFitness()) return 1;
            return 0;
        });
    }

    public TSPSolution crossover(TSPSolution parent1, TSPSolution parent2) {
        return parent1;
    }

    public void mutate(TSPSolution solution) {
        if (random.nextDouble() < mutationRate) {
            int index1 = random.nextInt(solution.getRoute().size());
            int index2 = random.nextInt(solution.getRoute().size());

            City city1 = solution.getRoute().get(index1);
            City city2 = solution.getRoute().get(index2);

            solution.getRoute().set(index1, city2);
            solution.getRoute().set(index2, city1);
        }
    }

    //evolving to next gen pt ca selectam parinti noi ,
    //generam o pop noua de solutii
    public List<TSPSolution> evolvePopulation(List<TSPSolution> population) {
        List<TSPSolution> newPopulation = new ArrayList<>();


        for (int i = 0; i < populationSize; i++) {
            TSPSolution parent1 = selectParent(population);
            TSPSolution parent2 = selectParent(population);
            TSPSolution offspring = crossover(parent1, parent2);
            mutate(offspring);
            newPopulation.add(offspring);
        }

        return newPopulation;
    }

    public TSPSolution run() {
        List<TSPSolution> population = initializePopulation();
        evaluatePopulation(population);

        for (int generation = 0; generation < 100; generation++) {
            population = evolvePopulation(population);
            evaluatePopulation(population);
        }

        return Collections.min(population, Comparator.comparingDouble(TSPSolution::getFitness));
    }

}


