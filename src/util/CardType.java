package util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public enum CardType {
    FIVE_OF_A_KIND,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    THREE_OF_A_KIND,
    TWO_PAIR,

    ONE_PAIR,
    HIGH_CARD;

    public static CardType fromString(final String card) {
        final Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < card.length(); i++) {
            charCount.put(card.charAt(i), charCount.getOrDefault(card.charAt(i), 0) + 1);
        }

        final int max = charCount.values().stream().max(Comparator.naturalOrder()).get();
        if (max == 5) {
            return FIVE_OF_A_KIND;
        } else if (max == 4) {
            return FOUR_OF_A_KIND;
        } else if (max == 3) {
            if (charCount.size() == 2) {
                return FULL_HOUSE;
            } else {
                return THREE_OF_A_KIND;
            }
        } else if (max == 2) {
            if (charCount.size() == 3) {
                return TWO_PAIR;
            } else {
                return ONE_PAIR;
            }
        } else {
            return HIGH_CARD;
        }
    }

    public static CardType fromStringPart2(final String card) {
        final Map<Character, Integer> charCount = new HashMap<>();
        int wildCardCount = 0;
        for (int i = 0; i < card.length(); i++) {
            if (card.charAt(i) == 'J') {
                wildCardCount++;
            } else {
                charCount.put(card.charAt(i), charCount.getOrDefault(card.charAt(i), 0) + 1);
            }
        }

        final int max = charCount.values().stream().max(Comparator.naturalOrder()).orElse(0) + wildCardCount;
        if (max == 5) {
            return FIVE_OF_A_KIND;
        } else if (max == 4) {
            return FOUR_OF_A_KIND;
        } else if (max == 3) {
            if (charCount.size() == 2) {
                return FULL_HOUSE;
            } else {
                return THREE_OF_A_KIND;
            }
        } else if (max == 2) {
            if (charCount.size() == 3) {
                return TWO_PAIR;
            } else {
                return ONE_PAIR;
            }
        } else {
            return HIGH_CARD;
        }
    }
}
