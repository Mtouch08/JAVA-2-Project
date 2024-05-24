package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Responsibility of class: 
 */

public class ButtonListeners implements ActionListener
{
	
	private MainFrameView mainFrame;
    private SearchFrameView searchFrame;
    private UserProfileFrameView userProfileFrame;
    private PlayerManagerModel model;

	public ButtonListeners(MainFrameView mainFrame, SearchFrameView searchFrame, PlayerManagerModel model) {
        this.mainFrame = mainFrame;
        this.searchFrame = searchFrame;
        this.model = model;
    }

	@Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "login":
                handleLogin();
                break;
            case "signUp":
                toggleSignUpPanel();
                break;
            case "register":
                handleRegister();
                break;
            case "findMatch":
                performSearch();
                break;
            case "requestChallenge":
                handleRequestChallenge();
                break;
            case "nextMatch":
                handleNextMatch();
                break;
      
        }
    }

	private void handleMenuActions(String command)
	{
		// TODO Auto-generated method stub
		
	}

	private void handleNextMatch()
	{
		// TODO Auto-generated method stub
		
	}

	private void handleRequestChallenge()
	{
		// TODO Auto-generated method stub
		
	}

	private void performSearch() {
	    String region = searchFrame.getRegion();
	    String skillLevels = searchFrame.getSelectedSkillLevels();
	    List<Player> matchingPlayers = model.findMatchingPlayers(region, skillLevels);
	    searchFrame.displayMatchingPlayers(matchingPlayers);
	}
	private void handleRegister() {
	    String name = mainFrame.getNameField();
	    String email = mainFrame.getSignUpEmail(); 
	    String region = mainFrame.getRegion();
	    String skillLevel = mainFrame.getSkillLevel();
	    String password = mainFrame.getSignUpPassword(); // Securely handle password
	    
	    try {
	        model.registerPlayer(name, email, region, skillLevel, password);
	        JOptionPane.showMessageDialog(mainFrame, "Registration successful for: " + name, "Registration Success", JOptionPane.INFORMATION_MESSAGE);
	        returnToLogin();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(mainFrame, "Registration failed. Please try again.");
	    }
	}
       
	 private void toggleSignUpPanel() {
		 // Get the signUpPanel from MainFrameView
		    JPanel signUpPanel = mainFrame.getSignUpPanel();

		    // Toggle the visibility of the signUpPanel
		    signUpPanel.setVisible(!signUpPanel.isVisible());
		    mainFrame.getLoginPanel().setVisible(!signUpPanel.isVisible());
		    mainFrame.getNewUserPanel().setVisible(!signUpPanel.isVisible());
		    // Repaint and revalidate to update GUI after visibility change
		    mainFrame.repaint();
		    mainFrame.revalidate();
	    }

	 private void handleLogin() {
		 String email = mainFrame.getLoginEmail().trim();
		 String password = mainFrame.getLoginPassword().trim();
		    // Debug output to check received values
		    System.out.println("Attempting login with email: " + email + " and password: " + password);
		    if (model.validateLogin(email, password)) {
		        JOptionPane.showMessageDialog(mainFrame, "Login successful for: " + email, "Login Success", JOptionPane.INFORMATION_MESSAGE);
		        goToSearchFrame();
		    } else {
		        JOptionPane.showMessageDialog(mainFrame, "Invalid credentials!", "Login Error", JOptionPane.ERROR_MESSAGE);
		    }
		}
	

	private void goToSearchFrame() {
		 // Create a new instance of SearchFrameView and pass the model
	    SearchFrameView searchFrame = new SearchFrameView(model);
	    searchFrame.setVisible(true);
	    // Dispose the current MainFrameView
	    mainFrame.dispose();

	}
	
	private void returnToLogin() {
		JPanel loginPanel = mainFrame.getLoginPanel();

	    // Toggle the visibility of the signUpPanel
	    loginPanel.setVisible(!loginPanel.isVisible());
	    mainFrame.getSignUpPanel().setVisible(!loginPanel.isVisible());
	    // Repaint and revalidate to update GUI after visibility change
	    mainFrame.repaint();
	    mainFrame.revalidate();
	}
	
	private void goToUserProfile() {
		// Create a new instance of SearchFrameView and pass the model
		UserProfileFrameView userProfile = new UserProfileFrameView(model);
		userProfile.setVisible(true);
	}
}	 	

