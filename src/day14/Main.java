package day14;

import util.Utility;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day14", "input1");
        for (int i = 0; i < inputs.get(0).length(); i++) {
            for (int j = 0; j < inputs.size(); j++) {
                String input = inputs.get(j);
                char cur = input.charAt(i);
                if ((cur == 'O') && (j > 0)) {
                    //shift me north
                    int counter = j - 1;
                    while (counter >= 0) {
                        if (inputs.get(counter).charAt(i) != '.') {
                            break;
                        }
                        counter--;
                    }
                    int newIdx = counter + 1;
                    if (newIdx != j) {
                        StringBuilder newStr = new StringBuilder(input);
                        newStr.setCharAt(i, '.');
                        inputs.set(j, newStr.toString());

                        StringBuilder newStr2 = new StringBuilder(inputs.get(newIdx));
                        newStr2.setCharAt(i, 'O');
                        inputs.set(newIdx, newStr2.toString());
                    }
                }
            }
        }
        int sum = 0;
        Collections.reverse(inputs);
        int multiplier = 1;
        for (String input : inputs) {
            sum += multiplier * input.chars().filter(c -> c == 'O').count();
            multiplier++;
        }
        System.out.println(sum);
    }
}