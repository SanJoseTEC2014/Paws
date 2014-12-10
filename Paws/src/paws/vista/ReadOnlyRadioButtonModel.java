package paws.vista;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.event.ChangeListener;

public class ReadOnlyRadioButtonModel implements ButtonModel {  
    // Extraído de la siguiente página web:
	// http://www.coderanch.com/t/346898/GUI/java/Making-radio-buttons-noneditable
	private boolean selected;  
      
    public ReadOnlyRadioButtonModel(boolean isSelected) {  
        selected = isSelected;  
    }  
    public boolean isSelected()  
    {  
        return selected;  
    }  
    
    public boolean isArmed() { return false; }  
    public boolean isEnabled() { return true; }  
    public boolean isPressed() { return false; }  
    public boolean isRollover() { return false; }  
    public void setArmed(boolean b) { }  
    public void setEnabled(boolean b) { }  
    public void setPressed(boolean b) { }  
    public void setRollover(boolean b) { }  
    public void setSelected(boolean b) { }  
    public void setActionCommand(String s) { }  
    public void setGroup(ButtonGroup group) { }  
    public void setMnemonic(int key) { }  
    public String getActionCommand() { return "static"; }  
    public int getMnemonic() { return java.awt.event.KeyEvent.VK_UNDEFINED; }  
    public Object[] getSelectedObjects() { return null; }  
    public void addActionListener(ActionListener l) { }  
    public void addChangeListener(ChangeListener l) { }  
    public void addItemListener(ItemListener l) { }  
    public void removeActionListener(ActionListener l) { }  
    public void removeChangeListener(ChangeListener l) { }  
    public void removeItemListener(ItemListener l) { }

}
