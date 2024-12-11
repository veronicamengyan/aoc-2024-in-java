package day10;

import util.Pair;
import util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Main {
    public static void main(final String[] args) throws IOException {
        final char[][] inputs = Utility.readFileIntoGrid("day10", "input1");
        final List<Pair> trailHeads = new ArrayList<>();
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[0].length; j++) {
                if (inputs[i][j] == '0') {
                    trailHeads.add(new Pair(i, j));
                }
            }
        }

        //part 1
        int sum = 0;
        for (int i = 0; i < trailHeads.size(); i++) {
            final Set<Pair> reachableDestinations = new HashSet<>();
            findPaths(trailHeads.get(i), inputs, '0', reachableDestinations);
            sum += reachableDestinations.size();
        }
        System.out.println(sum);

        //part2
        int sum2 = 0;
        for (int i = 0; i < trailHeads.size(); i++) {
            sum2 += findRating(trailHeads.get(i), inputs, '0', 0);
        }
        System.out.println(sum2);
    }

    private static void findPaths(final Pair position, final char[][] inputs, final char startingLetter, final Set<Pair> reachableDestinations) {
        if ((position.getFirst() < 0) || (position.getFirst() >= inputs.length) || (position.getSecond() < 0) || (position.getSecond() >= inputs[0].length)) {
            return;
        }
        if ((startingLetter == '9') && (inputs[position.getFirst()][position.getSecond()] == '9')) {
            reachableDestinations.add(new Pair(position.getFirst(), position.getSecond()));
            return;
        }
        if (inputs[position.getFirst()][position.getSecond()] != startingLetter) {
            return;
        }
        final char nextLetter = (char) (startingLetter + 1);
        findPaths(new Pair(position.getFirst() + 1, position.getSecond()), inputs, nextLetter, reachableDestinations);
        findPaths(new Pair(position.getFirst() - 1, position.getSecond()), inputs, nextLetter, reachableDestinations);
        findPaths(new Pair(position.getFirst(), position.getSecond() + 1), inputs, nextLetter, reachableDestinations);
        findPaths(new Pair(position.getFirst(), position.getSecond() - 1), inputs, nextLetter, reachableDestinations);
    }

    private static int findRating(final Pair position, final char[][] inputs, final char startingLetter, final int rating) {
        if ((position.getFirst() < 0) || (position.getFirst() >= inputs.length) || (position.getSecond() < 0) || (position.getSecond() >= inputs[0].length)) {
            return rating;
        }
        if ((startingLetter == '9') && (inputs[position.getFirst()][position.getSecond()] == '9')) {
            return rating + 1;
        }
        if (inputs[position.getFirst()][position.getSecond()] != startingLetter) {
            return rating;
        }
        final char nextLetter = (char) (startingLetter + 1);
        return findRating(new Pair(position.getFirst() + 1, position.getSecond()), inputs, nextLetter, rating) +
                findRating(new Pair(position.getFirst() - 1, position.getSecond()), inputs, nextLetter, rating) +
                findRating(new Pair(position.getFirst(), position.getSecond() + 1), inputs, nextLetter, rating) +
                findRating(new Pair(position.getFirst(), position.getSecond() - 1), inputs, nextLetter, rating);
    }
}