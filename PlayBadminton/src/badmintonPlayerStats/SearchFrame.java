package badmintonPlayerStats;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchFrame extends RegFrame
{
	private ImageIcon image = new ImageIcon("C://Users//milli//JAVA-2-Project//PlayBadminton//Images//badminton icon.jpg"); 
	private JLabel titleLabel = new JLabel("Search for matches!");
	private JButton findMatchButton = new JButton("Find Match");
	private JCheckBox aButton = new JCheckBox("A Level");
	private JCheckBox cButton = new JCheckBox("C Level");
	private JCheckBox dPlusButton = new JCheckBox("D+ Level");
	private JCheckBox dButton = new JCheckBox("D Level");
	private JTextField regionField = new JTextField("San Diego,Ca");
	private JLabel skillLabel = new JLabel("Select level(s):");
	private PlayerManager playerManager = new PlayerManager();
	Font defaultFont = new Font("Arial", Font.BOLD, 20);
	Font titleFontSize = new Font("Arial", Font.BOLD, 30);
    Color foregroundColor = Color.WHITE;
	JPanel contentPane;//custom content pane for background image
	
	public SearchFrame()
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
		    styleComponent(regionField, defaultFont, foregroundColor);
	        styleComponent(skillLabel, defaultFont, foregroundColor);
	        styleComponent(aButton, defaultFont, foregroundColor);
	        styleComponent(cButton, defaultFont, foregroundColor);
	        styleComponent(dButton, defaultFont, foregroundColor);
	        styleComponent(dPlusButton, defaultFont, foregroundColor);

		    //search panel:
		    JPanel searchPanel = new JPanel(new GridLayout(7,1));
		    searchPanel.setOpaque(false);
		    searchPanel.setBounds(350,50,250,500);
		    searchPanel.add(regionField);
		    searchPanel.add(skillLabel);
		    searchPanel.add(aButton);
			searchPanel.add(cButton);
			searchPanel.add(dPlusButton);
			searchPanel.add(dButton);
			findMatchButton.setFont(defaultFont);
			searchPanel.add(findMatchButton);
	
		    //Add components to content pane:
		    contentPane.add(titlePanel);
		    contentPane.add(searchPanel);
		  		
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

	public static void main(String[] args)
	{
		new SearchFrame();

	}

}
