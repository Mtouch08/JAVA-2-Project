package application;

import java.util.Arrays;

import javax.swing.JLabel;
/**
 * Responsibility of class:
 */
public class Player {
    private String name;
    private String email;
    private String region;
    private String skillLevel;
    private int wins = 0;
    private int losses = 0;
    private char[] password;

    public Player(String name, String email, String region, String skillLevel, char[] password) {
        this.name = name;
        this.email = email;
        this.region = region;
        this.skillLevel = skillLevel;
        this.password = Arrays.copyOf(password, password.length); // Deep copy for security
    }

	public Player(String region, String skillLevel )
	{
		this.region=region;
		this.skillLevel=skillLevel;
	}

	// Getters and setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRegion() {
        return region;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public char[] getPassword() {
        return Arrays.copyOf(password, password.length); // Return a copy to preserve encapsulation and security
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    // Method to record match outcome
    public void recordMatchOutcome(boolean win, int unused) {
        if (win) {
            this.wins++;
        } else {
            this.losses++;
        }
    }

    // Clear the password from memory when no longer needed
    public void clearPassword() {
        Arrays.fill(password, '0');
    }

	public int getStats()
	{
		
		return wins + losses;
	}

	public String getRecord()
	{
		
		return null;
	}

}
