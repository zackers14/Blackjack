
package BlackJack;

import java.util.*;

public class Deck {

   private Card[][] CurrentDeck = new Card[4][12];
   private List<Card> DealtCards = new ArrayList<Card>();

   //Deck constructor, builds the current deck with appropriate cards in the correct order
   public Deck(){
      for(int i = 0; i < 4; i++){
      
         for(int j = 0; j < 12; j++){
         
            CurrentDeck[i][j] = new Card(j+1, i, i + " " + j);
         }
      
      }
   }

   //Deals a card to a target player
   public boolean DealCard(Player player) {
    int randomIndex1;
    int randomIndex2;
    Card randomCard;
    boolean cardDealt = false;

       if (player.action) {
           while (!cardDealt) {
               randomIndex1 = (int) Math.floor(Math.random() * 4);
               randomIndex2 = (int) Math.floor(Math.random() * 12);
               randomCard = CurrentDeck[randomIndex1][randomIndex2];
               if (randomCard != null) {
                   player.hand.add(randomCard);
                   DealtCards.add(randomCard);
                   CurrentDeck[randomIndex1][randomIndex2] = null;
                   if (player.hand.size() == 1){
                       player.hand.get(0).hidden = false;
                   }
                   if (randomCard.value == 1){
                       player.hasAce = true;
                   }
                   cardDealt = true;
                   System.out.println("You drew the " + randomCard.toString());
               }
           }

           return true;
       }
       else {return false;}
   }


   //Shuffles the deck, switching all cards with another random card in the deck. Also adds back in dealt cards
   public void ShuffleDeck(){
   int randomIndex1;
   int randomIndex2;
   Card currentCard;
   Card randomCard;
   int listCounter = 0;
   
      for(int i = 0; i < 4; i++){
      
         for(int j = 0; j < 12; j++){
         
            randomIndex1 = (int) Math.floor(Math.random() * 4);
            randomIndex2 = (int) Math.floor(Math.random() * 12);
            currentCard = CurrentDeck[i][j];
            if (currentCard == null){
               CurrentDeck[i][j] = DealtCards.get(listCounter);
               currentCard = CurrentDeck[i][j];
               DealtCards.remove(currentCard);
            }
            randomCard = CurrentDeck[randomIndex1][randomIndex2];
            CurrentDeck[i][j] = randomCard;
            CurrentDeck[randomIndex1][randomIndex2] = currentCard;
            listCounter++;
         }
      }
   }
   
}