package badmintonPlayerStats;

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
import javax.swing.*;
import java.awt.*;

public class RegFrame extends JFrame
{
	private ImageIcon image = new ImageIcon("C://Users//milli//JAVA-2-Project//PlayBadminton//Images//badminton icon.jpg"); 
	private JLabel titleLabel = new JLabel("Player Registration");
	private JButton regPlayerButton = new JButton("Register");
	private JTextField regionField = new JTextField("San Diego,Ca");	
	private PlayerManager playerManager = new PlayerManager();
	private JTextField nameField = new JTextField("Millie Tan");
	private JPasswordField passwordField = new JPasswordField("Enter Password");
	private JTextField skillField = new JTextField("Enter skill level: A,C,D+,D");
	Font defaultFont = new Font("Arial", Font.BOLD, 20);
	Font titleFontSize = new Font("Arial", Font.BOLD, 30);
    Color foregroundColor = Color.WHITE;
	JPanel contentPane;//custom content pane for background image
	
	
	public RegFrame()
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
	    
	      
	    //registration panel:
	    JPanel regPanel = new JPanel(new GridLayout(5,1));
	    regPanel.setBounds(510,110,250,300);
	    regPanel.setOpaque(false);
	    styleComponent(nameField, defaultFont, foregroundColor);
        styleComponent(regionField, defaultFont, foregroundColor);
        styleComponent(skillField, defaultFont, foregroundColor);
        styleComponent(passwordField, defaultFont, foregroundColor);       
	    regPanel.add(nameField);
	    regPanel.add(regionField);
	    regPanel.add(skillField);
	    regPanel.add(passwordField);
	    regPanel.add(regPlayerButton);
	    regPlayerButton.setFont(defaultFont);
	    regPlayerButton.addActionListener( new RegistrationListener());
	    
	    //Add components to content pane:
	    contentPane.add(titlePanel);
	    contentPane.add(regPanel);
	  		
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
	
	  public void styleComponent(JComponent component, Font font, Color foregroundColor) 
	  {
	        component.setOpaque(false);
	        component.setFont(font);
	        component.setForeground(foregroundColor);
	    }
	
	public static void main(String[] args) 
    {
        //new RegFrame();
        //run the frame
        SwingUtilities.invokeLater(() -> new RegFrame());   
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
	            returnToLogin();
	        }
	}
	 	
	 	private void returnToLogin() 
	    {
	        // This method will open the new window and close the current one
	    	new MainFrame();
	        this.dispose();  // Close the current window
	    }
}