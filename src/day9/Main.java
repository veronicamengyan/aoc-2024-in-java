package day9;

import util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day9", "input1");
        int id = 0;
        boolean isFreeSpace = false;
        final List<Integer> disk = new ArrayList<>();
        final String input = inputs.get(0);

        //get disk by map
        for (int i = 0; i < input.length(); i++) {
            final int cur = Integer.parseInt(String.valueOf(input.charAt(i)));
            int counter = 0;
            while (counter < cur) {
                if (isFreeSpace) {
                    disk.add(-1);
                } else {
                    disk.add(id);
                }
                counter++;
            }
            if (!isFreeSpace) {
                id++;
            }
            isFreeSpace = !isFreeSpace;
        }

        //compact disk - part 1
//        for (int i = disk.size() - 1; i >= 0; i--) {
//            if (disk.get(i) != -1) {
//                final int freeIndex = findFirstFreeSpace(disk, i);
//                if (freeIndex == -1) {
//                    break;
//                }
//                disk.set(freeIndex, disk.get(i));
//                disk.set(i, -1);
//            }
//        }

        //compact disk - part 2
        for (int i = disk.size() - 1; i >= 0; i--) {
            if (disk.get(i) != -1) {

                //find file size
                int fileSize = 0;
                final int fileID = disk.get(i);
                for (int k = i; k >= 0; k--) {
                    if (disk.get(k) == fileID) {
                        fileSize++;
                    } else {
                        break;
                    }
                }
                final int freeIndex = findFirstFreeSpace2(disk, i, fileSize);
                if (freeIndex == -1) {
                    i = i - fileSize + 1;
                    continue;
                }
                for (int j = 0; j < fileSize; j++) {
                    disk.set(freeIndex + j, disk.get(i - j));
                    disk.set(i - j, -1);
                }
                i = i - fileSize + 1;
            }

        }

        System.out.println(disk);

        //checksum
        long sum = 0;
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == -1) {
                continue;
            }
            sum += (long) i * disk.get(i);
        }

        System.out.println(sum);
    }

    private static int findFirstFreeSpace2(final List<Integer> disk, final int cur, final int fileSize) {
        int index = -1;
        int tempSize = 0;
        for (int i = 0; i < disk.size(); i++) {
            if (i > cur) {
                return -1;
            }
            if (disk.get(i) == -1) {
                index = i;
                tempSize++;
            }
            if (tempSize == fileSize) {
                return (index - fileSize) + 1;
            }
            if (disk.get(i) != -1) {
                index = -1;
                tempSize = 0;
            }
        }
        return -1;
    }

    private static int findFirstFreeSpace(final List<Integer> disk, final int cur) {
        for (int i = 0; i < disk.size(); i++) {
            if (i > cur) {
                return -1;
            }
            if (disk.get(i) == -1) {
                return i;
            }
        }
        return -1;
    }
}