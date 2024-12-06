package day6;

import util.Pair;
import util.Utility;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(final String[] args) throws IOException {
        solution();
    }

    public static void solution() throws IOException {
        final char[][] grid = Utility.readFileIntoGrid("day6", "input1");
        final Map<Integer, Pair> directions = Map.of(
                0, new Pair(-1, 0), //up
                1, new Pair(0, 1), //right
                2, new Pair(1, 0), //down
                3, new Pair(0, -1) //left
        );

        // find starting position
        int rowStart = 0;
        int colStart = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '^') {
                    rowStart = i;
                    colStart = j;
                    break;
                }
            }
        }

        //traverse the grid
        int direction = 0;
        int row = rowStart;
        int col = colStart;

        while ((row >= 0) && (row < grid.length) && (col >= 0) && (col < grid[0].length)) {
            final int dx = directions.get(direction).getFirst();
            final int dy = directions.get(direction).getSecond();
            if (((row + dx) < 0) || ((row + dx) >= grid.length) || ((col + dy) < 0) || ((col + dy) >= grid[0].length)) {
                break;
            }
            if (grid[row + dx][col + dy] == '#') {
                direction = (direction + 1) % 4;
            } else {
                row += dx;
                col += dy;
                grid[row][col] = 'X';
            }
        }

        // check path
        // used for part2
        final List<Pair> paths = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if ((grid[i][j] == 'X') || (grid[i][j] == '^')) {
                    sum++;
                    if (grid[i][j] == 'X') {
                        paths.add(new Pair(i, j));
                    }
                }
            }
        }

        System.out.println(sum);

        //part 2
        final Instant start = Instant.now();
        int solutions = 0;
        final Map<Integer, Character> directionMark = Map.of(
                0, '^', //up
                1, '@', //right
                2, '$', //down
                3, '%' //left
        );
        for (final Pair path : paths) {

            //get a fresh copy
            final char[][] grid2 = Utility.readFileIntoGrid("day6", "input1");
            direction = 0;
            row = rowStart;
            col = colStart;

            //put an obstacle in the path
            grid2[path.getFirst()][path.getSecond()] = '#';
            while ((row >= 0) && (row < grid2.length) && (col >= 0) && (col < grid2[0].length)) {
                final int dx = directions.get(direction).getFirst();
                final int dy = directions.get(direction).getSecond();
                if (((row + dx) < 0) || ((row + dx) >= grid2.length) || ((col + dy) < 0) || ((col + dy) >= grid2[0].length)) {
                    break;
                }
                if (grid2[row + dx][col + dy] == '#') {
                    direction = (direction + 1) % 4;
                } else {
                    row += dx;
                    col += dy;

                    //to check for loop, not only we want to check for the same position but also same direction
                    if (grid2[row][col] == directionMark.get(direction)) {
                        solutions++;
                        break;
                    }
                    grid2[row][col] = directionMark.get(direction);
                }
            }
        }
        final Instant end = Instant.now();
        final Duration duration = Duration.between(start, end);
        System.out.println("Code execution time: " + duration.getSeconds() + " seconds");
        System.out.println(solutions);
    }
}