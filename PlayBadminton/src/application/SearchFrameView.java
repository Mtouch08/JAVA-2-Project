package application;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchFrameView extends MainFrameView {
    private JPanel settingPanel,resultsPanel,menuPanel,searchPanel; 
    private ImageIcon settingimage = new ImageIcon("C://Users//milli//JAVA-2-Project//PlayBadminton//Images//settingicon.jpg");
    private JButton findMatchButton = new JButton("Find Match");
    private JCheckBox aButton = new JCheckBox("A Level");
    private JCheckBox bButton = new JCheckBox("B Level");
    private JCheckBox cButton = new JCheckBox("C Level");
    private JCheckBox dPlusButton = new JCheckBox("D+ Level");
    private JCheckBox dButton = new JCheckBox("D Level");
    private JLabel skillLabel = new JLabel("Select desired level(s):");
    private JTextField regionField = new JTextField("Enter your region");
    private PlayerManagerModel model;
    public SearchFrameView(PlayerManagerModel model) {
        super(model);
        this.model=model;
    	initFrame();
        initComponents();
        addComponents();
        setupListeners();
        setVisible(true);
    }
    
    private void initFrame() {
    	// Configure the frame
        setTitle("Play Badminton");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setResizable(false);
    }

    private void initComponents() {
    	initPanels();
        setPanelLayout();
        addSettingPanel();
        addMenuPanel();
        addSearchPanel();
        addResultPanel();
        
    }
    
    private void initPanels() {
    	// Initialize panels
    	settingPanel = new JPanel();
    	resultsPanel = new JPanel();
        searchPanel = new JPanel();
        menuPanel = new JPanel();
    }
    private void addSettingPanel() {
        JButton settingIconButton = new JButton(settingimage);         
        settingIconButton.setPreferredSize(new Dimension(50, 50)); 
        settingPanel.add(settingIconButton);
        setIconImage(image.getImage());
        settingIconButton.setOpaque(false);
    }
   
    private void addMenuPanel() {
        JToolBar toolBar = new JToolBar();

        // Add buttons to the toolBar
        toolBar.add(new JButton("Scores"));
        toolBar.add(new JButton("Calendar"));
        toolBar.add(new JButton("Latest"));
        toolBar.add(new JButton("Rankings"));
        toolBar.add(new JButton("More..."));
        
        // Add the toolBar to the menuPanel
        menuPanel.add(toolBar);
    }
   
   private void addSearchPanel() {
 
       regionField.setPreferredSize(new Dimension(100, 150)); // Set preferred size with smaller height
       //regionPanel.add(regionField);
       searchPanel.add(regionField);

       JSlider distanceSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 0); // min, max, initial
       distanceSlider.setMajorTickSpacing(10); // Set major tick spacing
       distanceSlider.setPaintLabels(true); // Display labels
       distanceSlider.setPreferredSize(new Dimension(250, 150)); // Set preferred size
       // Add listener to the distanceSlider
       distanceSlider.addChangeListener(new ChangeListener() {
           public void stateChanged(ChangeEvent e) {
               // Get the selected value from the slider
               int selectedDistance = ((JSlider) e.getSource()).getValue();
               // You can use the selectedDistance value in your search criteria
               System.out.println("Selected distance: " + selectedDistance);
           }
       });
    
       searchPanel.add(distanceSlider);
       searchPanel.add(skillLabel);
       searchPanel.add(aButton);
       searchPanel.add(bButton);
       searchPanel.add(cButton);
       searchPanel.add(dPlusButton);
       searchPanel.add(dButton);
       searchPanel.add(findMatchButton);
       // Set border for searchPanel
       searchPanel.setBorder(BorderFactory.createTitledBorder("Search Options"));
   }
   
   private void setupListeners() {
       ButtonListeners listener = new ButtonListeners(null, this, model);
       findMatchButton.setActionCommand("findMatch");
       findMatchButton.addActionListener(listener);
   }
   private void addResultPanel() {
       resultsPanel.setLayout(new BorderLayout()); // Set BorderLayout for resultsPanel
       JList<String> searchResultList = new JList<>(new String[]{}); 
       JScrollPane scrollPane = new JScrollPane(searchResultList);
       resultsPanel.add(scrollPane); // Add scroll pane to resultsPanel
   }
   
   // Method to display matching players in the results panel
   public void displayMatchingPlayers(List<Player> matchingPlayers) {
       DefaultListModel<Player> model = new DefaultListModel<>();
       JList<Player> searchResultList = new JList<>(model);
       searchResultList.setCellRenderer(new PlayerLinkRenderer());

       if (!matchingPlayers.isEmpty()) {
           for (Player player : matchingPlayers) {
               model.addElement(player);
           }
       } else {
           model.addElement(new Player("No matching players found for the selected criteria.", ""));
       }

       searchResultList.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               int index = searchResultList.locationToIndex(e.getPoint());
               if (index >= 0) {
                   Player selectedPlayer = model.getElementAt(index);
                   if (!selectedPlayer.getName().equals("No matching players found for the selected criteria.")) {
                       openPlayerProfile(selectedPlayer);
                   }
               }
           }
       });

       resultsPanel.removeAll();
       resultsPanel.setLayout(new BorderLayout());
       resultsPanel.add(new JScrollPane(searchResultList), BorderLayout.CENTER);
       resultsPanel.revalidate();
       resultsPanel.repaint();
   }

   private void openPlayerProfile(Player player) {
       model.setPlayer(player); 
       // Now open the UserProfileFrameView with the updated model
       new UserProfileFrameView(model);
   }
   
    private void setPanelLayout() {
    	// Set layouts for panels
        searchPanel.setLayout(new GridLayout(10,1)); 
        settingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuPanel.setLayout(new GridLayout(1, 6)); 
    }

    private void addComponents() {
        add(settingPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.SOUTH);
        add(searchPanel, BorderLayout.WEST); 
        add(resultsPanel, BorderLayout.CENTER);
    }

    public String getSelectedSkillLevels() {
        StringBuilder skillLevels = new StringBuilder();
        if (aButton.isSelected()) skillLevels.append("A,");
        if (bButton.isSelected()) skillLevels.append("B,");
        if (cButton.isSelected()) skillLevels.append("C,");
        if (dPlusButton.isSelected()) skillLevels.append("D+,");
        if (dButton.isSelected()) skillLevels.append("D,");
        if (skillLevels.length() > 0) skillLevels.setLength(skillLevels.length() - 1); // Remove trailing comma
        return skillLevels.toString();
    }
    
    public String getRegion() {
    	return regionField.getText();
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
    	new SearchFrameView(new PlayerManagerModel());
    }
}
