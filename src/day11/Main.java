package day11;

import util.Utility;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day11", "input1");
        part1(inputs.get(0));

    }

    private static void part1(final String input) {
        final ArrayList<String> numbers = new ArrayList<>(Arrays.asList(input.split(" ")));
        Map<Long, Long> stoneCount = numbers.stream()
                .collect(Collectors.groupingBy(
                        Long::parseLong,
                        Collectors.counting()
                ));
        final int blink = 75;
        for (int j = blink; j > 0; j--) {
            final Map<Long, Long> updated = new HashMap<>();
            for (final Long key : stoneCount.keySet()) {
                transform(key, stoneCount, updated);
            }
            stoneCount = updated;
        }
        System.out.println(stoneCount.values().stream().mapToLong(Long::longValue).sum());
    }

    private static void transform(final Long stone, final Map<Long, Long> stoneCount, final Map<Long, Long> updated) {
        if (stone == 0) {
            updated.put(1L, stoneCount.get(0L));
            return;
        }
        final String stoneString = stone + "";
        if ((stoneString.length() % 2) == 0) {
            final long firstHalf = Integer.parseInt(stoneString.substring(0, (stoneString.length() / 2)));
            final long secondHalf = Long.parseLong(stoneString.substring(stoneString.length() / 2));
            if (firstHalf == secondHalf) {
                updated.put(firstHalf, updated.getOrDefault(firstHalf, 0L) + (stoneCount.getOrDefault(stone, 0L) * 2));
            } else {
                updated.put(firstHalf, updated.getOrDefault(firstHalf, 0L) + stoneCount.getOrDefault(stone, 0L));
                updated.put(secondHalf, updated.getOrDefault(secondHalf, 0L) + stoneCount.getOrDefault(stone, 0L));
            }
            return;
        }
        updated.put(stone * 2024, stoneCount.getOrDefault(stone, 0L));
    }
}

