package badmintonPlayerStats;

import java.util.Arrays;

public class Player
{
	protected String name;
	protected String email;
	protected static int phoneNumber;
	protected String region;
	protected String skillLevel;
    private int wins;
    private int losses;
	private char[] password;
	

	public Player()
	{
		
	}
	
	public Player(String name)
	{
		this.name=name;
	}
	
	 public Player(String email, char[] password) {
	        this.email = email;
	        this.password = Arrays.copyOf(password, password.length); // Deep copy for security
	    }
	 
	public Player(String name, String email, int phoneNmber)
	{
		this.name=name;
		this.email=email;
	}
	

    public Player(String name, String region, String skillLevel) 
    {
        this.name = name;
        this.region = region;
        this.skillLevel = skillLevel;

    }
    

    // Getters and setters
    public String getName() 
    {
        return name;
    }
    
    public String getEmail()
    {
    	return email;
    }

    public String getRegion() 
    {
        return region;
    }
    
    public int getPhoneNumber() {
    	return phoneNumber;
    }

    public String getSkillLevel() 
    {
        return skillLevel;
    }
    
    public char[] getPassword() {
        return Arrays.copyOf(password, password.length); // Return a copy to preserve encapsulation and security
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
    public void recordMatchOutcome(boolean b, int wins2) {
        if (b) 
        {
            this.wins++;
        } else {
            this.losses++;
        }
    }
    
 // clear the password from memory when no longer needed
    public void clearPassword() {
        Arrays.fill(password, '0');
    }


}
