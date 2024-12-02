package day1;

import util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day1", "input1");
        System.out.println(part1(inputs));
        System.out.println(part2(inputs));
    }

    public static int part1(final List<String> inputs) {
        final List<Integer> left = new ArrayList<>();
        final List<Integer> right = new ArrayList<>();
        for (final String input : inputs) {
            final String[] split = input.split("\\s+");
            left.add(Integer.parseInt(split[0].trim()));
            right.add(Integer.parseInt(split[1].trim()));
        }
        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);
        return IntStream.range(0, left.size()).map(i -> Math.abs(left.get(i) - right.get(i))).sum();
    }

    public static int part2(final List<String> inputs) {
        final List<Integer> left = new ArrayList<>();
        final Map<Integer, Integer> right = new HashMap<>();
        for (final String input : inputs) {
            final String[] split = input.split("\\s+");
            left.add(Integer.parseInt(split[0].trim()));
            final Integer rightValue = Integer.parseInt(split[1].trim());
            right.put(rightValue, right.getOrDefault(rightValue, 0) + 1);
        }
        return left.stream().mapToInt(i -> i * right.getOrDefault(i, 0)).sum();
    }
}