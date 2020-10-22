package win.poker;

import java.util.ArrayList;


public class Player {

    private Card Card;
    private Ranking rank;

    public Player() {
    }

    public win.poker.Card getCard() {
        return Card;
    }

    public void setCard(win.poker.Card card) {
        Card = card;
    }

    public Ranking getRank() {
        return rank;
    }

    public void setRank(Ranking rank) {
        this.rank = rank;
    }
}
