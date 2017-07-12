
package BlackJack;

import java.io.IOException;
import java.util.*;

public class Player{
   
   public List<Card> hand = new ArrayList<Card>();
   // Hit or stay, default is hit.
   public boolean action = true;
   public boolean fold = false;
   // If player is AI controlled or not
   public boolean comp = false;
   protected boolean hasAce = false;
   public int handSum;

   public Player(){
       this.handSum = 0;
   }

    // Player constructor
   public Player(boolean comp){
      this.comp = comp;
      this.action = true;
      this.handSum = 0;
   }

   public String getHand(){
       String toString = "";
       for (Card card: hand){
           toString += card.toString() + "\n";

       }
       return toString;
    }

    // Changes player's desired action to hit
   public void ChangePlayerActionToHit(){
      if (!action) {
         action = true;
      }
   }

   //Changes player's desired action to stay
   public void ChangePlayerActionToStay(){
       if (action) {
           action = false;
       }
   }

    // Sums the values of the cards in the player's hand
    public int SumHand(){
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        for(Card card: this.hand){
            switch(card.value) {
                case 1: break;
                case 11:
                case 12:
                case 13: sum += 10; break;
                default: sum += card.value;
            }
            if (card.value == 1){
                System.out.println("Would you like to count your ace as a 1 or an 11?");
                int input = 0;
                try {
                    input = sc.nextInt();
                    while (input != 1 && input != 11) {
                        System.out.println("Would you like to count your ace as a 1 or an 11?");
                        input = sc.nextInt();
                    }
                }
                catch (Exception ex){
                    System.out.println("For being a smart ass, the value is 21");
                    input = 21;
                }
                System.out.println("You chose " + input);
                sum += input;
            }
        }

        return sum;
    }
}