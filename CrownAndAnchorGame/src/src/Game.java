import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Game {

	private List<Dice> dice;
	private List<DiceValue> values;

	public Game(Dice die1, Dice die2, Dice die3) {
		if (die1 == null || die2 == null || die3 == null) throw new IllegalArgumentException("Dice cannot be null.");
		dice = new ArrayList<Dice>();
		dice.add(die1);
		dice.add(die2);
		dice.add(die3);
		values = new ArrayList<DiceValue>();
	}

	public List<DiceValue> getDiceValues() {
		values.clear();
		for (Dice d : dice) {
			if(!values.contains(d.getValue())){
				values.add(d.getValue());
			}else{
				Dice d4 = new Dice();
				values.add(d4.getValue());
			}
		}
		return values;
	}	

	public int playRound(int turn,Player player, DiceValue pick, int bet ) {		
		if (player == null) throw new IllegalArgumentException("Player cannot be null.");
		if (pick == null) throw new IllegalArgumentException("Pick cannot be negative.");
		if (bet < 0) throw new IllegalArgumentException("Bet cannot be negative.");
		
		player.takeBet(bet);

		int matches = 0;
		for ( Dice d : dice) {
			d.roll();
			if (d.getValue().equals(pick)) { 
					if(turn ==1){
						matches += 2;
					}else{
						matches += 1;
					}
			}
		}

		int winnings = matches * bet;

		player.receiveWinnings(winnings);
		return winnings;		
	}

}