package win.poker;

import java.util.ArrayList;

public class Card {

    private ArrayList<String> cards;
    private ArrayList<String> values;
    private ArrayList<String> suites;



    public Card(){

    }

    public Card(ArrayList<String> cards, ArrayList<String> values, ArrayList<String> suites) {
        this.cards = cards;
        this.values = values;
        this.suites = suites;
    }

    public ArrayList<String> getCards() {
        return cards;
    }

    public void setCards(ArrayList<String> cards) {
        this.cards = cards;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

    public ArrayList<String> getSuites() {
        return suites;
    }

    public void setSuites(ArrayList<String> suites) {
        this.suites = suites;
    }
}
