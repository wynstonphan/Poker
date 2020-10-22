package win.poker;

public class Ranking {

    private int rank;
    private int highestCard;
    private int highestPair;
    private int secondHighestPair;
    private int threeOfKind;
    private int fourOfKind;
    private int secondHighestCard;
    private int thirdHighestCard;


    public int getRank() {
        return rank;
    }

    public int getSecondHighestPair() {
        return secondHighestPair;
    }

    public void setSecondHighestPair(int secondHighestPair) {
        this.secondHighestPair = secondHighestPair;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getHighestCard() {
        return highestCard;
    }

    public void setHighestCard(int highestCard) {
        this.highestCard = highestCard;
    }

    public int getHighestPair() {
        return highestPair;
    }

    public void setHighestPair(int highestPair) {
        this.highestPair = highestPair;
    }

    public int getThreeOfKind() {
        return threeOfKind;
    }

    public void setThreeOfKind(int threeOfKind) {
        this.threeOfKind = threeOfKind;
    }

    public int getFourOfKind() {
        return fourOfKind;
    }

    public void setFourOfKind(int fourOfKind) {
        this.fourOfKind = fourOfKind;
    }

    public int getSecondHighestCard() {
        return secondHighestCard;
    }

    public void setSecondHighestCard(int secondHighestCard) {
        this.secondHighestCard = secondHighestCard;
    }

    public int getThirdHighestCard() {
        return thirdHighestCard;
    }

    public void setThirdHighestCard(int thirdHighestCard) {
        this.thirdHighestCard = thirdHighestCard;
    }

}
