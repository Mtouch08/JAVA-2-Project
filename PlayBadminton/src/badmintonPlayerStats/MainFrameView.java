package badmintonPlayerStats;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class MainFrameView extends JFrame
{
	private JTextField loginEmailField = new JTextField("Enter email address");
	private JPasswordField loginPasswordField = new JPasswordField("Enter Password");
	private JTextField signUpEmailField = new JTextField("Enter email address");
	private JPasswordField signUpPasswordField = new JPasswordField("Enter Password");
	private JTextField nameField = new JTextField("Enter First and Last Name");
	private JPasswordField passwordField = new JPasswordField("Enter Password");
	private JLabel signUpLabel = new JLabel("I don't have an account:");
	private JButton signUpButton = new JButton("Sign Up");
	private JButton loginButton = new JButton("Login");
	private JButton registerButton = new JButton("Register");
	protected JPanel contentPane; //custom content pane for background image
	protected ImageIcon image = new ImageIcon("C://Users//milli//JAVA-2-Project//PlayBadminton//Images//badminton icon.jpg");
	protected JLabel titleLabel = new JLabel("Search > Request Match > Play! Let's Go!");
	protected PlayerManagerModel model;
	private Font defaultFont = new Font("Arial", Font.BOLD, 20);
	private Font titleFontSize = new Font("Arial", Font.BOLD, 30);
	private Color foregroundColor = Color.WHITE;
	private Color titleLabelColor = Color.YELLOW;
	private JTextField regionField = new JTextField("San Diego,Ca");
	private JTextField skillField = new JTextField("Enter skill level: A,C,D+,D");
	private JPanel signUpPanel;
	private JPanel loginPanel;
	private JPanel newUserPanel;
	protected JPanel titlePanel = new JPanel(); 
	
	
	public MainFrameView(PlayerManagerModel model)
	{
		initializeModel(model);
		initializeFrame();
		initializeUI();
		setVisible(true);
	 }
	
	protected void initializeFrame()
	{
		setTitle("Play Badminton");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setResizable(false);
	}

	private void initializeModel(PlayerManagerModel model)
	{
		this.model=model;
	}

	private void initializeUI()
	{
		contentPane = new JPanel(){
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			//Load background image and draw it
			//image from acesporty.com
			ImageIcon backgroundImage = new ImageIcon("C://Users//milli//JAVA-2-Project//PlayBadminton//Images//badmintonbackground.jpg");
			g.drawImage(backgroundImage.getImage(),0, 0,getWidth(), getHeight(),this);
			}
		};
		contentPane.setLayout(null);
		addContentPane();

	}
	
	private void addContentPane()
	{
		//image from acesporty.com
		// Configure the frame
		setIconImage(image.getImage());//fineartamerica.com
		setContentPane(contentPane);
		initPanels();
		setPanelLayout();
		addPanels();
		setFontStyle();
		setPanelStyle();
		addTitlePanel();
		addLoginPanel();
		addNewUserPanel();
		addSignUpPanel();
		
		signUpPanel.setVisible(false);
	}
	
	private void initPanels() {
		titlePanel = new JPanel();
		loginPanel = new JPanel();
		newUserPanel = new JPanel();
		signUpPanel = new JPanel();
	}
	
	private void addPanels() {
		contentPane.add(titlePanel);
		contentPane.add(loginPanel);
		contentPane.add(newUserPanel);
		contentPane.add(signUpPanel);
		
	}
	
	private void setPanelLayout() {
		loginPanel.setLayout(new GridLayout(3,1));
		newUserPanel.setLayout(new GridLayout(2,1));
		signUpPanel.setLayout(new GridLayout(6,1));

	}
	private void addTitlePanel()
	{
	    
	    titlePanel.setBounds(100,0,600,100);
	    titlePanel.add(titleLabel);

	}
	
	public void addLoginPanel()
	{
		loginPanel.setBounds(510,150,250,200);
		loginPanel.add(loginEmailField);
		loginPanel.add(loginPasswordField);
		loginPanel.add(loginButton);
		loginButton.addActionListener( new LoginListener());
	}
	
	public void addNewUserPanel() {
		newUserPanel.setBounds(510,350,250,150);
		newUserPanel.add(signUpLabel);
		newUserPanel.add(signUpButton);
		signUpButton.addActionListener( new signUpListener());	
	}
	
	public void addSignUpPanel() {
		signUpPanel.setBounds(300,250,250,300);
		
		signUpPanel.add(nameField);
		signUpPanel.add(signUpEmailField);
		signUpPanel.add(regionField);
		signUpPanel.add(skillField);
		signUpPanel.add(signUpPasswordField);
		signUpPanel.add(registerButton);
		registerButton.addActionListener( new RegisterListener());	
	}
	public void setFontStyle()
	{
		styleComponent(nameField, defaultFont, foregroundColor);
		styleComponent(loginEmailField, defaultFont, foregroundColor);
		styleComponent(passwordField, defaultFont, foregroundColor);
		styleComponent(signUpLabel, defaultFont, foregroundColor);	
		styleComponent(titleLabel, titleFontSize, titleLabelColor);	
		styleComponent(regionField, defaultFont, foregroundColor);
		styleComponent(loginPasswordField,defaultFont,foregroundColor);
		loginButton.setFont(defaultFont);
		signUpButton.setFont(defaultFont);
		registerButton.setFont(defaultFont);
	}

	 public void styleComponent(JComponent component, Font font, Color foregroundColor) 
	  {
	        component.setOpaque(false);
	        component.setFont(font);
	        component.setForeground(foregroundColor);
	  }
	 
	 private void setPanelStyle() {
		 titlePanel.setOpaque(false);
		 loginPanel.setOpaque(false);
		 newUserPanel.setOpaque(false);
		 signUpPanel.setOpaque(false);
	 }
	 
	  private class LoginListener implements ActionListener {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String email = loginEmailField.getText();
	            char[] password = passwordField.getPassword();

	            try {
	                // Check if email or password fields are empty
	                if (email.isEmpty() || password.length == 0) {
	                    throw new IllegalArgumentException("Email or password cannot be empty.");
	                }

	                // Attempt to validate login
	                if (model.validateLogin(email, password)) {
	                    JOptionPane.showMessageDialog(MainFrameView.this, "Login successful!");
	                    goToSearchFrame();
	                } else {
	                    JOptionPane.showMessageDialog(MainFrameView.this, "Invalid email or password.");
	                }
	            } catch (IllegalArgumentException ex) {
	                JOptionPane.showMessageDialog(MainFrameView.this, "Error: " + ex.getMessage());
	            } finally {
	                // Clear the password from memory for security
	                Arrays.fill(password, '\0');
	            }
	        }
	    }

		// Inner class for handling registration
	 	private class signUpListener implements ActionListener {
	 		 @Override
	 	    public void actionPerformed(ActionEvent event) {
	 	        loginPanel.setVisible(false);
	 	        newUserPanel.setVisible(false);
	 	        signUpPanel.setVisible(true); 
	 	    }
	    }
	 	
	    private class RegisterListener implements ActionListener {
	        @Override
	        public void actionPerformed(ActionEvent event2) {
	        	   String name = nameField.getText();
	               String region = regionField.getText();
	               String skillLevel = skillField.getText();
	               char[] password = passwordField.getPassword(); // Securely handle password

	               try {
	                   model.registerPlayer(name, region, skillLevel);
	                   JOptionPane.showMessageDialog(MainFrameView.this, "User registered!");
	                   returnToLogin(); // Return to the login view
	               } catch (Exception e) {
	                   JOptionPane.showMessageDialog(MainFrameView.this, "Registration failed. Please try again.");
	               }
	           }
	       }
	    
	    private void goToSearchFrame() {
	    	new SearchFrameView(model);
	    	this.dispose();
	    }
	 	
	private void returnToLogin() {
		signUpPanel.setVisible(false);
	    newUserPanel.setVisible(false);
	    loginPanel.setVisible(true);
	}

    public static void main(String[] args) 
    {
    	new MainFrameView(new PlayerManagerModel());
    }
}

