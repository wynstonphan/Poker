package win.poker;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        int player1Point = 0;
        int player2Point = 0;

        File file= new File("poker-hands.txt");
        Scanner myFile = new Scanner(file);

        while(myFile.hasNextLine()){
            String data = myFile.nextLine();
            String[] cardData = data.split(" ");

            if(dealCard(cardData) == 1)
                player1Point++;
            else
                player2Point++;
        }

        System.out.println("Player 1: " + player1Point);
        System.out.println("Player 2: " + player2Point);

        }

    private static int dealCard(String[] card) {
        ArrayList<String> cardPlayer1= new ArrayList<>();
        ArrayList<String> cardPlayer2= new ArrayList<>();
        ArrayList<String> player1Value = new ArrayList<>();
        ArrayList<String> player1Suits = new ArrayList<>();
        ArrayList<String> player2Value = new ArrayList<>();
        ArrayList<String> player2Suits = new ArrayList<>();


        for (int i = 0; i <10; i++){
            if (i<5)
                cardPlayer1.add(card[i]);
            if ( i>4){
                cardPlayer2.add(card[i]);
            }
        }

        for (int i = 0; i<5;i++){
            player1Value.add(cardPlayer1.get(i).substring(0,1));
            player1Suits.add(cardPlayer1.get(i).substring(1,2));
        }
        for (int i = 0; i<5;i++){
            player2Value.add(cardPlayer2.get(i).substring(0,1));
            player2Suits.add(cardPlayer2.get(i).substring(1,2));
        }

        Card card1 = new Card(cardPlayer1,player1Value,player1Suits);
        Card card2 = new Card(cardPlayer2,player2Value,player2Suits);

        Player player1 = new Player();
        Player player2 = new Player();

        player1.setCard(card1);
        player2.setCard(card2);

        player1.setRank(rankingPlayer(player1));
        player2.setRank(rankingPlayer(player2));

        int rankPlayer1 = player1.getRank().getRank();
        int rankPlayer2 = player2.getRank().getRank();

        if(rankPlayer1 == rankPlayer2){
            switch (rankPlayer1){
                case 9:
                case 5:
                    if(player1.getRank().getHighestCard() > player2.getRank().getHighestCard())
                        return 1;
                    else
                        return 2;
                case 8:
                    if(player1.getRank().getFourOfKind() > player2.getRank().getFourOfKind())
                        return 1;
                    else
                        return 2;
                case 7:
                case 4:
                    if(player1.getRank().getThreeOfKind() > player2.getRank().getThreeOfKind())
                        return 1;
                    else
                        return 2;
                case 6:
                case 1:
                    List<Integer> player1Deck = valuesInT(player1.getCard().getValues());
                    List<Integer> player2Deck = valuesInT(player2.getCard().getValues());
                    Collections.sort(player1Deck,Collections.reverseOrder());
                    Collections.sort(player2Deck,Collections.reverseOrder());

                    for(int i = 0 ; i<player1Deck.size()-1 ; i++){
                        if(player1Deck.get(i) > player2Deck.get(i))
                            return 1;
                        if(player1Deck.get(i)< player2Deck.get(i))
                            return 2;
                    }

                case 3:
                    Set<String> player1DeckString = checkingPair(player1.getCard().getValues());
                    Set<String> player2DeckString = checkingPair(player2.getCard().getValues());
                    if(player1DeckString.equals(player2DeckString)){
                        if (player1.getRank().getHighestCard() > player2.getRank().getHighestCard())
                            return 1;
                        else
                            return 2;
                    }
                    if(player1.getRank().getHighestPair() > player2.getRank().getHighestPair() || player1.getRank().getSecondHighestPair() > player2.getRank().getSecondHighestPair())
                        return 1;
                    else
                        return 2;
                case 2:
                    if(player1.getRank().getHighestPair() == player2.getRank().getHighestPair()){
                        if(player1.getRank().getHighestCard() > player2.getRank().getHighestCard() || player1.getRank().getThirdHighestCard() > player2.getRank().getThirdHighestCard() || player1.getRank().getSecondHighestCard() > player2.getRank().getSecondHighestCard())
                            return 1;
                        else
                            return 2;
                    }
                    if(player1.getRank().getHighestPair() > player2.getRank().getHighestPair())
                        return 1;
                    else
                        return 2;
            }
        }
            if(rankPlayer1>rankPlayer2)
                return 1;
            else
                return 2;

    }

    private static Ranking rankingPlayer(Player player) {
        ArrayList<String> values = player.getCard().getValues();
        ArrayList<String> suites = player.getCard().getSuites();
        Ranking rankedHand = new Ranking();

        if(checkingRoyalFlush(values,suites)){
            rankedHand.setRank(10);
            return rankedHand;
        }
        if(checkingStraight(values) && !checkingFlush(suites).isEmpty()){
            rankedHand.setRank(9);
            rankedHand.setHighestCard(highCard(values));
            return rankedHand;
        }
        if(!checkingFourOfAKind(values).isEmpty()){

            List<String> valueInString = List.copyOf(checkingFourOfAKind(values));
            List<Integer> intValue = valuesInT(valueInString);
            rankedHand.setFourOfKind(Collections.max(intValue));
            rankedHand.setRank(8);
            return rankedHand;

        }
        if(checkingPair(values).size() == 1 && checkingThreeOfAKind(values).size()==1){
            List<String> valueInString = List.copyOf(checkingThreeOfAKind(values));
            List<Integer> intValue = valuesInT(valueInString);
            rankedHand.setThreeOfKind(Collections.max(intValue));
            rankedHand.setRank(7);
            return rankedHand;

        }
        if(checkingFlush(suites).size() == 1){
            rankedHand.setRank(6);
            return rankedHand;
        }
        if(checkingStraight(values)){
            rankedHand.setRank(5);
            rankedHand.setHighestCard(highCard(values));
            return rankedHand;
        }
        if(checkingThreeOfAKind(values).size() == 1){
            List<String> valueInString = List.copyOf(checkingThreeOfAKind(values));
            List<Integer> intValue = valuesInT(valueInString);
            rankedHand.setThreeOfKind(Collections.max(intValue));
            rankedHand.setRank(4);
            return rankedHand;
        }
        if(checkingPair(values).size() == 2) {
            List<Integer> listValueInt = valuesInT(values);
            List<String> valueInString = List.copyOf(checkingPair(values));
            List<Integer> intValue = valuesInT(valueInString);
            Collections.sort(intValue);
            rankedHand.setHighestPair(intValue.get(1));
            rankedHand.setSecondHighestPair(intValue.get(0));
            rankedHand.setRank(3);
            listValueInt.removeAll(intValue);
            rankedHand.setHighestCard(listValueInt.get(0));
            return rankedHand;
        }
        if(checkingPair(values).size() == 1) {
            rankedHand.setRank(2);
            List<Integer> listValueInt = valuesInT(values);
            List<String> valueInString = List.copyOf(checkingPair(values));
            List<Integer> intValue = valuesInT(valueInString);
            rankedHand.setHighestPair(intValue.get(0));
            listValueInt.removeAll(intValue);
            Collections.sort(listValueInt);
            rankedHand.setHighestCard(listValueInt.get(2));
            rankedHand.setSecondHighestCard(listValueInt.get(1));
            rankedHand.setThirdHighestCard(listValueInt.get(0));
            return rankedHand;
        }
        else {
            rankedHand.setRank(1);
            return rankedHand;
        }
    }

    private static int highCard(ArrayList<String> values) {
        List<Integer> ranks = valuesInT(values);
        int number = Collections.max(ranks);
        return number;
    }

    private static Set<String> checkingThreeOfAKind(List<String> values) {
        return values.stream()
                .filter(a -> Collections.frequency(values,a) == 3)
                .collect(Collectors.toSet());
    }

    private static Set<String> checkingPair(List<String> values) {
        return  values.stream()
                .filter(a -> Collections.frequency(values,a)  == 2)
                .collect(Collectors.toSet());
    }
    private static Set<String> checkingFourOfAKind(List<String> values) {
        return values.stream()
                .filter(a -> Collections.frequency(values,a) == 4)
                .collect(Collectors.toSet());
    }

    private static Set<String> checkingFlush(List<String> suites){
        return suites.stream()
                .filter(a -> Collections.frequency(suites,a) == 5)
                .collect(Collectors.toSet());
    }

    private static Boolean checkingStraight(List<String> values){
        List<Integer> ranks = valuesInT(values);
        Collections.sort(ranks);
        for (int i = 0; i < ranks.size()-1;i++){
            if(ranks.get(i) + 1 != ranks.get(i+1)){
                return false;
            }
        }
        return true;
        }


     private static Boolean checkingRoyalFlush(List<String> values, List<String> suites){
        List<Integer> ranks = valuesInT(values);
        int minNumber = Collections.min(ranks);
        if(checkingStraight(values) && minNumber >=10 && !checkingFlush(suites).isEmpty()){
            return true;
        }
        return false;
     }

        private static List<Integer> valuesInT(List<String> values){
            List<Integer> ranks = new LinkedList<>();
            for (String value: values){
                switch (value){
                    case "2":
                        ranks.add(2);
                        break;
                    case "3":
                        ranks.add(3);
                        break;
                    case "4":
                        ranks.add(4);
                        break;
                    case "5":
                        ranks.add(5);
                        break;
                    case "6":
                        ranks.add(6);
                        break;
                    case "7":
                        ranks.add(7);
                        break;
                    case "8":
                        ranks.add(8);
                        break;
                    case "9":
                        ranks.add(9);
                        break;
                    case "T":
                        ranks.add(10);
                        break;
                    case "J":
                        ranks.add(11);
                        break;
                    case "Q":
                        ranks.add(12);
                        break;
                    case "K":
                        ranks.add(13);
                        break;
                    default:
                        ranks.add(14);
                        break;
                }
            }
            return ranks;
        }

    }





