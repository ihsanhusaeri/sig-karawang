/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigkarawang.details;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sigkarawang.model.Kecamatan;

/**
 *
 * @author ihsan
 */
public class Detail extends JFrame{
    private String[] labels= {"Kecamatan", "Jumlah Penduduk", "Luas Wilayah", "Kepadatan Penduduk", "Ibukota Kecamatan", "Tinggi Wilayah (DPL)", "Jumlah Desa/Kelurahan", "Rasio Jenis Kelamin"};
    private Kecamatan kecamatan;
    private JTextField[] textDeskripsi;
    
    public Detail(Kecamatan kecamatan){
        this.kecamatan = kecamatan;
        
        add(makeCenterPanel(), BorderLayout.CENTER);
        add(makeNorthPanel(), BorderLayout.NORTH);
        add(makeWestPanel(), BorderLayout.WEST);
        add(makeEastPanel(), BorderLayout.EAST);
        
        showDetails();
        
        setResizable(false);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public JPanel makeCenterPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2, 10, 10));
        
        textDeskripsi = new JTextField[labels.length];
       
        for(int i=0; i < labels.length; i++){
            panel.add(new JLabel(labels[i]));
            textDeskripsi[i] = new JTextField(15);
            textDeskripsi[i].setEditable(false);
            panel.add(textDeskripsi[i]);
        }
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        return panel;
    }
    public JPanel makeNorthPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
       
        JLabel logo = new JLabel(new ImageIcon("sigkarawang/file/3.png"));
        JLabel judul = new JLabel("Data tahun 2018");
        
        setFont(judul, "Times New Roman", Font.BOLD, 18);
        logo.setSize(100, 100);
        
        panel.add(logo);
        panel.add(judul);
        
        return panel;
    }
    public JPanel makeWestPanel(){
        JPanel panel = new JPanel();
        
        panel.add(new JLabel("     "));
        
        return panel;
    }
    public JPanel makeEastPanel(){
        JPanel p = new JPanel();
        
        p.add(new JLabel("    "));
        
        return p;
                
    }
    private void setFont(Component component, String fontType, int plain, int size){
        component.setFont(new Font(fontType, plain, size));
    }
    public void showDetails(){
        textDeskripsi[0].setText(kecamatan.getNama());
        textDeskripsi[1].setText(getDecimalFormat(kecamatan.getPenduduk2018())+" jiwa");
        textDeskripsi[2].setText(getDecimalFormat(kecamatan.getLuas())+" km\u00b2");
        textDeskripsi[3].setText(getDecimalFormat(kecamatan.getPenduduk2018() / kecamatan.getLuas())+" jiwa/km\u00b2");
        textDeskripsi[4].setText(kecamatan.getIbukota());
        textDeskripsi[5].setText(String.format("%.2f m",kecamatan.getTinggi()));
        textDeskripsi[6].setText(String.format("%.0f", kecamatan.getJumlahDesa()));
        textDeskripsi[7].setText(String.format("%.2f", kecamatan.getRasioJK()));
        
        
    }
    public String getDecimalFormat(double angka){
        DecimalFormat decimalFormat= new DecimalFormat("#,###");
        String stringFormat = decimalFormat.format(angka);
        
        return stringFormat;
    }
  
}
