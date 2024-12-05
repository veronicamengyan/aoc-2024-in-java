package day4;

import util.Utility;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day4", "input1");
        // initialize grid
        final char[][] grid = new char[inputs.size()][inputs.get(0).length()];
        for (int i = 0; i < inputs.size(); i++) {
            final String input = inputs.get(i);
            final char[] letters = input.toCharArray();
            for (int j = 0; j < letters.length; j++) {
                grid[i][j] = letters[j];
            }
        }
        part1(grid);
        part2(grid);
    }

    public static void part1(final char[][] grid) {
        int sum = 0;

        // check rows
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < (grid[0].length - 3); j++) {
                final String fourLetters = "" + grid[i][j] + grid[i][j + 1] + grid[i][j + 2] + grid[i][j + 3];
                //System.out.println(fourLetters);
                if ("XMAS".equals(fourLetters) || "SAMX".equals(fourLetters)) {
                    sum += 1;
                }
            }
        }

        // check columns
        for (int i = 0; i < (grid[0].length - 3); i++) {
            for (int j = 0; j < grid.length; j++) {
                final String fourLetters = "" + grid[i][j] + grid[i + 1][j] + grid[i + 2][j] + grid[i + 3][j];
                if ("XMAS".equals(fourLetters) || "SAMX".equals(fourLetters)) {
                    sum += 1;
                }
            }
        }

        // check diagonals
        for (int i = 0; i < (grid.length - 3); i++) {
            for (int j = 0; j < (grid[0].length - 3); j++) {
                final String fourLetters = "" + grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2] + grid[i + 3][j + 3];
                if ("XMAS".equals(fourLetters) || "SAMX".equals(fourLetters)) {
                    sum += 1;
                }
            }
        }

        // check the other diagonals
        for (int i = grid.length - 1; i >= 3; i--) {
            for (int j = 0; j < (grid[0].length - 3); j++) {
                final String fourLetters = "" + grid[i][j] + grid[i - 1][j + 1] + grid[i - 2][j + 2] + grid[i - 3][j + 3];
                if ("XMAS".equals(fourLetters) || "SAMX".equals(fourLetters)) {
                    sum += 1;
                }
            }
        }

        System.out.println(sum);
    }

    public static void part2(final char[][] grid) {
        final char[][] xmas1 = {
                {'M', '.', 'S' },
                {'.', 'A', '.' },
                {'M', '.', 'S' }
        };
        final char[][] xmas2 = {
                {'S', '.', 'M' },
                {'.', 'A', '.' },
                {'S', '.', 'M' }
        };
        final char[][] xmas3 = {
                {'S', '.', 'S' },
                {'.', 'A', '.' },
                {'M', '.', 'M' }
        };
        final char[][] xmas4 = {
                {'M', '.', 'M' },
                {'.', 'A', '.' },
                {'S', '.', 'S' }
        };
        int count = 0;
        for (int i = 0; i < (grid.length - 2); i++) {
            for (int j = 0; j < (grid[0].length - 2); j++) {
                if (isMatch(i, j, grid, xmas1) || isMatch(i, j, grid, xmas2) || isMatch(i, j, grid, xmas3) || isMatch(i, j, grid, xmas4)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean isMatch(final int i, final int j, final char[][] grid, final char[][] xmas) {
        // check 3 X 3 grid
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                if (((k == 1) && (l != 1)) || ((k != 1) && (l == 1))) {
                    continue;
                }
                if (grid[i + k][j + l] != xmas[k][l]) {
                    return false;
                }
            }
        }

        return true;
    }
}