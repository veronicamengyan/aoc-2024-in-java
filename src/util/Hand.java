package util;

public class Hand {
    private String card;
    private int bid;

    public Hand(final String card, final int bid) {
        this.card = card;
        this.bid = bid;
    }

    public String getCard() {
        return card;
    }

    public int getBid() {
        return bid;
    }
}
