

import org.junit.Assert;
import org.junit.Test;


public class PlayerTest {
	
	Player player = new Player("Randome Name", 100);

	@Test(expected=IllegalArgumentException.class)
	public void testNegativeLimit() {
		player.setLimit(-1);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeWinnings() {
		player.receiveWinnings(-1);
		
	}
	
	@Test
	public void testbalanceExceedsLimitByTrue() {
		Assert.assertEquals(player.balanceExceedsLimitBy(50), true);
	}
}


