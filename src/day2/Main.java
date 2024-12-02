package day2;

import util.Utility;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day2", "input1");
        part1(inputs);
        part2(inputs);
    }

    //part 1

    private static void part1(final List<String> inputs) {
        int gameCount = 1;
        int sum = 0;
        for (final String input : inputs) {
            final String[] gameResults = input.split(":");
            final String[] games = gameResults[1].split(";");
            boolean isPossible = true;
            for (final String game : games) {
                final String[] cubes = game.split(",");
                for (final String cube : cubes) {
                    if (!checkCubes(cube.trim())) {
                        isPossible = false;
                    }
                }
            }
            if (isPossible) {
                sum += gameCount;
            }
            gameCount++;
        }
        System.out.println(sum);
    }

    // for part 1
    private static boolean checkCubes(final String cube) {
        final int redMax = 12;
        final int greenMax = 13;
        final int blueMax = 14;
        final String[] numColor = cube.split("\\s+");
        final int num = Integer.parseInt(numColor[0]);
        return switch (numColor[1]) {
            case "red" -> num <= redMax;
            case "green" -> num <= greenMax;
            case "blue" -> num <= blueMax;
            default -> throw new RuntimeException("parsing error");
        };
    }

    private static void part2(final List<String> inputs) {
        int sum = 0;
        for (final String input : inputs) {
            final Map<String, Integer> maxMap = new HashMap<>();
            maxMap.put("red", 0);
            maxMap.put("green", 0);
            maxMap.put("blue", 0);
            final String[] gameResults = input.split(":");
            final String[] games = gameResults[1].split(";");
            for (final String game : games) {
                final String[] cubes = game.split(",");
                for (final String cube : cubes) {
                    updateMap(maxMap, cube.trim());
                }
            }
            sum += maxMap.values().stream().reduce(1, (a, b) -> a * b);
        }
        System.out.println(sum);
    }

    // for part2
    private static void updateMap(final Map<String, Integer> maxMap, final String cube) {
        final String[] numColor = cube.split("\\s+");
        final int num = Integer.parseInt(numColor[0]);
        switch (numColor[1]) {
            case "red" -> updateValueIfGreater(maxMap, "red", num);
            case "green" -> updateValueIfGreater(maxMap, "green", num);
            case "blue" -> updateValueIfGreater(maxMap, "blue", num);
            default -> throw new RuntimeException("parsing error");
        }
    }

    private static void updateValueIfGreater(final Map<String, Integer> maxMap, final String key, final int num) {
        if (num > maxMap.get(key)) {
            maxMap.put(key, num);
        }
    }
}