package day3;

import util.Utility;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day3", "input1");

        part1(inputs);
        part2(inputs);
    }

    private static void part2(final List<String> inputs) {
        final char[][] inputArray = new char[140][140];
        int i = 0;

        //store input in 2D array
        for (final String str : inputs) {
            int j = 0;
            for (final char symbol : str.toCharArray()) {
                inputArray[i][j] = symbol;
                j++;
            }
            i++;
        }

        int sum = 0;
        for (int k = 0; k < inputArray.length; k++) {
            for (int l = 0; l < inputArray[0].length; l++) {
                final char cur = inputArray[k][l];
                if (cur == '*') {
                    final Set<Integer> numbers = new HashSet<>();
                    checkForZeroAndUpdateSet(numbers, getNumAndUpdateMap(inputArray, k - 1, l));
                    checkForZeroAndUpdateSet(numbers, getNumAndUpdateMap(inputArray, k, l - 1));
                    checkForZeroAndUpdateSet(numbers, getNumAndUpdateMap(inputArray, k + 1, l));
                    checkForZeroAndUpdateSet(numbers, getNumAndUpdateMap(inputArray, k, l + 1));
                    checkForZeroAndUpdateSet(numbers, getNumAndUpdateMap(inputArray, k - 1, l - 1));
                    checkForZeroAndUpdateSet(numbers, getNumAndUpdateMap(inputArray, k + 1, l + 1));
                    checkForZeroAndUpdateSet(numbers, getNumAndUpdateMap(inputArray, k - 1, l + 1));
                    checkForZeroAndUpdateSet(numbers, getNumAndUpdateMap(inputArray, k + 1, l - 1));
                    inputArray[k][l] = '.';
                    if (numbers.size() == 2) {
                        sum += numbers.stream().reduce(1, (a, b) -> a * b);
                    }
                }
            }
        }
        System.out.println(sum);
    }

    private static void checkForZeroAndUpdateSet(Set<Integer> numbers, int value) {
        if (value > 0) {
            numbers.add(value);
        }
    }

    private static void part1(final List<String> inputs) {
        final char[][] inputArray = new char[140][140];
        int i = 0;

        //store input in 2D array
        for (final String str : inputs) {
            int j = 0;
            for (final char symbol : str.toCharArray()) {
                inputArray[i][j] = symbol;
                j++;
            }
            i++;
        }

        int sum = 0;
        for (int k = 0; k < inputArray.length; k++) {
            for (int l = 0; l < inputArray[0].length; l++) {
                final char cur = inputArray[k][l];
                if (cur == '.') {
                    continue;
                }
                if (isSymbol(cur)) {
                    sum += getNumAndUpdateMap(inputArray, k - 1, l);
                    sum += getNumAndUpdateMap(inputArray, k, l - 1);
                    sum += getNumAndUpdateMap(inputArray, k + 1, l);
                    sum += getNumAndUpdateMap(inputArray, k, l + 1);
                    sum += getNumAndUpdateMap(inputArray, k - 1, l - 1);
                    sum += getNumAndUpdateMap(inputArray, k + 1, l + 1);
                    sum += getNumAndUpdateMap(inputArray, k - 1, l + 1);
                    sum += getNumAndUpdateMap(inputArray, k + 1, l - 1);
                    inputArray[k][l] = '.';
                }
            }
        }
        System.out.println(sum);
    }

    private static boolean isSymbol(final char input) {
        return !Character.isLetterOrDigit(input) && !Character.isWhitespace(input);
    }

    //part 1
    private static int getNumAndUpdateMap(final char[][] inputArray, final int k, final int l) {
        if ((k < 0) || (k >= inputArray.length) || (l < 0) || (l > inputArray[0].length)) {
            return 0;
        }
        final char cur = inputArray[k][l];
        if (cur == '.') {
            return 0;
        }

        if (Character.isDigit(cur)) {
            StringBuilder num = new StringBuilder(String.valueOf(cur));
            for (int m = l + 1; m < inputArray[k].length; m++) {
                final char cursor = inputArray[k][m];
                if (Character.isDigit(cursor)) {
                    num = num.append(cursor);
                    inputArray[k][m] = '.';
                }
                if ((cursor == '.') || isSymbol(cursor)) {
                    break;
                }
            }
            for (int m = l - 1; m >= 0; m--) {
                final char cursor = inputArray[k][m];
                if (Character.isDigit(cursor)) {
                    num = num.insert(0, cursor);
                    inputArray[k][m] = '.';
                }
                if ((cursor == '.') || isSymbol(cursor)) {
                    break;
                }
            }
            return Integer.parseInt(num.toString());
        }
        return 0;
    }
}