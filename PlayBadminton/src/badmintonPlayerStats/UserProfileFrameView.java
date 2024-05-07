package badmintonPlayerStats;


import javax.swing.*;
import java.awt.*;

//picture copy rights: 
public class UserProfileFrameView extends MainFrameView
{
	 private JPanel userInfoPanel;
	    private JPanel userProfilePicturePanel;
	    private JPanel buttonPanel;
	    private JButton requestChallengeButton;
	    private JButton nextMatchButton;
	    private JLabel profilePictureLabel;
	    private JLabel nameLabel;
	    private JLabel locationLabel;
	    private JLabel skillLevelLabel;
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
	        userInfoPanel.setLayout(new GridLayout(5, 1));
	        userProfilePicturePanel.setLayout(new BorderLayout());
	        buttonPanel.setLayout(new GridLayout(1, 2));
	    }

	    public void addUserInfoPanel() {
	        Player currentPlayer = model.getCurrentPlayer();
	        if (currentPlayer != null) {
	            nameLabel = new JLabel("Name: " + currentPlayer.getName());
	            locationLabel = new JLabel("Location: " + currentPlayer.getRegion());
	            skillLevelLabel = new JLabel("Skill Level: " + currentPlayer.getSkillLevel());
	        } else {
	            nameLabel = new JLabel("Name: Unknown");
	            locationLabel = new JLabel("Location: Unknown");
	            skillLevelLabel = new JLabel("Skill Level: Unknown");
	        }

	        userInfoPanel.setPreferredSize(new Dimension(400, 150));
	        userInfoPanel.add(nameLabel);
	        userInfoPanel.add(locationLabel);
	        userInfoPanel.add(skillLevelLabel);
	        userInfoPanel.setOpaque(false);
	        userInfoPanel.setBorder(BorderFactory.createTitledBorder("Player Stats"));
	    }

	    public void adduserProfilePicturePanel() {
	        profilePictureLabel = new JLabel(profilePic);
	        profilePictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        profilePictureLabel.setVerticalAlignment(SwingConstants.TOP);
	        profilePictureLabel.setPreferredSize(new Dimension(350, 250));
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

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            new UserProfileFrameView(new PlayerManagerModel());
	        });
	    }
	}