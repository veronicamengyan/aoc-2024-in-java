package day1;

import util.Utility;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day1", "input1");
        int sum = 0;
        for (String input : inputs) {

            // used for part 2
            input = input.replace("one", "on1ne");
            input = input.replace("two", "tw2wo");
            input = input.replace("three", "thre3hree");
            input = input.replace("four", "fou4our");
            input = input.replace("five", "fiv5ive");
            input = input.replace("six", "si6ix");
            input = input.replace("seven", "seve7even");
            input = input.replace("eight", "eigh8ight");
            input = input.replace("nine", "nin9ine");
            final char[] chars = input.toCharArray();
            int first = 0;
            int last = 0;

            //two pointers
            for (int i = 0, j = chars.length - 1; ((i < chars.length) && (j >= 0)); i++, j--) {
                if (Character.isDigit(chars[i]) && (first == 0)) {
                    first = Character.getNumericValue(chars[i]);
                }
                if (Character.isDigit(chars[j]) && (last == 0)) {
                    last = Character.getNumericValue(chars[j]);
                }
                if ((first > 0) & (last > 0)) {
                    break;
                }
            }
            sum += ((first * 10) + last);
        }
        System.out.println(sum);
    }
}