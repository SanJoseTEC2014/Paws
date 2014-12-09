package paws.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class ImageIconCellRenderer extends DefaultTableCellRenderer {
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel temp = new JLabel();
        if (value!=null) {
        	temp.setHorizontalAlignment(JLabel.CENTER);
        	temp.setIcon(new ImageIcon((byte[]) value));
        }
        return temp;
    }
}
