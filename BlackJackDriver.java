package BlackJack;

import java.util.Scanner;

public class BlackJackDriver{


   public static void main(String[] args){
       BlackJackDriver driver = new BlackJackDriver();
       driver.RunGame(args);

   }

    private Player Player1;
    private AI Player2;
    private Deck PlayingDeck;
    private boolean GameIsRunning = false;

    //Starts game by initializing important variables
   private void StartGame(){
       // First player cannot be computer controlled
       Player1 = new Player(false);
       // AI is a subclass of Player class
       Player2 = new AI();
       // Creates Deck class and shuffles the cards in it
       PlayingDeck = new Deck();
       PlayingDeck.ShuffleDeck();
       // Flag that sets game loop
       GameIsRunning = true;
       // Deals first two cards to both players
       PlayingDeck.DealCard(Player1);
       PlayingDeck.DealCard(Player2);
       PlayingDeck.DealCard(Player1);
       PlayingDeck.DealCard(Player2);
       // Sums player hands
       Player1.handSum = Player1.SumHand();
       Player2.handSum = Player2.SumHand();
   }

   //Wraps up game by displaying score and cards to players
   private void EndGame(){
       if ((Player1.handSum > Player2.handSum && !Player1.fold) || (!Player1.fold && Player2.fold)){
           System.out.println("You win!");
       }
       else if ((Player1.handSum == Player2.handSum) || (Player1.fold && Player2.fold)){
           System.out.println("Draw!");
       }
       else {System.out.println("You lose!");}

       // End logging
       System.out.println("Player1 cards and values:");
       System.out.println(Player1.getHand());
       System.out.println("Player 1 sum: " + Player1.handSum + "\n");

       System.out.println("Player2 cards and values:");
       System.out.println(Player2.getHand());
       System.out.println("Player 2 sum: " + Player2.handSum);
   }

   //Player turn method
   private void PlayerTurn(Player player){
       Scanner sc = new Scanner(System.in);
           if (!player.fold && player.action) {
               System.out.println("Your current hand consists of:");
               System.out.println(player.getHand());
               System.out.println("The sum of cards in your hand is: " + player.handSum);
               System.out.println("Would you like to draw another card?");
               String input = sc.nextLine();
               System.out.println();
               if (!(input.equals("y") || input.equals("Y"))) {
                   player.ChangePlayerActionToStay();
               } else {
                   PlayingDeck.DealCard(player);
                   player.handSum = player.SumHand();
               }

               if (player.handSum > 21) {
                   player.fold = true;
                   player.ChangePlayerActionToStay();
               } else if (player.handSum == 21) {
                   player.ChangePlayerActionToStay();
                   System.out.println("Blackjack!");
               }

           }

   }
   // AI Turn, cannot be lumped in with Player turn because AI is a subclass of player
   private void AITurn(AI player){
       if (!player.fold && player.action){

           if (player.CalculateAction(player.handSum)){
               PlayingDeck.DealCard(player);
               player.handSum = player.SumHand();
           }
           player.AIBust(player.handSum);
       }
   }


   //Runs all methods important for playing the game
   private void RunGame (String[] args){
       boolean playAgain = true;


       while (playAgain) {
           StartGame();

           while (GameIsRunning) {

               PlayerTurn(Player1);
               AITurn(Player2);

               if (!Player1.action && !Player2.action) {
                   GameIsRunning = false;
               }

           }
           EndGame();
           System.out.println("Play again?");
       }
   }

}
