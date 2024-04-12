package badmintonPlayerStats;

public class Player
{
	private String name;
    private String region;
    private String skillLevel;
    private String passwordString;
    private int wins;
    private int losses;

    // Constructor
    public Player(String name, String region, String skillLevel, String passwordString) 
    {
        this.name = name;
        this.region = region;
        this.skillLevel = skillLevel;
        this.passwordString = passwordString;
        this.wins = 0;
        this.losses = 0;
    }

    // Getters and setters
    public String getName() 
    {
        return name;
    }

    public String getRegion() 
    {
        return region;
    }

    public String getSkillLevel() 
    {
        return skillLevel;
    }
    
    public String getPasswordString()
    {
    	return passwordString;
    }

    public int getWins() 
    {
        return wins;
    }

    public int getLosses() 
    {
        return losses;
    }

    // Method to record match outcome
    public void recordMatchOutcome(boolean win) {
        if (win) {
            this.wins++;
        } else {
            this.losses++;
        }
    }

	public Player()
	{
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
