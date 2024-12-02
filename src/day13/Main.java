package day13;

import util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day13", "input1");
        final List<List<String>> grids = new ArrayList<>();
        List<String> grid = new ArrayList<>();
        for (final String line : inputs) {
            if (line.isEmpty() && grid.size() > 0) {
                grids.add(grid);
                grid = new ArrayList<>();
            } else {
                grid.add(line);
            }
        }
        if (grid.size() > 0) {
            grids.add(grid);
        }

        int sum = 0;
        for (final List<String> block : grids) {

            // check horizontal
            int horizontalMirror = -1;
            for (int m = 1; m < block.size(); m++) {
                boolean isReflection = true;
                final int size = Math.min(m, block.size() - m);
                int diff = 0;
                for (int i = 0; i < size; i++) {
                    final String up = block.get(m - i - 1);
                    final String down = block.get(m + i);

                    //part1
//                    if (!up.equals(down)) {
//                        isReflection = false;
//                        break;
//                    }
                    diff += difference(up, down);
                }
                if (diff == 1) {
                    horizontalMirror = m;
                    break;
                }
            }
            if (horizontalMirror > 0) {
                sum += 100 * horizontalMirror;
            }

            //check vertical
            if (horizontalMirror == -1) {
                int verticalMirror = -1;
                for (int m = 1; m < block.get(0).length(); m++) {
                    boolean isReflection = true;
                    final int size = Math.min(m, block.get(0).length() - m);
                    int diff = 0;
                    for (int i = 0; i < size; i++) {
                        int finalM = m;
                        int finalI = i;
                        final String left = block.stream().map(str -> str.charAt(finalM - finalI - 1)).collect(Collectors.toList()).toString();
                        final String right = block.stream().map(str -> str.charAt(finalM + finalI)).collect(Collectors.toList()).toString();

                        //part1
//                        if (!left.equals(right)) {
//                            isReflection = false;
//                            break;
//                        }
                        diff += difference(left, right);
                    }
                    if (diff == 1) {
                        verticalMirror = m;
                    }
                }
                if (verticalMirror > 0) {
                    sum += verticalMirror;
                }
            }
        }


        System.out.println(sum);
    }

    private static int difference(String str1, String str2) {
        int i = 0;
        int j = 0;
        int diff = 0;
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) != str2.charAt(j)) {
                diff++;
            }
            i++;
            j++;
        }
        return diff;

    }
}