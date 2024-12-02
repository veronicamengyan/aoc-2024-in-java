package day4;

import util.Utility;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day4", "input1");
        part1(inputs);
        part2(inputs);
    }

    public static void part2(final List<String> inputs) {
        int cardNumber = 1;
        final Map<Integer, Integer> cardNumberToPoints = new HashMap<>();
        for (String input : inputs) {
            input = input.substring(input.indexOf(":") + 1);
            input = input.trim();
            final String[] splitStrs = input.split("\\|");
            final String winningNums = splitStrs[0];
            final String[] winningNumsArray = winningNums.split("\\s+");
            final Set<String> winningNumbers = new HashSet<>(Arrays.asList(winningNumsArray));
            final String numsIHave = splitStrs[1];
            final String[] numsIHaveArray = numsIHave.split("\\s+");
            int counter = 0;
            for (final String s : numsIHaveArray) {
                if (winningNumbers.contains(s)) {
                    counter++;
                }
            }
            if (counter > 0) {
                cardNumberToPoints.put(cardNumber, counter);
            }
            cardNumber++;
        }


        final Queue<Integer> cards = new LinkedList<>();

        // add all original cards to the queue
        for (int i = 1; i <= 196; i++) {
            cards.add(i);
        }

        int sum = 0;
        while (!cards.isEmpty()) {
            final int cur = cards.poll();
            sum += 1;
            if (cardNumberToPoints.containsKey(cur)) {
                final int counter = cardNumberToPoints.get(cur);
                for (int i = cur + 1; i <= (cur + counter); i++) {
                    cards.offer(i);
                }
            }
        }

        System.out.println(sum);
    }

    public static void part1(final List<String> inputs) {
        int sum = 0;
        for (String input : inputs) {
            input = input.substring(input.indexOf(":") + 1);
            input = input.trim();
            final String[] splitStrs = input.split("\\|");
            final String winningNums = splitStrs[0];
            final String[] winningNumsArray = winningNums.split("\\s+");
            final Set<String> winningNumbers = new HashSet<>(Arrays.asList(winningNumsArray));
            final String numsIHave = splitStrs[1];
            final String[] numsIHaveArray = numsIHave.split("\\s+");
            int counter = 0;
            for (String s : numsIHaveArray) {
                if (winningNumbers.contains(s)) {
                    counter++;
                }
            }
            if (counter > 0) {
                sum += Math.pow(2, counter - 1);
            }
        }

        System.out.println(sum);
    }
}