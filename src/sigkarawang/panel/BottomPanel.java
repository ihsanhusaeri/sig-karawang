/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigkarawang.panel;

import sigkarawang.PrediksiView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author ihsan
 */
public class BottomPanel extends JPanel{
    private JButton buttonPrediksi = new JButton("Prediksi Penduduk");
    public BottomPanel(){
        add(buttonPrediksi);
        
        buttonPrediksi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PrediksiView prediksi = new PrediksiView();
                
            }
            
        });
    }
    
}
