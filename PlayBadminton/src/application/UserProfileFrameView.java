package application;


import javax.swing.*;
import java.awt.*;

//picture copy rights: 
public class UserProfileFrameView extends MainFrameView
{
	 private JPanel userInfoPanel;
	 private JPanel userProfilePicturePanel,buttonPanel;
	 private JButton requestChallengeButton,nextMatchButton;
	 private JLabel profilePictureLabel,nameLabel,locationLabel,skillLevelLabel,statsLabel,statsLabel2,statsLabel3,recordLabel;
	 private ImageIcon profilePic = new ImageIcon("C://Users//milli//JAVA-2-Project//PlayBadminton//Images//profilePic.jpg");
	 private ImageIcon backgroundImage2 = new ImageIcon("C://Users//milli//JAVA-2-Project//PlayBadminton//Images//profileViewBackground.jpg");
			
	 public UserProfileFrameView(PlayerManagerModel model) {
		 super(model);
		 remove(titlePanel);
		 initFrame();
		 addContentPane();
		 setVisible(true);
	 }

	 private void addContentPane() {
		 // Create a custom JPanel to paint the background image
		 JPanel contentPane = new JPanel() {
			 @Override
			 protected void paintComponent(Graphics graphic) {
				 super.paintComponent(graphic);
				 // Draw the background image
				 graphic.drawImage(backgroundImage2.getImage(), 0, 0, getWidth(), getHeight(), this);
			 }
		 };
		 contentPane.setLayout(new BorderLayout());

		 // Set the content pane of the frame
		 setContentPane(contentPane);

		 // Initialize panels
		 initPanels();

		 // Add panels to the content pane
		 contentPane.add(userInfoPanel, BorderLayout.WEST);
		 contentPane.add(userProfilePicturePanel, BorderLayout.CENTER);
		 contentPane.add(buttonPanel, BorderLayout.SOUTH);

		 // Initialize components
		 initComponents();
	 }

	 private void initFrame() {
		 setTitle("Play Badminton");
		 setLayout(new BorderLayout());
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(800, 600);
		 setResizable(false);
	 }

	 private void initComponents() {
		 setPanelLayout();
		 addUserInfoPanel();
		 adduserProfilePicturePanel();
		 addButtonPanel();
	 }

	 private void initPanels() {
		 userInfoPanel = new JPanel();
		 userProfilePicturePanel = new JPanel();
		 buttonPanel = new JPanel();
	 }

	 private void setPanelLayout() {
		 userInfoPanel.setLayout(new GridLayout(10, 1));
		 userProfilePicturePanel.setLayout(new BorderLayout());
		 buttonPanel.setLayout(new GridLayout(1, 2));
	 }

	 public void addUserInfoPanel() {
		    Player player = model.getPlayer();
		    if (player != null) {
		        nameLabel = new JLabel("Name: " + player.getName());
		        locationLabel = new JLabel("Location: " + player.getRegion());
		        skillLevelLabel = new JLabel("Skill Level: " + player.getSkillLevel());
		        statsLabel = new JLabel("Women Singles: " + player.getStats());
		        statsLabel2 = new JLabel("Women Doubles: " + player.getStats());
		        statsLabel3 = new JLabel("Mix Doubles: " + player.getStats());
		        recordLabel = new JLabel("Record: " + player.getRecord());
		    } else {
		        nameLabel = new JLabel("Name: ");
		        locationLabel = new JLabel("Location: ");
		        skillLevelLabel = new JLabel("Skill Level: ");
		        statsLabel = new JLabel("Women Singles: ");
		        statsLabel2 = new JLabel("Women Doubles: ");
		        statsLabel3 = new JLabel("Mix Doubles: ");
		        recordLabel = new JLabel("Record: ");
		    }

		    JLabel[] labels = {nameLabel, locationLabel, skillLevelLabel, statsLabel, statsLabel2, statsLabel3, recordLabel};
		    for (JLabel label : labels) {
		        if (label == null) {
		            System.out.println("A label was not initialized");
		        } else {
		            userInfoPanel.add(label);
		        }
		    }
		    userInfoPanel.setBorder(BorderFactory.createTitledBorder("Player Stats"));
		    userInfoPanel.setOpaque(false);
		    userInfoPanel.setPreferredSize(new Dimension(300, 600));
		}
	 
	 public void adduserProfilePicturePanel() {
		 profilePictureLabel = new JLabel(profilePic);
		 profilePictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 profilePictureLabel.setVerticalAlignment(SwingConstants.TOP);
		 profilePictureLabel.setPreferredSize(new Dimension(250, 250));
		 userProfilePicturePanel.setPreferredSize(new Dimension(390, 300));
		 userProfilePicturePanel.setOpaque(false);
		 userProfilePicturePanel.add(profilePictureLabel);
	 }	

	 public void addButtonPanel() {
		 requestChallengeButton = new JButton("Request a Challenge");
		 nextMatchButton = new JButton("Next Match");
		 requestChallengeButton.setPreferredSize(new Dimension(150, 40));
		 nextMatchButton.setPreferredSize(new Dimension(150, 40));
		 buttonPanel.add(requestChallengeButton);
		 buttonPanel.add(nextMatchButton);
		 buttonPanel.setOpaque(false);
		 
	 }

	    // Override methods to prevent adding components from MainFrameView
	    @Override
	    public void addLoginPanel() {
	        // Override to prevent adding login panel from MainFrameView
	    }

	    @Override
	    public void addNewUserPanel() {
	        // Override to prevent adding new user panel from MainFrameView
	    }

	    @Override
	    public void addSignUpPanel() {
	        // Override to prevent adding sign-up panel from MainFrameView
	    }
	    
	    public static void main(String[]arg) {
	    	new UserProfileFrameView(new PlayerManagerModel());
	    }
	}