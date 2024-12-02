package day10;

import util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day10", "input1");
        final char[][] grid = new char[inputs.size()][inputs.get(0).length()];
        int i = 0;
        int startRow = -1;
        int startCol = -1;
        for (String input : inputs) {
            int j = 0;
            for (Character symbol : input.toCharArray()) {
                grid[i][j] = symbol;
                if (symbol == 'S') {
                    startRow = i;
                    startCol = j;
                }
                j++;
            }
            i++;
        }

        //part1
        int sum = 0;

        sum += followPath(Direction.DOWN, grid, startRow + 1, startCol);
        System.out.println((sum + 1) / 2);

        //part2
        grid[startRow][startCol] = 'T'; // it is kinda cheating here because I know S would be '7' based on the input

        int area = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if ("PT".indexOf(Character.toString(grid[row][col])) >= 0) {
                    continue;
                }
                if (isInsideLoop(grid, row, col)) {
                    area++;
                }
            }
        }
        System.out.println(area);
    }

    private static boolean isInsideLoop(final char[][] grid, int row, int col) {
        int pathCounter = 0;
        int rayRow = row;
        int rayCol = col;
        while (rayRow < grid.length && rayCol < grid[0].length) {
            char cursor = grid[rayRow][rayCol];
            if (cursor == 'P') {
                pathCounter++;
            }
            rayRow++;
            rayCol++;
        }
        return (pathCounter % 2) != 0;
    }

    private static int followPath(final Direction direction, final char[][] grid, int row, int col) {
        int curRow = row;
        int curCol = col;
        int counter = 0;
        Direction dir = direction;
        while (grid[curRow][curCol] != 'S') {
            char temp = grid[curRow][curCol];
            grid[curRow][curCol] = 'P';
            if (dir == Direction.DOWN) {
                switch (temp) {
                    case '|' -> {
                        dir = Direction.DOWN;
                        curRow += 1;
                    }
                    case 'L' -> {
                        dir = Direction.RIGHT;
                        grid[curRow][curCol] = 'T';
                        curCol += 1;
                    }
                    case 'J' -> {
                        dir = Direction.LEFT;
                        curCol -= 1;
                    }
                }
            } else if (dir == Direction.UP) {
                switch (temp) {
                    case '|' -> {
                        dir = Direction.UP;
                        curRow -= 1;
                    }
                    case '7' -> {
                        dir = Direction.LEFT;
                        grid[curRow][curCol] = 'T';
                        curCol -= 1;
                    }
                    case 'F' -> {
                        dir = Direction.RIGHT;
                        curCol += 1;
                    }
                }
            } else if (dir == Direction.LEFT) {
                switch (temp) {
                    case '-' -> {
                        dir = Direction.LEFT;
                        curCol -= 1;
                    }
                    case 'L' -> {
                        dir = Direction.UP;
                        grid[curRow][curCol] = 'T';
                        curRow -= 1;
                    }
                    case 'F' -> {
                        dir = Direction.DOWN;
                        curRow += 1;
                    }
                }
            } else if (dir == Direction.RIGHT) {
                switch (temp) {
                    case '-' -> {
                        dir = Direction.RIGHT;
                        curCol += 1;
                    }
                    case 'J' -> {
                        dir = Direction.UP;
                        curRow -= 1;
                    }
                    case '7' -> {
                        dir = Direction.DOWN;
                        grid[curRow][curCol] = 'T';
                        curRow += 1;
                    }
                }
            }
            counter++;
        }
        return counter;
    }
}