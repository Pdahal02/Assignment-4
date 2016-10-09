import org.junit.Test;

public class GameTest {

	Game game = new Game(new Dice(), new Dice(),new Dice());

	@Test(expected=IllegalArgumentException.class)
	public void testNullPlay() {
		game.playRound(0,null,DiceValue.ANCHOR,0);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullDiceValue() {
		game.playRound(0,new Player("hello", 100),null,0);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeBet() {
		game.playRound(0,new Player("hello", 100),DiceValue.ANCHOR,-90);
		
	}
	
}
