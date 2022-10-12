package regras;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegrasTest {

	@Test
	void testRoll_dice() {
		int dado = Regras.roll_dice();
		assertTrue(dado > 0 && dado < 7);
	}

}
