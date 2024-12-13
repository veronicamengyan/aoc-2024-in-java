package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Utility {

    public static List<String> readFile(final String day, final String fileName) throws IOException {
        final Path filePath = Path.of("src/" + day + "/" + fileName + ".txt");

        return Files.readAllLines(filePath);
    }

    public static char[][] readFileIntoGrid(final String day, final String fileName) throws IOException {
        final Path filePath = Path.of("src/" + day + "/" + fileName + ".txt");
        final List<String> lines = Files.readAllLines(filePath);
        final char[][] grid = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).trim().toCharArray();
        }
        return grid;
    }

    public static String readFileToString(final String day, final String fileName) throws IOException {
        final Path filePath = Path.of("src/" + day + "/" + fileName + ".txt");

        return Files.readString(filePath);
    }

    public static long findLCM(final long a, final long b) {
        // LCM(a, b) = |a * b| / GCD(a, b)
        return Math.abs(a * b) / calculateGCD(a, b);
    }

    // Function to calculate GCD using Euclidean algorithm
    public static long calculateGCD(final long a, final long b) {
        if (b == 0) {
            return a;
        }

        if (a == 0) {
            return b;
        }
        return calculateGCD(Math.max(a, b) % Math.min(a,b), Math.min(a,b));
    }
}
