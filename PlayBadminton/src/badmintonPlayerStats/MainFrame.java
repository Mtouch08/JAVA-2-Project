package badmintonPlayerStats;

import java.awt.Color;
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
import javax.swing.SwingUtilities;


public class MainFrame extends JFrame
{
	private JTextField nameField = new JTextField("UserName");
	private JPasswordField passwordField = new JPasswordField("Password");
	private JLabel signUpLabel = new JLabel("I don't have an account:");
	private JLabel background;
	private JButton signUp = new JButton("Sign Up");
	private JButton loginButton = new JButton("Login");
	
	private ImageIcon image = new ImageIcon("badminton icon.jpg");
    private PlayerManager playerManager = new PlayerManager(); 
    
	public MainFrame()
	{
		  //Login Panel:
		   JPanel loginPanel = new JPanel(new GridLayout(5,1));
		   loginPanel.setBounds(150,150,250,300);
		   loginPanel.setOpaque(false);
		   loginPanel.add(nameField);
		   loginPanel.add(passwordField);
		   loginPanel.add(loginButton);
		   loginPanel.add(signUpLabel);
		   loginPanel.add(signUp);
		   signUpLabel.setFont(new Font("ARIAL",Font.BOLD,15));
		   signUpLabel.setForeground(Color.GRAY);
		   
		   signUp.addActionListener(e -> openNewWindow());
		   loginButton.addActionListener( new LoginListener());
		   
		this.setTitle("Play Badminton");
		this.setIconImage(image.getImage());//fineartamerica.com
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setVisible(true);
		this.setLayout(null);
		//image from acesporty.com
		ImageIcon backgroundImage = new ImageIcon("file:///C:/Users/milli/OneDrive/Desktop/badmintonbackground.jpg");
		background = new JLabel("",backgroundImage,JLabel.CENTER);
		background.setBounds(0,0,800,600);
		
		this.add(background);
		this.add(loginPanel);

	}
	
	    private class LoginListener implements ActionListener 
	    {
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	            String name = nameField.getText();
	            char[] password = passwordField.getPassword();
	            String passwordString = new String(password);
	            if (playerManager.validateLogin(name, passwordString)) 
	            {  
	                JOptionPane.showMessageDialog(MainFrame.this, "Login successful!");
	            } else 
	            {
	                JOptionPane.showMessageDialog(MainFrame.this, "Invalid username or password.");
	            }
	        }
	    }
	
	    private void openNewWindow() 
	    {
	        // This method will open the new window and close the current one
	    	new RegFrame();
	        this.dispose();  // Close the current window
	    }

    public static void main(String[] args) 
    {
        // Ensure the GUI is created on the Event Dispatch Thread
        //SwingUtilities.invokeLater(MainFrame::new);
    }
}
