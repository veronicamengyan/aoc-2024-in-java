package day3;

import util.Utility;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(final String[] args) throws IOException {
        final String inputs = Utility.readFileToString("day3", "input1");

        System.out.println(part1(inputs));
        System.out.println(part2(inputs));
    }

    private static int part1(final String inputs) {
        final String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(inputs);
        int sum = 0;
        while (matcher.find()) {
            final String firstNumber = matcher.group(1);
            final String secondNumber = matcher.group(2);
            sum += Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber);
        }
        return sum;
    }

    private static int part2(final String inputs) {
        final String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(inputs);
        int sum = 0;
        boolean enabled = true;
        while (matcher.find()) {
            final String match = matcher.group();
            if ("do()".equals(match)) {
                enabled = true;
            } else if ("don't()".equals(match)) {
                enabled = false;
            } else if ((matcher.group(1) != null) && (matcher.group(2) != null)) {
                final String firstNumber = matcher.group(1);
                final String secondNumber = matcher.group(2);
                if (enabled) {
                    sum += Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber);
                }
            }
        }
        return sum;
    }
}