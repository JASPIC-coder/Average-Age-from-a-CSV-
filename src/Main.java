
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static double calculateAverageAge(String csvFilePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(csvFilePath))) {
            return lines
                    .skip(1)
                    .map(line -> line.split(","))
                    .filter(arr -> arr.length >= 2)
                    .map(arr -> {
                        try {
                            return Integer.parseInt(arr[1].trim());
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    })
                    .filter(age -> age != null) 
                    .mapToDouble(age -> age)
                    .average()
                    .orElse(0.0);
        }
    }
    public static void main(String[] args) {
        String csvFilePath = "src/people.csv";
        try {
            double averageAge = calculateAverageAge(csvFilePath);
            System.out.printf("Average Age: %.2f%n", averageAge);
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }
}
