/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigkarawang.panel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ihsan
 */
public class KeteranganPanel extends JPanel{
    public KeteranganPanel(){
        add(new JLabel("Jumlah Penduduk"));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setBorder(BorderFactory.createTitledBorder("Keterangan"));
        setFont(new Font("Abyssinica SIL", Font.PLAIN, 12));
        
    }
}
