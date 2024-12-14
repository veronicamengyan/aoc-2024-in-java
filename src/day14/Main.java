package day14;

import util.Pair;
import util.Regex;
import util.Utility;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day14", "input1");
        final List<List<Pair>> robots = new ArrayList<>(); // <position, velocity>
        for (final String input : inputs) {
            final List<String> matches = Regex.findMatches(input, "p=(-?\\d+),(-?\\d+)\\s+v=(-?\\d+),(-?\\d+)");
            robots.add(new ArrayList<>(List.of(
                    new Pair(Integer.parseInt(matches.get(0)), Integer.parseInt(matches.get(1))),
                    new Pair(Integer.parseInt(matches.get(2)), Integer.parseInt(matches.get(3))))
            ));
        }

        // initialize grid
        final char[][] grid = new char[103][101];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = '.';
            }
        }

        //put robots on the grid
        for (final List<Pair> robot : robots) {
            final Pair position = robot.get(0);
            if (grid[position.getSecond()][position.getFirst()] == '.') {
                grid[position.getSecond()][position.getFirst()] = '1';
            } else {
                grid[position.getSecond()][position.getFirst()] += 1;
            }
        }

        //robot moves
        int seconds = 1;
        while (seconds <= 100000) {
            for (final List<Pair> robot : robots) {
                final Pair position = robot.get(0);
                final Pair velocity = robot.get(1);
                int newY = position.getFirst() + velocity.getFirst();
                int newX = position.getSecond() + velocity.getSecond();
                if (grid[position.getSecond()][position.getFirst()] == '1') {
                    grid[position.getSecond()][position.getFirst()] = '.';
                } else {
                    grid[position.getSecond()][position.getFirst()] -= 1;
                }
                if (newX < 0) {
                    newX += grid.length;
                }
                if (newX >= grid.length) {
                    newX -= grid.length;
                }
                if (newY < 0) {
                    newY += grid[0].length;
                }
                if (newY >= grid[0].length) {
                    newY -= grid[0].length;
                }
                if (grid[newX][newY] == '.') {
                    grid[newX][newY] = '1';
                } else {
                    grid[newX][newY] += 1;
                }
                robot.set(0, new Pair(newY, newX));
            }
            seconds++;

            //part 2
            if (hasLongAdjacentLine(grid)) {
                print2DCharArray(grid);
                System.out.println(seconds-1);
                return;
            }
        }

        //part 1
        final long sum = (long) countRobot(0, (grid.length - 1) / 2, 0, (grid[0].length - 1) / 2, grid)
                * countRobot(((grid.length - 1) / 2) + 1, grid.length, 0, (grid[0].length - 1) / 2, grid)
                * countRobot(0, (grid.length - 1) / 2, ((grid[0].length - 1) / 2) + 1, grid[0].length, grid)
                * countRobot(((grid.length - 1) / 2) + 1, grid.length, ((grid[0].length - 1) / 2) + 1, grid[0].length, grid);

        System.out.println(sum);
    }

    public static void print2DCharArray(final char[][] array) {
        for (final char[] row : array) {
            for (final char element : row) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

    public static boolean hasLongAdjacentLine(final char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            int count = 0;
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '.') {
                    count = 0; //reset count
                    continue;
                }
                count++;
                if (count >= 10) {
                    return true;
                }
            }
        }
        return false;
    }


    public static int countRobot(final int rowStart, final int rowEnd, final int colStart, final int colEnd, final char[][] grid) {
        int sum = 0;
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = colStart; j < colEnd; j++) {
                if (grid[i][j] != '.') {
                    sum += grid[i][j] - '0';
                }
            }
        }
        return sum;
    }
}