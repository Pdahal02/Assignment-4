import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
	   BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	   
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Please Enter the Player Name :");
	   String playerName = sc.next();
	   System.out.println("Please Enter the inital amount you want to play with :");
	   String initialAmount = sc.next();

        int totalWins = 0;
        int totalLosses = 0;

        System.out.println("Please press q to exit the game, you'll be given chance before every game, else input anything to continue");
        while (true)
        {
        	
            int winCount = 0;
            int loseCount = 0;
            
            for (int i = 0; i < 100; i++)
            {

                Dice d1 = new Dice();
                Dice d2 = new Dice();
                Dice d3 = new Dice();

                Player player = new Player(playerName, Integer.parseInt(initialAmount));
                
            	String quit = sc.next();
            	if(quit.equals("q")){
            		System.out.println("You have decided to quit the game");
            		break;
            	}
            	String name = playerName;
            	int balance = Integer.parseInt(initialAmount);
            	int limit = 0;
                player = new Player(name, balance);
                player.setLimit(limit);
                int bet = 5;

                System.out.println(String.format("Start Game %d: ", i));
                System.out.println(String.format("%s starts with balance %d, limit %d", 
                		player.getName(), player.getBalance(), player.getLimit()));

                int turn = 0;
                while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200)
                {
                	Game game = new Game(d1, d2, d3);
                    List<DiceValue> cdv = game.getDiceValues();

                    turn++;                    
                	DiceValue pick = DiceValue.getRandom();
                   
                	System.out.printf("Turn %d: %s bet %d on %s\n",
                			turn, player.getName(), bet, pick); 
                	
                	int winnings = game.playRound(turn, player, pick, bet);
                    cdv = game.getDiceValues();
                    
                    System.out.printf("Rolled %s, %s, %s\n",
                    		cdv.get(0), cdv.get(1), cdv.get(2));
                    
                    if (winnings > 0) {
	                    System.out.printf("%s won %d, balance now %d\n\n",
	                    		player.getName(), winnings, player.getBalance());
	                	winCount++; 
                    }
                    else {
	                    System.out.printf("%s lost, balance now %d\n\n",
	                    		player.getName(), player.getBalance());
	                	loseCount++;
                    }
                    
                } //while

                System.out.print(String.format("%d turns later.\nEnd Game %d: ", turn, i));
                System.out.println(String.format("%s now has balance %d\n", player.getName(), player.getBalance()));
                
            } //for
            
            System.out.println(String.format("Win count = %d, Lose Count = %d, %.2f", winCount, loseCount, (float) winCount/(winCount+loseCount)));
            totalWins += winCount;
            totalLosses += loseCount;

            String ans = console.readLine();
            if (ans.equals("q")) break;
        } //while true
        
        System.out.println(String.format("Overall win rate = %.1f%%", (float)(totalWins * 100) / (totalWins + totalLosses)));
        sc.close();
	}

}
