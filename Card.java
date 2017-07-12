package BlackJack;

public class Card{

   public int value;
   private String suit;
   private String identifier;
   public boolean hidden;
   
   public Card(int value, int suit, String identifier){
      this.value = value;
      
      switch(suit){
         case 0: this.suit = "Spades"; break;
         case 1: this.suit = "Clubs"; break;
         case 2: this.suit = "Hearts"; break;
         case 3: this.suit = "Diamonds"; break;
      }
      this.hidden = true;
      this.identifier = identifier;
   }

   public String toString(){
      String cardName;
      switch(value){
         case 1: cardName = "Ace"; break;
         case 11: cardName = "Jack"; break;
         case 12: cardName = "Queen"; break;
         case 13: cardName = "King"; break;
         default: cardName = "" + value;
      }
      return cardName + " of " + suit;
   }

}