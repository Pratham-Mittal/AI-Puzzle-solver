package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JLabel implements MouseListener {

    ActionListener listener;

    public Button(String str){
        super(str);
        this.setForeground(Color.yellow);
        this.setOpaque(false);
        this.addMouseListener(this);
    }
    public void addActionListener(ActionListener al){
        listener = al;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        listener.actionPerformed(new ActionEvent(this, 501, ""));
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setForeground(new Color(255, 59, 0));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(Color.yellow);
    }
}