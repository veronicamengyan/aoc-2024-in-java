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
        for (final String line : lines) {
            grid[lines.indexOf(line)] = line.toCharArray();
        }
        return grid;
    }

    public static String readFileToString(final String day, final String fileName) throws IOException {
        final Path filePath = Path.of("src/" + day + "/" + fileName + ".txt");

        return Files.readString(filePath);
    }
}
