package day5;

import util.Utility;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day5", "input1");
        final Map<Integer, Set<Integer>> reverseRules = new HashMap<>();
        final List<String> updates = new ArrayList<>();
        boolean isRule = true;
        for (final String input : inputs) {
            if (input.isEmpty()) {
                isRule = false;
                continue;
            }
            if (isRule) {
                final String[] transitiveRules = input.split("\\|");
                final int first = Integer.parseInt(transitiveRules[0]);
                final int second = Integer.parseInt(transitiveRules[1]);
                final Set<Integer> updatedReverseRule = reverseRules.getOrDefault(second, new HashSet<>());
                updatedReverseRule.add(first);
                reverseRules.put(second, updatedReverseRule);
            } else {
                updates.add(input);
            }
        }

        part1(updates, reverseRules);
        part2(updates, reverseRules);
    }

    public static void part1(final List<String> updates, final Map<Integer, Set<Integer>> reverseRules) {
        int sum = 0;
        for (final String update : updates) {
            final String[] numbers = update.split(",");
            if (isCorrect(numbers, reverseRules)) {
                sum += Integer.parseInt(numbers[(numbers.length - 1) / 2]);
            }
        }
        System.out.println(sum);
    }

    public static void part2(final List<String> updates, final Map<Integer, Set<Integer>> reverseRules) {
        int sum = 0;
        for (final String update : updates) {
            final String[] numbers = update.split(",");
            if (isCorrect(numbers, reverseRules)) {
                continue;
            }
            final List<Integer> sorted = Arrays.stream(numbers)
                    .map(Integer::parseInt)
                    .sorted((first, second) -> compareTo(first, second, reverseRules))
                    .toList();
            sum += sorted.get((sorted.size() - 1) / 2);
        }
        System.out.println(sum);
    }

    private static boolean isCorrect(final String[] numbers, final Map<Integer, Set<Integer>> reverseRules) {
        for (int i = 0; i < (numbers.length - 1); i++) {
            final int first = Integer.parseInt(numbers[i]);
            final int second = Integer.parseInt(numbers[i + 1]);
            final Set<Integer> before = reverseRules.get(first);
            if ((before != null) && before.contains(second)) {
                return false;
            }
        }
        return true;
    }

    private static int compareTo(final int first, final int second, final Map<Integer, Set<Integer>> reverseRules) {
        final Set<Integer> before = reverseRules.get(first);
        if ((before != null) && before.contains(second)) {
            return 1;
        } else {
            return -1;
        }
    }
}