package day7;

import util.Utility;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day7", "input1");
        final char[] operations1 = {'+', '*' };
        final char[] operations2 = {'+', '*', 'c' };

        //part1
        part(inputs, operations1);

        //part2
        part(inputs, operations2);
    }

    public static void part(final List<String> inputs, final char[] operations) {
        long sum = 0;
        for (final String input : inputs) {
            final String[] parts = input.split(":");
            final long targetValue = Long.parseLong(parts[0]);
            final Long[] inputValues = Arrays.stream(parts[1].trim().split(" "))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .toArray(Long[]::new);
            if (correct(targetValue, inputValues, operations)) {
                sum += targetValue;
            }
        }
        System.out.println(sum);
    }

    public static boolean correct(final long targetValue, final Long[] inputValues, char[] operations) {
        return checkRecursively(targetValue, inputValues, 0, 0, operations);
    }

    public static boolean checkRecursively(final long targetValue, final Long[] inputValues, final long result, final int index, char[] operations) {
        if (index == inputValues.length) {
            return result == targetValue;
        }
        for (final char operation : operations) {
            boolean isValid;
            if (operation == '+') {
                isValid = checkRecursively(targetValue, inputValues, result + inputValues[index], index + 1, operations);
            } else if (operation == '*') {
                isValid = checkRecursively(targetValue, inputValues, result * inputValues[index], index + 1, operations);
            } else {
                isValid = checkRecursively(targetValue, inputValues, concatenate(result, inputValues[index]), index + 1, operations);
            }
            if (isValid) {
                return true;
            }
        }
        return false;
    }

    public static long concatenate(final long result, final long value) {
        return Long.parseLong(result + "" + value);
    }
}