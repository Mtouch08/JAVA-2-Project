package application;

import java.util.*;
import java.io.*;

public class PlayerManagerModel {
    
    private Player currentPlayer;
    private String fileData = "dataFile.txt";
	HashMap<String,String> loginInfo;
	HashMap<String,Player> players;
	
    public PlayerManagerModel() {
        this.loginInfo = new HashMap<>();
        this.players = new HashMap<>();
        loadProfile();
    }
    
    public void setPlayer(Player player) {
    	this.currentPlayer = player;
    }

    public Player getPlayer() {
    	return currentPlayer;
    }
    
    public void registerPlayer(String name, String email, String region, String skillLevel, String password) {
        Player player = new Player(name, email, region, skillLevel, password.toCharArray());
        players.put(email, player);
        loginInfo.put(email, password);
        saveProfile(player);
        System.out.println("Stored password for email " + email + ": " + password);  // Debug statement
    }

    // Method to save player data to the data file
    public void saveProfile(Player player) {
        try {
        String passwordString = new String(player.getPassword());
        FileWriter writer = new FileWriter(fileData, true);
        writer.write(player.getName() + "\t" + player.getEmail() + "\t" + player.getRegion() + "\t" + player.getSkillLevel() + "\t" + passwordString + "\n");
        writer.close();
        } catch (IOException e) {
            System.out.println("Error saving player data: " + e.getMessage());
        }
    }

 // Method to validate login credentials
    public boolean validateLogin(String email, String password) {
        if (loginInfo.containsKey(email)) {
            String storedPassword = loginInfo.get(email);
            System.out.println("Stored password for email " + email + ": " + storedPassword);  // Debug statement
            boolean valid = storedPassword.equals(password.trim());  // Trim input password to remove leading/trailing whitespace
            return valid;
        }
        return false;
    }


 // Method to find matching players based on region and skill level
    public ArrayList<Player> findMatchingPlayers(String region, String skillLevels) {
        ArrayList<Player> matchingPlayers = new ArrayList<>();

        // Split the skillLevels string into individual levels
        String[] levels = skillLevels.split(",");

        for (Player player : players.values()) {
            if (player.getRegion().equalsIgnoreCase(region)) {
                for (String level : levels) {
                    if (player.getSkillLevel().equalsIgnoreCase(level)) {
                        matchingPlayers.add(player);
                        break;
                    }
                }
            }
        }

        return matchingPlayers;
    }
    

    // Method to load player data from the data file
    public void loadProfile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileData));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                if (data.length == 5) {
                    String name = data[0];
                    String email = data[1];
                    String region = data[2];
                    String skillLevel = data[3];
                    String password = data[4];
                    Player player = new Player(name, email, region, skillLevel, password.toCharArray());
                    players.put(email, player);
                    loginInfo.put(email, password);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading player data: " + e.getMessage());
        }
    }

    // Method to update player statistics
    public void updatePlayerStatistics(Player player, boolean win) {
        player.recordMatchOutcome(win, 0);
        // Update player data in the data file
        File tempFile = new File(fileData + ".tmp");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileData));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5 && data[1].equals(player.getEmail())) {
                    int wins = player.getWins();
                    int losses = player.getLosses();
                    writer.write(player.getName() + "," + player.getEmail() + "," + player.getRegion() + "," + player.getSkillLevel() + "," + wins + "," + losses);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating player statistics: " + e.getMessage());
        }
        // Replace original file with updated file
        if (!tempFile.renameTo(new File(fileData))) {
            System.out.println("Error replacing old data file with updated data file.");
        }
    }

}
