package day8;

import util.Utility;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day8", "input1");
        part1(inputs);
        final String instruction = inputs.get(0);
        final Map<String, Node> map = new HashMap<>();
        for (int i = 1; i < inputs.size(); i++) {
            final String cur = inputs.get(i).trim();
            if (cur.isEmpty()) {
                continue;
            }
            final String[] line = cur.split("=");
            final String name = line[0].trim();
            final String[] neighbors = line[1].replaceAll("\\(", "").replaceAll("\\)", "").trim().split(",");
            final String leftName = neighbors[0].trim();
            final String rightName = neighbors[1].trim();
            final Node curNode = map.get(name);
            final Node left;
            if (map.get(leftName) == null) {
                left = new Node(leftName);
                map.put(leftName, left);
            } else {
                left = map.get(leftName);
            }
            final Node right;
            if (map.get(rightName) == null) {
                right = new Node(rightName);
                map.put(rightName, right);
            } else {
                right = map.get(rightName);
            }
            if (curNode != null) {
                curNode.setLeft(left);
                curNode.setRight(right);
            } else {
                map.put(name, new Node(name, left, right));
            }
        }
        int steps = 0;
        final List<Node> currents = new ArrayList<>();
        final List<Integer> stepCounter = new ArrayList<>();
        for (final String name : map.keySet()) {
            if (name.charAt(2) == 'A') {
                currents.add(map.get(name));
            }
        }
        for (int i = 0; i < currents.size(); i++) {
            int stepsForGhost = findSteps2(map, instruction, currents.get(i));
            stepCounter.add(stepsForGhost);
        }
        long lcm = 1;
        for (final Integer num : stepCounter) {
            lcm = findLCM(lcm, num);
        }
        System.out.println(lcm);
    }

    private static long findLCM(long a, long b) {
        // LCM(a, b) = |a * b| / GCD(a, b)
        return Math.abs(a * b) / calculateGCD(a, b);
    }

    // Function to calculate GCD using Euclidean algorithm
    private static long calculateGCD(long a, long b) {
        if (b == 0) {
            return a;
        }

        if (a == 0) {
            return b;
        }
        return calculateGCD(Math.max(a, b) % Math.min(a,b), Math.min(a,b));
    }

    public static void part1(final List<String> inputs) {
        final String instruction = inputs.get(0);
        final Map<String, Node> map = new HashMap<>();
        for (int i = 1; i < inputs.size(); i++) {
            final String cur = inputs.get(i).trim();
            if (cur.isEmpty()) {
                continue;
            }
            final String[] line = cur.split("=");
            final String name = line[0].trim();
            final String[] neighbors = line[1].replaceAll("\\(", "").replaceAll("\\)", "").trim().split(",");
            final String leftName = neighbors[0].trim();
            final String rightName = neighbors[1].trim();
            final Node curNode = map.get(name);
            final Node left;
            if (map.get(leftName) == null) {
                left = new Node(leftName);
                map.put(leftName, left);
            } else {
                left = map.get(leftName);
            }
            final Node right;
            if (map.get(rightName) == null) {
                right = new Node(rightName);
                map.put(rightName, right);
            } else {
                right = map.get(rightName);
            }
            if (curNode != null) {
                curNode.setLeft(left);
                curNode.setRight(right);
            } else {
                map.put(name, new Node(name, left, right));
            }
        }
        System.out.println(findSteps(map, instruction, map.get("AAA")));
    }

    private static int findSteps2(final Map<String, Node> map, final String instruction, final Node startNode) {
        int steps = 0;
        Node current = startNode;
        boolean found = false;
        while (!found) {
            for (final Character direction : instruction.toCharArray()) {
                if (current.getCurrent().charAt(2) == 'Z') {
                    found = true;
                    break;
                }
                if (direction == 'L') {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
                steps++;
            }
        }
        return steps;
    }

    private static int findSteps(final Map<String, Node> map, final String instruction, final Node startNode) {
        int steps = 0;
        Node current = startNode;
        boolean found = false;
        while (!found) {
            for (final Character direction : instruction.toCharArray()) {
                if ("ZZZ".equals(current.getCurrent())) {
                    found = true;
                    break;
                }
                if (direction == 'L') {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
                steps++;
            }
        }
        return steps;
    }
}