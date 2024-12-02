package day6;

import util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day6", "input1");
        part1(inputs);

        //part2
        final long time = Long.parseLong(inputs.get(0).substring(inputs.get(0).indexOf(":") + 1).trim().replaceAll("\\s", ""));
        final long distance = Long.parseLong(inputs.get(1).substring(inputs.get(1).indexOf(":") + 1).trim().replaceAll("\\s", ""));
        int ways = 0;
        for (long t = 1; t < time; t++) {
            final long y = (time - t) * t;
            if (y > distance) {
                ways++;
            }
        }

        System.out.println(ways);
    }

    public static void part1(final List<String> inputs) {
        final String[] timeStr = inputs.get(0).substring(inputs.get(0).indexOf(":") + 1).trim().split("\\s+");
        final String[] distanceStr = inputs.get(1).substring(inputs.get(1).indexOf(":") + 1).trim().split("\\s+");
        final List<Race> races = new ArrayList<>();
        for (int i = 0; i < timeStr.length; i++) {
            races.add(new Race(Integer.parseInt(timeStr[i]), Integer.parseInt(distanceStr[i])));
        }
        int sum = 1;
        for (final Race race : races) {
            int ways = 0;
            for (int t = 1; t < race.getTime(); t++) {
                final int y = (race.getTime() - t) * t;
                if (y > race.getDistance()) {
                    ways++;
                }
            }
            sum *= ways;
        }

        System.out.println(sum);
    }
}