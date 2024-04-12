package badmintonPlayerStats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


//Define PlayerManager class to manage player registration, matching, etc.
public class PlayerManager 
{
	private ArrayList<Player> players;

    // Constructor
    public PlayerManager() 
    {
      this.players = new ArrayList<>();

    }

    // Method to register a new player
    public void registerPlayer(String name, String region, String skillLevel,String passwordString) 
    {
        Player player = new Player(name, region, skillLevel,passwordString);
        players.add(player);
        savePlayerDataToFile(player);
    }

    // Method to save player data to the data file
    private void savePlayerDataToFile(Player player) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\milli\\OneDrive\\Desktop\\Test\\output.txt", true))) {
            writer.write(player.getName() + "," + player.getRegion() + "," + player.getSkillLevel() + ","+player.getPasswordString());
            writer.newLine();
        } catch (IOException e) 
        {
            System.out.println("Error saving player data: " + e.getMessage());
        }
    }

    public boolean validateLogin(String name, String password)
    {
    	return players.stream().anyMatch(player -> player.getName().equals(name));
    }

}