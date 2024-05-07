package badmintonPlayerStats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchFrameView extends MainFrameView {
    private JPanel settingPanel; // Top left panel with setting icon button
    private JPanel resultsPanel; // Center panel with search result list
    private JPanel menuPanel; // Bottom panel with JMenu
    private JPanel searchPanel; // Left panel with search components
    private ImageIcon settingimage = new ImageIcon("C://Users//milli//JAVA-2-Project//PlayBadminton//Images//settingicon.jpg");
    private JButton findMatchButton = new JButton("Find Match");
    private JCheckBox aButton = new JCheckBox("A Level");
    private JCheckBox bButton = new JCheckBox("B Level");
    private JCheckBox cButton = new JCheckBox("C Level");
    private JCheckBox dPlusButton = new JCheckBox("D+ Level");
    private JCheckBox dButton = new JCheckBox("D Level");
    private JLabel skillLabel = new JLabel("Select desired level(s):");
    private JTextField regionField = new JTextField("Enter your region");

    public SearchFrameView(PlayerManagerModel model) {
        super(model);
        this.model=model;
    	initFrame();
        initComponents();
        addComponents();
        addListeners();
       
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
    // Create a panel to hold the regionField
       //JPanel regionPanel = new JPanel();
       regionField.setPreferredSize(new Dimension(100, 150)); // Set preferred size with smaller height
       //regionPanel.add(regionField);
       searchPanel.add(regionField);

       // Create the JSlider
       JSlider distanceSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 0); // min, max, initial
       distanceSlider.setMajorTickSpacing(10); // Set major tick spacing
       distanceSlider.setPaintLabels(true); // Display labels
       distanceSlider.setPreferredSize(new Dimension(250, 150)); // Set preferred size

       // Add the slider to the menuPanel
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
   
   private void addResultPanel() {
	   resultsPanel.setLayout(new BorderLayout()); // Set BorderLayout for resultsPanel
       JList<String> searchResultList = new JList<>(new String[]{"Result 1", "Result 2", "Result 3"}); // Example data
       JScrollPane scrollPane = new JScrollPane(searchResultList);
       resultsPanel.add(scrollPane); // Add scroll pane to resultsPanel

       // Set resultsPanel to the center of the page
       
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
    

    private void addListeners() {
        findMatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String region = regionField.getText();
                String skillLevel = getSelectedSkillLevel();
                ArrayList<Player> matchingPlayers = model.findMatchingPlayers(region, skillLevel);
                // Do something with matchingPlayers, like displaying them in a list
            }
        });
    }

    private String getSelectedSkillLevel() {
        StringBuilder skillLevel = new StringBuilder();
        if (aButton.isSelected()) skillLevel.append("A Level ");
        if (bButton.isSelected()) skillLevel.append("B Level ");
        if (cButton.isSelected()) skillLevel.append("C Level ");
        if (dPlusButton.isSelected()) skillLevel.append("D+ Level ");
        if (dButton.isSelected()) skillLevel.append("D Level ");
        return skillLevel.toString().trim();
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
            new SearchFrameView(new PlayerManagerModel());
            
        });
    }
}
