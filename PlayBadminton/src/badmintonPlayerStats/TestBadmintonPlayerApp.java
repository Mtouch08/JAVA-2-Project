package badmintonPlayerStats;


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

//	@Test
//	void testPlayer(String name, String region, String skillLevel)
//	{
//		
//	}
//	@Test
//	void testCopyConstructor()
//	{
//		
//	}
//	
//	@Test
//	void testgetPasswordString()
//	{
//		
//	}
//	
//	@Test
//	void testButton()
//	{
//		JButton button = new JButton();
//		assertEquals(1, button.getRow());
//		assertEquals(2, button.getColumn());
//	}
//	
//	@Test
//	void testBadmintonView()
//	{
//		BadmintonModel badmintonModel = new BadmintonModel();
//		BadmintonView badmintonView = new BadmintonView(badmintonModel);
//		badmintonView.updateUI();
//		badmintonModel.fishAt(0, 0);
//	}
//
//	@Test
//	void testButtonListener()
//	{
//		BadmintonModel badmintonModel = new BadmintonModel();
//		BadmintonView badmintonView = new BadmintonView(badmintonModel);
//		JButton button = new JButton();
//		ButtonListener buttonlistener = new ButtonListener(badmintonModel, badmintonView, button);
//		listener.actionPerformed(null);


}
