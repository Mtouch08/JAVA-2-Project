package badmintonPlayerStats;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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


public class MainFrame extends RegFrame
{
	private JTextField nameField = new JTextField("Millie Tan");
	private JPasswordField passwordField = new JPasswordField("Enter Password");
	private JLabel signUpLabel = new JLabel("I don't have an account:");
	private JButton signUpButton = new JButton("Sign Up");
	private JButton loginButton = new JButton("Login");
	JPanel contentPane;//custom content pane for background image
    private PlayerManager playerManager = new PlayerManager(); 
	private ImageIcon image = new ImageIcon("C://Users//milli//JAVA-2-Project//PlayBadminton//Images//badminton icon.jpg"); 
	private JLabel titleLabel = new JLabel("Search->Request Match->Play! Let's Go!");
		
	public MainFrame()
	{
		contentPane= new JPanel() {
			
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				//Load background image and draw it
				//image from acesporty.com
				ImageIcon backgroundImage = new ImageIcon("C://Users//milli//JAVA-2-Project//PlayBadminton//Images//badmintonbackground.jpg");
				g.drawImage(backgroundImage.getImage(),0, 0,getWidth(), getHeight(),this);
			}};
		contentPane.setLayout(null);
		//Title Panel:
	    JPanel titlePanel = new JPanel();
	    titlePanel.setOpaque(false);
	    titlePanel.setBounds(100,0,600,100);
	    styleComponent(titleLabel, titleFontSize, foregroundColor);
	    titlePanel.add(titleLabel);
	    
		  //Login Panel:
		   JPanel loginPanel = new JPanel(new GridLayout(5,1));
		   loginPanel.setBounds(510,110,250,300);
		   loginPanel.setOpaque(false);
		   loginButton.setFont(defaultFont);
		   signUpButton.setFont(defaultFont);
		   styleComponent(nameField, defaultFont, foregroundColor);
	       styleComponent(passwordField, defaultFont, foregroundColor);
	       styleComponent(signUpLabel, defaultFont, foregroundColor);
	       loginPanel.add(nameField);
		   loginPanel.add(passwordField);
		   loginPanel.add(loginButton);
		   loginPanel.add(signUpLabel);
		   loginPanel.add(signUpButton);
	
		 //Add components to content pane:
		    contentPane.add(loginPanel);
		    contentPane.add(titlePanel);
		    
		   signUpButton.addActionListener(e -> openRegistrationWindow());
		   loginButton.addActionListener( new LoginListener());
	
		//image from acesporty.com
		// Configure the frame
	    setTitle("Play Badminton");
		setIconImage(image.getImage());//fineartamerica.com
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setResizable(false);
		setContentPane(contentPane);
		setVisible(true);
		repaint();
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
	                searchWindow();
	                
	            } else 
	            {
	                JOptionPane.showMessageDialog(MainFrame.this, "Invalid username or password.");
	            }
	        }
	    }
	
	    private void openRegistrationWindow() 
	    {
	        // This method will open the new window and close the current one
	    	new RegFrame();
	        this.dispose();  // Close the current window
	    }
	    
	    private void searchWindow()
	    {
	    	//This method will take you to search for matches window
	    	new SearchFrame();
	    	this.dispose();
	    }

    public static void main(String[] args) 
    {
        // Ensure the GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
