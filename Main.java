// Olayinka Olufowoshe
// N01487625
// Assignment 4
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Autocompleter autocompleter = new Autocompleter();
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the path to the text file containing words:");
        String filePath = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Loading words into the Autocompleter...");


            while ((line = br.readLine()) != null) {
                String word = line.trim().toLowerCase();
                if (word.matches("[a-zA-Z0-9]+")) {
                    autocompleter.insert(word);
                }
            }
            System.out.println("Words loaded successfully!");

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;  // Exit if file reading fails
        }


        while (true) {
            System.out.println("\nEnter a prefix (or type 'stop' to exit): ");
            String prefix = scanner.nextLine();
            if (prefix.equalsIgnoreCase("stop")) {
                break;
            }

            List<String> suggestions = autocompleter.getSuggestions(prefix);
            if (suggestions.isEmpty()) {
                System.out.println("No suggestions found.");
            } else {
                System.out.println("Suggestions: " + suggestions);
            }
        }

        scanner.close();
    }

}
