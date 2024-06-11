import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TSPReader {
    public static List<City> readCities(String filePath) throws FileNotFoundException {
        List<City> cities = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        boolean startReading = false;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("NODE_COORD_SECTION")) {
                startReading = true;
                continue;
            } else if (line.contains("EOF")) {
                break;
            }
            if (startReading) {
                String[] parts = line.trim().split("\\s+");
                int cityIndex = Integer.parseInt(parts[0]);
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                cities.add(new City(cityIndex, x, y));
            }
        }
        scanner.close();
        return cities;
    }
}
