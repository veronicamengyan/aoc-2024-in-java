package day11;

import util.Pair;
import util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day11", "input1");
        part1(inputs);
        part2(inputs);

    }

    private static void part2(final List<String> inputs) {
        final List<String> expanded = new ArrayList<>();
        final Set<Integer> emptyRows = new HashSet<>();

        // expand row
        int rowCounter = 0;
        for (final String row : inputs) {
            boolean isEmptyRow = true;
            expanded.add(row);
            for (final Character col : row.toCharArray()) {
                if (col != '.') {
                    isEmptyRow = false;
                    break;
                }
            }
            if (isEmptyRow) {
                emptyRows.add(rowCounter);
            }
            rowCounter++;
        }

        final Set<Integer> emptyCols = new HashSet<>();
        //expand column
        for (int i = 0; i < expanded.get(0).length(); i++) {
            boolean isEmptyCol = true;
            for (int j = 0; j < expanded.size(); j++) {
                if (expanded.get(j).toCharArray()[i] != '.') {
                    isEmptyCol = false;
                    break;
                }
            }
            if (isEmptyCol) {
                emptyCols.add(i);
            }
        }

        final List<Pair> galaxies = new ArrayList<>();

        int rowIdx = 0;
        int colIdx;
        for (final String row : expanded) {
            colIdx = 0;
            for (final Character col : row.toCharArray()) {
                if (col == '#') {
                    galaxies.add(new Pair(rowIdx, colIdx));
                }
                colIdx++;
            }
            rowIdx++;
        }

        long sum = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            final Pair node1 = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                final Pair node2 = galaxies.get(j);
                final long millionModifiers = emptyRows.stream().filter(index -> index > Math.min(node1.getFirst(), node2.getFirst()) && index < Math.max(node1.getFirst(), node2.getFirst())).count()
                        + emptyCols.stream().filter(index -> index > Math.min(node1.getSecond(), node2.getSecond()) && index < Math.max(node1.getSecond(), node2.getSecond())).count();
                sum += Math.abs(node2.getSecond() - node1.getSecond()) + Math.abs(node2.getFirst() - node1.getFirst()) + millionModifiers * 999999;
            }
        }


        System.out.println(sum);
    }

    private static void part1(final List<String> inputs) {
        List<String> expanded = new ArrayList<>();

        // expand row
        for (final String row : inputs) {
            boolean isEmptyRow = true;
            expanded.add(row);
            for (final Character col : row.toCharArray()) {
                if (col != '.') {
                    isEmptyRow = false;
                    break;
                }
            }
            if (isEmptyRow) {
                expanded.add(row);
            }
        }

        //expand column
        for (int i = 0; i < expanded.get(0).length(); i++) {
            boolean isEmptyCol = true;
            for (int j = 0; j < expanded.size(); j++) {
                if (expanded.get(j).toCharArray()[i] != '.') {
                    isEmptyCol = false;
                    break;
                }
            }
            if (isEmptyCol) {
                final int insert = i;
                expanded = expanded.stream().map(str -> {
                    final StringBuilder stringBuffer = new StringBuilder(str);
                    stringBuffer.insert(insert, '.');
                    return stringBuffer.toString();
                }).toList();
                i++;
            }
        }

        final List<Pair> galaxies = new ArrayList<>();

        int rowIdx = 0;
        int colIdx;
        for (final String row : expanded) {
            colIdx = 0;
            for (final Character col : row.toCharArray()) {
                if (col == '#') {
                    galaxies.add(new Pair(rowIdx, colIdx));
                }
                colIdx++;
            }
            rowIdx++;
        }

        int sum = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            final Pair node1 = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                final Pair node2 = galaxies.get(j);
                sum += Math.abs(node2.getSecond() - node1.getSecond()) + Math.abs(node2.getFirst() - node1.getFirst());
            }
        }


        System.out.println(sum);
    }
}

