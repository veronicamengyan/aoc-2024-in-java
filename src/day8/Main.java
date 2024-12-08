package day8;

import util.Pair;
import util.Utility;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(final String[] args) throws IOException {
        final char[][] grid = Utility.readFileIntoGrid("day8", "input1");
        part1(grid);
    }

    public static void part1(final char[][] grid) {
        final Map<Character, List<Pair>> antennas = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if ((grid[i][j] != '.') && Character.isLetterOrDigit(grid[i][j])) {
                    final List<Pair> coordinates = antennas.getOrDefault(grid[i][j], new ArrayList<>());
                    coordinates.add(new Pair(i, j));
                    antennas.put(grid[i][j], coordinates);
                }
            }
        }
        final Set<Pair> antinodes = new HashSet<>();
        for (final Character key : antennas.keySet()) {
            final List<Pair> coordinates = antennas.get(key);
            // loop through any two coordinates
            for (int k = 0; k < coordinates.size(); k++) {
                for (int l = k + 1; l < coordinates.size(); l++) {
                    final Pair p1 = coordinates.get(k);
                    final Pair p2 = coordinates.get(l);

                    //calculate antinodes
                    final int xDiff = p1.getFirst() - p2.getFirst();
                    final int yDiff = p1.getSecond() - p2.getSecond();

                    // antinode1
                    final int xAntinode = p1.getFirst() + xDiff;
                    final int yAntinode = p1.getSecond() + yDiff;

                    if ((xAntinode >= 0) && (xAntinode < grid.length) && (yAntinode >= 0) && (yAntinode < grid[0].length)) {
                        antinodes.add(new Pair(xAntinode, yAntinode));
                    }

                    // antinode2
                    final int xAntinode2 = p2.getFirst() - xDiff;
                    final int yAntinode2 = p2.getSecond() - yDiff;

                    if ((xAntinode2 >= 0) && (xAntinode2 < grid.length) && (yAntinode2 >= 0) && (yAntinode2 < grid[0].length)) {
                        antinodes.add(new Pair(xAntinode2, yAntinode2));
                    }
                }
            }
        }
        System.out.println(antinodes.size());
    }
}