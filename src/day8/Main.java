package day8;

import util.Pair;
import util.Utility;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(final String[] args) throws IOException {
        final char[][] grid = Utility.readFileIntoGrid("day8", "input1");
        part(grid, true);
        part(grid, false);
    }

    public static void part(final char[][] grid, final boolean isPart1) {
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
        for (final List<Pair> coordinates : antennas.values()) {
            // loop through any two coordinates
            for (int k = 0; k < coordinates.size(); k++) {
                for (int l = k + 1; l < coordinates.size(); l++) {
                    final Pair p1 = coordinates.get(k);
                    final Pair p2 = coordinates.get(l);

                    //calculate antinodes
                    final int xDiff = p1.getFirst() - p2.getFirst();
                    final int yDiff = p1.getSecond() - p2.getSecond();

                    if (isPart1) {
                        // antinode1
                        final int xAntinode = p1.getFirst() + xDiff;
                        final int yAntinode = p1.getSecond() + yDiff;

                        if (withinBounds(xAntinode, yAntinode, grid)) {
                            antinodes.add(new Pair(xAntinode, yAntinode));
                        }

                        // antinode2
                        final int xAntinode2 = p2.getFirst() - xDiff;
                        final int yAntinode2 = p2.getSecond() - yDiff;

                        if (withinBounds(xAntinode2, yAntinode2, grid)) {
                            antinodes.add(new Pair(xAntinode2, yAntinode2));
                        }
                    } else {
                        for (int i = 0; i < grid.length; i++) {
                            for (int j = 0; j < grid[0].length; j++) {
                                if (sameLine(i, j, xDiff, yDiff, p1.getFirst(), p1.getSecond())) {
                                    antinodes.add(new Pair(i, j));
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(antinodes.size());
    }

    private static boolean withinBounds(final int x, final int y, final char[][] grid) {
        return (x >= 0) && (x < grid.length) && (y >= 0) && (y < grid[0].length);
    }

    private static boolean sameLine(final int x, final int y, final int xDiff, final int yDiff, final int xAntenna, final int yAntenna) {
        return (xDiff * (y - yAntenna)) == (yDiff * (x - xAntenna));
    }
}