package day2;

import util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day2", "input1");
        part1(inputs);
        part2(inputs);
    }

    private static void part1(final List<String> inputs) {
        int safeCount = 0;
        for (final String input : inputs) {
            final String[] split = input.split("\\s+");
            final List<Integer> report = Arrays.stream(split).map(Integer::parseInt).toList();
            if (isSafe(report)) {
                safeCount++;
            }
        }
        System.out.println(safeCount);
    }

    private static boolean isSafe(final List<Integer> report) {
        final int firstNum = report.get(0);
        final int secondNum = report.get(1);
        final boolean isIncreasing = firstNum < secondNum;
        boolean isSafe = true;
        for (int i = 0; i < (report.size() - 1); i++) {
            final int current = report.get(i);
            final int next = report.get(i + 1);
            if (isIncreasing && (current > next)) {
                isSafe = false;
                break;
            }
            if (!isIncreasing && (current < next)) {
                isSafe = false;
                break;
            }
            if ((Math.abs(current - next) > 3) || (Math.abs(current - next) < 1)) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    private static void part2(final List<String> inputs) {
        int safeCount = 0;
        for (final String input : inputs) {
            final String[] split = input.split("\\s+");
            final List<Integer> report = Arrays.stream(split).map(Integer::parseInt).toList();
            for (int i = 0; i < report.size(); i++) {
                final List<Integer> currentReport = new ArrayList<>(report);
                currentReport.remove(i);
                if (isSafe(currentReport)) {
                    safeCount++;
                    break;
                }
            }

        }
        System.out.println(safeCount);
    }
}