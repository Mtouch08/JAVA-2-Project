package badmintonPlayerStats;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegFrame extends JFrame
{
	private ImageIcon image = new ImageIcon("badminton icon.jpg"); 
	private JLabel titleLabel = new JLabel("Player Registration");
	private JButton regPlayerButton = new JButton("Register");
	private JTextField regionField = new JTextField("City");	
	private PlayerManager playerManager = new PlayerManager();
	private JTextField nameField = new JTextField("Name");
	private JPasswordField passwordField = new JPasswordField("Password");
	private JTextField skillField = new JTextField("Skill Level: A,C,D,D+");
	//private JLabel background; 
	public RegFrame()
	{
		//Title Panel:
	    JPanel titlePanel = new JPanel();
	    titlePanel.setBounds(100,0,600,100);
	    titleLabel.setFont(new Font("ARIAL",Font.BOLD,25));
	    titlePanel.add(titleLabel);
	    
	      
	    //registration panel:
	    JPanel regPanel = new JPanel(new GridLayout(5,1));
	    regPanel.setBounds(150,150,250,300);
	    regPanel.add(nameField);
	    regPanel.add(regionField);
	    regPanel.add(skillField);
	    regPanel.add(passwordField);
	    regPanel.add(regPlayerButton);
	    regPlayerButton.addActionListener( new RegistrationListener());
	    regPlayerButton.addActionListener(e -> returnToLogin());
		 // Configure the frame
	    this.setTitle("Play Badminton");
		this.setIconImage(image.getImage());//fineartamerica.com
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setVisible(true);
		this.setLayout(null);
		//image from acesporty.com
		//ImageIcon backgroundImage = new ImageIcon("file:///C:/Users/milli/OneDrive/Desktop/badmintonbackground.jpg");
		//background.setBackground("",backgroundImage,JLabel.CENTER);
		//background.setBounds(0,0,800,600);
		this.add(titlePanel);
		this.add(regPanel);
		//this.add(background);
		
	    }
	public static void main(String[] args) 
    {
        new RegFrame();
    }
	// Inner class for handling registration
	 	public class RegistrationListener implements ActionListener 
	 	{
	 		
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	        	
	            String name = nameField.getText();
	            String region = regionField.getText();
	            String skillLevel = skillField.getText();
	            char[] password = passwordField.getPassword();// Securely handle password
	            String passwordString = new String(password);// Convert char[] to String if necessary for processing
	            playerManager.registerPlayer(name, region, skillLevel,passwordString);
	            JOptionPane.showMessageDialog(RegFrame.this, "User registered!");
	        }
	}
	 	private void returnToLogin() 
	    {
	        // This method will open the new window and close the current one
	    	new MainFrame();
	        this.dispose();  // Close the current window
	    }
}
	
    

