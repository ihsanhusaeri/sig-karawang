/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigkarawang.panel;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ihsan
 */
public class DeskripsiPanel extends JPanel{
    private JTextField textKecamatan = new JTextField(15);
    private JTextField textJumlahPenduduk = new JTextField(15);
    private JTextField textLuas = new JTextField(15);
    private JTextField textKepadatan = new JTextField(15);
    private JTextField textLajuPertumbuhanPenduduk = new JTextField(15);
    private JTextField textJumlahDesa = new JTextField(15);
    
    public DeskripsiPanel(){
        setLayout(new GridLayout(10, 2, 10, 10));
        
        
        add(new JLabel("Kecamatan: "));
        add(textKecamatan);
        add(new JLabel("Jumlah Penduduk: "));
        add(textJumlahPenduduk);
        add(new JLabel("Luas: "));
        add(textLuas);
        add(new JLabel("Kepadatan: "));
        add(textKepadatan);
        add(new JLabel("Pertumbuhan Penduduk"));
        add(textLajuPertumbuhanPenduduk);
        add(new JLabel("Jumlah Desa"));
        add(textJumlahDesa);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(BorderFactory.createTitledBorder("Deskripsi"));
    }
    
}
