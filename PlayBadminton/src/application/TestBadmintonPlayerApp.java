package application;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class TestBadmintonPlayerApp
{

	@Test
	void testNoArgConstructor()
	{
		Player player = new Player();
		assertEquals(null, player.getName());
		assertEquals(null, player.getRegion());
		assertEquals(null, player.getSkillLevel());
	}




}
