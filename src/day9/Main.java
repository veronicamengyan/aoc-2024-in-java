package day9;

import util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day9", "input1");
        long sum = 0;
        for (String input : inputs) {
            final List<Long> numbers = Arrays.stream(input.split("\\s+")).map(Long::parseLong).toList();
            final List<List<Long>> analysis = new ArrayList<>();
            boolean stop = false;
            analysis.add(numbers);
            int counter = 1;
            List<Long> current = numbers;
            while (!stop) {
                final List<Long> nextLayer = new ArrayList<>();
                for (int i = 0; i < (current.size() - 1); i++) {
                    nextLayer.add(current.get(i + 1) - current.get(i));
                }
                if (nextLayer.stream().noneMatch(i -> i != 0L)) {
                    stop = true;
                }
                analysis.add(counter, nextLayer);
                counter++;
                current = nextLayer;
            }
            for (int j = analysis.size() - 1; j > 0; j--) {
                List<Long> cur = analysis.get(j);
                List<Long> prev = new ArrayList<>(analysis.get(j - 1));
                //part1
                //prev.add(cur.get(cur.size() - 1) + prev.get(prev.size() - 1));

                //part2
                prev.add(0, prev.get(0) - cur.get(0));
                analysis.set(j - 1, prev);
            }
            //part 1
            //sum += analysis.get(0).get(analysis.get(0).size() - 1);

            //part 2
            sum += analysis.get(0).get(0);
        }

        System.out.println(sum);
    }
}