package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//Defines a custom renderer for list items that are Player objects.
public class PlayerLinkRenderer extends JLabel implements ListCellRenderer<Player> {
    public PlayerLinkRenderer() {
        setOpaque(true);
    }
 // Method to customize the rendering of each cell in the list
    @Override
    public Component getListCellRendererComponent(JList<? extends Player> list, Player value, int index, boolean isSelected, boolean cellHasFocus) {
    	// Sets the text of this label as an HTML hyperlink. The actual Player name is displayed.
        setText("<html><a href=''>" + value.getName() + "</a></html>");
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
