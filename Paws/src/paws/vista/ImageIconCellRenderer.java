package paws.vista;

import java.awt.Component;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import paws.control.Imagenes;
import paws.control.excepciones.ImagenNoEncontradaException;

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
