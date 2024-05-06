package badmintonPlayerStats;

import java.util.*;
import java.io.*;

public class PlayerManagerModel {
    private ArrayList<Player> players;
    private static final String DATA_FILE = "C:\\\\Users\\\\milli\\\\OneDrive\\\\Desktop\\\\Test\\\\output.txt";

    // Constructor
    public PlayerManagerModel() {
        this.players = new ArrayList<>();
        loadPlayerDataFromFile();
    }

    // Method to register a new player
    public void registerPlayer(String name, String region, String skillLevel) {
        Player player = new Player(name, region, skillLevel);
        players.add(player);
        savePlayerDataToFile(player);
    }

    // Method to save player data to the data file
    private void savePlayerDataToFile(Player player) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE, true))) {
            writer.write(player.getName() + "," + player.getRegion() + "," + player.getSkillLevel());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving player data: " + e.getMessage());
        }
    }

    // Method to validate login credentials
    public boolean validateLogin(String email, char[] password) {
        if (email == null) {
            return false;
        }
        return players.stream().anyMatch(player -> email.equals(player.getEmail()) && Arrays.equals(player.getPassword(), password));
    }

    // Method to find matching players based on region and skill level
    public ArrayList<Player> findMatchingPlayers(String region, String skillLevel) {
        ArrayList<Player> matchingPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.getRegion().equalsIgnoreCase(region) && player.getSkillLevel().equals(skillLevel)) {
                matchingPlayers.add(player);
            }
        }
        return matchingPlayers;
    }

    // Method to load player data from the data file
    private void loadPlayerDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    String name = data[0];
                    String region = data[1];
                    String skillLevel = data[2];
                    try {
                        int wins = Integer.parseInt(data[3]);
                        int losses = Integer.parseInt(data[4]);
                        Player player = new Player(name, region, skillLevel);
                        player.recordMatchOutcome(true, wins);
                        player.recordMatchOutcome(false, losses);
                        players.add(player);
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid data line: " + line);
                    }
                } else {
                    System.out.println("Skipping invalid data line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading player data: " + e.getMessage());
        }
    }

    // Method to update player statistics
    public void updatePlayerStatistics(Player player, boolean win) {
        player.recordMatchOutcome(win, 0);
        // Update player data in the data file
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5 && data[0].equals(player.getName()) && data[1].equals(player.getRegion()) && data[2].equals(player.getSkillLevel())) {
                    int wins = win ? player.getWins() : player.getWins() - 1;
                    int losses = win ? player.getLosses() - 1 : player.getLosses();
                    writer.write(player.getName() + "," + player.getRegion() + "," + player.getSkillLevel() + "," + wins + "," + losses);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating player statistics: " + e.getMessage());
        }
    }
}
