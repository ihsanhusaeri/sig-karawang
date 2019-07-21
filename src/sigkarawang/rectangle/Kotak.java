/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigkarawang.rectangle;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author ihsan
 */
public class Kotak extends JPanel{
    private Color color;
    public Kotak(Color c){
        color =c;
    }
    
    public void paintComponent(Graphics g){
        g.setColor(color);
        g.drawRect(5, 5, 30, 30);
        g.fillRect(5, 5, 30, 30);
        
    }
}
