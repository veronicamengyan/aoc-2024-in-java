package day7;

import util.Utility;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(final String[] args) throws IOException {
        final List<String> inputs = Utility.readFile("day7", "input1");
        part1(inputs);
        final Map<CardType, List<Hand>> cardTypeHandMap = new TreeMap<>(Comparator.comparingInt(CardType::ordinal).reversed());
        for (final String hand : inputs) {
            final String[] cardBid = hand.split("\\s+");
            final String card = cardBid[0].trim();
            final int bid = Integer.parseInt(cardBid[1].trim());
            final CardType cardType = CardType.fromStringPart2(card);
            if (cardTypeHandMap.containsKey(cardType)) {
                cardTypeHandMap.get(cardType).add(new Hand(card, bid));
            } else {
                cardTypeHandMap.put(cardType, new ArrayList<>(List.of(new Hand(card, bid))));
            }
        }

        int sum = 0;
        int rank = 1;
        for (final List<Hand> hands : cardTypeHandMap.values()) {
            hands.sort((a, b) -> {
                for (int i = 0; i < a.getCard().length(); i++) {
                    final Comparator<Character> charComparator = Comparator
                            .<Character, Integer>comparing(c -> "AKQT98765432J".indexOf((char) c))
                            .reversed();
                    final int compare = charComparator.compare(a.getCard().charAt(i), b.getCard().charAt(i));
                    if (compare != 0) {
                        return compare;
                    }
                }
                return 0;
            });
            for (final Hand hand : hands) {
                sum += hand.getBid() * rank;
                rank++;
            }
        }

        System.out.println(sum);
    }

    public static void part1(final List<String> inputs) {
        final Map<CardType, List<Hand>> cardTypeHandMap = new TreeMap<>(Comparator.comparingInt(CardType::ordinal).reversed());
        for (final String hand : inputs) {
            final String[] cardBid = hand.split("\\s+");
            final String card = cardBid[0].trim();
            final int bid = Integer.parseInt(cardBid[1].trim());
            final CardType cardType = CardType.fromString(card);
            if (cardTypeHandMap.containsKey(cardType)) {
                cardTypeHandMap.get(cardType).add(new Hand(card, bid));
            } else {
                cardTypeHandMap.put(cardType, new ArrayList<>(List.of(new Hand(card, bid))));
            }
        }

        int sum = 0;
        int rank = 1;
        for (final List<Hand> hands : cardTypeHandMap.values()) {
            hands.sort((a, b) -> {
                for (int i = 0; i < a.getCard().length(); i++) {
                    Comparator<Character> charComparator = Comparator
                            .<Character, Integer>comparing(c -> "AKQJT98765432".indexOf((char) c))
                            .reversed();
                    final int compare = charComparator.compare(a.getCard().charAt(i), b.getCard().charAt(i));
                    if (compare != 0) {
                        return compare;
                    }
                }
                return 0;
            });
            for (final Hand hand : hands) {
                sum += hand.getBid() * rank;
                rank++;
            }
        }

        System.out.println(sum);
    }
}