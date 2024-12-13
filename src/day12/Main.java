package day12;

import util.Pair;
import util.Utility;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
    public static void main(final String[] args) throws IOException {
        final char[][] inputs = Utility.readFileIntoGrid("day12", "input1");
        final Set<Set<Pair>> islands = new HashSet<>();
        final Set<Pair> visited = new HashSet<>();
        final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[0].length; j++) {
                if (!visited.contains(new Pair(i, j))) {
                    islands.add(bfs(inputs, i, j, visited, directions));
                }
            }
        }

        //part1
        System.out.println(islands.stream().mapToLong(island -> getArea(island) * getPerimeter(island, directions)).sum());

        //part2
        System.out.println(islands.stream().mapToLong(island -> getArea(island) * getSides(island)).sum());
    }

    public static Set<Pair> bfs(final char[][] inputs, final int i, final int j, final Set<Pair> visited, final int[][] directions) {
        visited.add(new Pair(i, j));
        final Set<Pair> island = new HashSet<>();
        final Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(i, j));
        island.add(new Pair(i, j));
        while (!queue.isEmpty()) {
            final Pair coordinate = queue.poll();
            for (final int[] direction : directions) {
                final int x = coordinate.getFirst() + direction[0];
                final int y = coordinate.getSecond() + direction[1];
                if ((x >= 0) && (x < inputs.length) && (y >= 0) && (y < inputs[0].length) && (inputs[x][y] == inputs[i][j]) && !visited.contains(new Pair(x, y))) {
                    queue.add(new Pair(x, y));
                    visited.add(new Pair(x, y));
                    island.add(new Pair(x, y));
                }
            }
        }
        return island;
    }

    public static long getArea(final Set<Pair> island) {
        return island.size();
    }

    public static long getPerimeter(final Set<Pair> island, final int[][] directions) {
        long perimeter = 0;
        for (final Pair coordinate : island) {
            for (final int[] direction : directions) {
                final int x = coordinate.getFirst() + direction[0];
                final int y = coordinate.getSecond() + direction[1];
                if (!island.contains(new Pair(x, y))) {
                    perimeter++;
                }
            }
        }
        return perimeter;
    }

    public static long getSides(final Set<Pair> island) {
        long sides = 0;
        for (final Pair coordinate : island) {
            sides += getCorners(coordinate.getFirst(), coordinate.getSecond(), island);
        }
        return sides;
    }

    public static int getCorners(final int x, final int y, final Set<Pair> island) {
        final int[][] SW = {{1, 0}, {0, -1}, {1, -1}}; //last one is diagonal
        final int[][] SE = {{1, 0}, {0, 1}, {1, 1}};
        final int[][] NW = {{-1, 0}, {0, -1}, {-1, -1}};
        final int[][] NE = {{-1, 0}, {0, 1}, {-1, 1}};
        return isCorner(x, y, island, SW) + isCorner(x, y, island, SE) + isCorner(x, y, island, NW) + isCorner(x, y, island, NE);
    }

    public static int isCorner(final int x, final int y, final Set<Pair> island, final int[][] directions) {
        final int x1 = x + directions[0][0];
        final int y1 = y + directions[0][1];
        final int x2 = x + directions[1][0];
        final int y2 = y + directions[1][1];
        final int x3 = x + directions[2][0];
        final int y3 = y + directions[2][1];
        if (!island.contains(new Pair(x1, y1)) && !island.contains(new Pair(x2, y2))) {
            return 1;
        }

        if (island.contains(new Pair(x1, y1)) && island.contains(new Pair(x2, y2)) && !island.contains(new Pair(x3, y3))) {
            return 1;
        }

        return 0;
    }
}