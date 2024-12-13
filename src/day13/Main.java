package day13;

import util.Pair;
import util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day13", "input1");
        final List<List<String>> machines = new ArrayList<>();
        List<String> machine = new ArrayList<>();
        machines.add(machine);
        for (final String input : inputs) {
            if (input.trim().isEmpty()) {
                machine = new ArrayList<>();
                machines.add(machine);
            } else {
                machine.add(input);
            }
        }

        //part1
        System.out.println(machines.stream().mapToLong(Main::findTokens).sum());

        //part2
        System.out.println(machines.stream().mapToLong(Main::findTokens2).sum());
    }

    public static long findTokens(final List<String> machine) {
        final Pair A = findPair(machine.get(0), "Button A: X\\+(\\d+), Y\\+(\\d+)");
        final Pair B = findPair(machine.get(1), "Button B: X\\+(\\d+), Y\\+(\\d+)");
        final Pair prize = findPair(machine.get(2), "Prize: X\\=(\\d+), Y\\=(\\d+)");
        final int determinant1 = (A.getFirst() * B.getSecond()) - (A.getSecond() * B.getFirst());
        final int determinant2 = (prize.getFirst() * B.getSecond()) - (B.getFirst() * prize.getSecond());
        final int determinant3 = (A.getFirst() * prize.getSecond()) - (A.getSecond() * prize.getFirst());

        if (determinant2 % determinant1 != 0 || determinant3 % determinant1 != 0) {
            return 0;
        }

        final int x = determinant2 / determinant1;
        final int y = determinant3 / determinant1;

        return (3L * x) + y;
    }

    public static long findTokens2(final List<String> machine) {
        final Pair A = findPair(machine.get(0), "Button A: X\\+(\\d+), Y\\+(\\d+)");
        final Pair B = findPair(machine.get(1), "Button B: X\\+(\\d+), Y\\+(\\d+)");
        final Pair prize = findPair(machine.get(2), "Prize: X\\=(\\d+), Y\\=(\\d+)");
        final long bigNumber = 10000000000000L;
        final int determinant1 = (A.getFirst() * B.getSecond()) - (A.getSecond() * B.getFirst());
        final long determinant2 = ((prize.getFirst() + bigNumber) * B.getSecond()) - (B.getFirst() * (prize.getSecond() + bigNumber));
        final long determinant3 = (A.getFirst() * (prize.getSecond() + bigNumber)) - (A.getSecond() * (prize.getFirst() + bigNumber));
        if (((determinant2 % determinant1) != 0) || ((determinant3 % determinant1) != 0)) {
            return 0;
        }
        final long x = determinant2 / determinant1;
        final long y = determinant3 / determinant1;
        return (3 * x) + y;
    }

    public static Pair findPair(final String line, final String regex) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return new Pair(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        } else {
            throw new IllegalArgumentException("No match found");
        }
    }
}