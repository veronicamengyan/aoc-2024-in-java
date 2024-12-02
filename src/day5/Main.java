package day5;

import util.Utility;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day5", "input1");
        //part1(inputs);
        final String seedString = inputs.get(0).substring(inputs.get(0).indexOf(":") + 1).trim();
        List<Range> curs = new ArrayList<>();
        final List<Long> seedInput = Arrays.stream(seedString.split("\\s+")).map(Long::parseLong).collect(Collectors.toList());
        for (int i = 0; i < seedInput.size() - 1; i += 2) {
            final Long start = seedInput.get(i);
            final Long repeat = seedInput.get(i + 1);
            curs.add(new Range(start, start + repeat - 1));
        }

        List<Range> next = new ArrayList<>();
        for (int i = 1; i < inputs.size(); i++) {
            final String line = inputs.get(i);
            if (line.trim().isEmpty()) {
                continue;
            }

            // new map
            if (!line.matches(".*\\d.*") && next.size() > 0) {
                next.addAll(curs);
                curs = next;
                next = new ArrayList<>();
            } else if (line.matches(".*\\d.*")) {
                final String[] nums = line.split("\\s+");
                final long dest = Long.parseLong(nums[0]);
                final long srcStart = Long.parseLong(nums[1]);
                final long repeat = Long.parseLong(nums[2]);
                final Range mappingRange = new Range(srcStart, srcStart + repeat - 1);
                final long diff = dest - srcStart;

                final List<Range> rangesToAdd = new ArrayList<>();
                final Iterator<Range> iterator = curs.iterator();
                while (iterator.hasNext()) {
                    final Range cur = iterator.next();

                    // check for overlap
                    if (mappingRange.getStart() <= cur.getEnd() && cur.getStart() <= mappingRange.getEnd()) {
                        if (cur.getStart() < mappingRange.getStart()) {
                            rangesToAdd.add(new Range(cur.getStart(), mappingRange.getStart() - 1));
                        }
                        if (cur.getEnd() > mappingRange.getEnd()) {
                            rangesToAdd.add(new Range(mappingRange.getEnd() + 1, cur.getEnd()));
                        }
                        final Range intersect = cur.intersect(mappingRange);
                        next.add(new Range(intersect.getStart() + diff, intersect.getEnd() + diff));
                        iterator.remove();
                    }
                }
                curs.addAll(rangesToAdd);
            }
        }
        next.addAll(curs);
        System.out.println(Collections.min(next.stream().map(Range::getStart).toList()));
    }

    public static void part1(final List<String> inputs) {
        final String seedString = inputs.get(0).substring(inputs.get(0).indexOf(":") + 1).trim();

        List<Long> curs = Arrays.stream(seedString.split("\\s+")).map(Long::parseLong).collect(Collectors.toList());
        final List<Long> seedInput = Arrays.stream(seedString.split("\\s+")).map(Long::parseLong).collect(Collectors.toList());

        for (int i = 0; i < seedInput.size() - 1; i += 2) {
            final Long start = seedInput.get(i);
            final Long repeat = seedInput.get(i + 1);
            for (long j = 0; i < start + repeat; j++) {
                curs.add(j);
            }

        }

        List<Long> next = new ArrayList<>();
        for (int i = 1; i < inputs.size(); i++) {
            final String line = inputs.get(i);
            if (line.trim().isEmpty()) {
                continue;
            }

            // new map
            if (!line.matches(".*\\d.*") && next.size() > 0) {
                next.addAll(curs);
                curs = next;
                next = new ArrayList<>();
            } else if (line.matches(".*\\d.*")) {
                final String[] nums = line.split("\\s+");
                final long dest = Long.parseLong(nums[0]);
                final long src = Long.parseLong(nums[1]);
                final long repeat = Long.parseLong(nums[2]);

                final Iterator<Long> iterator = curs.iterator();
                while (iterator.hasNext()) {
                    final Long cur = iterator.next();
                    if (cur >= src && cur <= (src + repeat - 1)) {
                        next.add(dest + (cur - src));
                        iterator.remove();  // Use iterator's remove method
                    }
                }

            }

        }
        next.addAll(curs);
        System.out.println(Collections.min(next));
    }
}