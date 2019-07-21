/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigkarawang;

import com.bbn.openmap.LayerHandler;
import com.bbn.openmap.MouseDelegator;
import com.bbn.openmap.PropertyHandler;
import com.bbn.openmap.event.OMMouseMode;
import com.bbn.openmap.gui.OverlayMapPanel;
import com.bbn.openmap.gui.ScaleTextPanel;
import com.bbn.openmap.gui.ToolPanel;
import com.bbn.openmap.layer.GraticuleLayer;
import com.bbn.openmap.layer.shape.ShapeLayer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import sigkarawang.coordinates.Coordinates;
import sigkarawang.lab.KecamatanLab;
import sigkarawang.layer.PolygonLayer;
import sigkarawang.color.ColorMap;
import sigkarawang.details.Detail;
import sigkarawang.model.Kecamatan;
import sigkarawang.rectangle.Kotak;
import sigkarawang.trend.TrendAnalysis;

/**
 *
 * @author ihsan
 */
public class PrediksiView extends JFrame implements ActionListener{
    private JPanel northPanel= new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel topOfNorth = new JPanel();
    private JPanel bottomOfNorth = new JPanel();
    private JTable tabel;
    private JScrollPane scroller;
    private String[] tahunAwal = {"Tahun Awal", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010"};
    private String[] atributTahun = {"pnddk2018", "pnddk2017", "pnddk2016", "pnddk2015", "pnddk2014", "pnddk2013", "pnddk2012", "pnddk2011", "pnddk2010"}; 
    
    private List<Kecamatan> listKecamatan;
    private JLabel[] labelKecamatan;
    private JTextField[] textFieldTahunAwal;
    private JTextField[] textFieldTahunPrediksi;
    private JTextField[] textFieldKepadatanTahunAwal;
    private JTextField[] textFieldKepadatanTahunPrediksi;
    private JTextField[] textFieldSelisih;
    private JButton buttonProses;
    private JButton buttonProsesSatu;
    private JTextField textTahunPrediksi;
    private JTextField textTahunAwal;
    private TrendAnalysis trendLinear;
    private List<Double> listJP;
    private JLabel atributKecamatan = new JLabel("Kecamatan");
    private JLabel atributPrediksi = new JLabel("Tahun Prediksi");
    private JLabel atributTahunAwal = new JLabel("Tahun Awal");
    private JLabel atributKepadatanAwal = new JLabel("Kepadatan Penduduk");
    private JLabel atributKepadatanPrediksi = new JLabel("Kepadatan Penduduk");
    private JLabel atributSelisih = new JLabel("Prediksi Pertambahan\nPenduduk");
    
    private JLabel labelTotal = new JLabel("Total");
    private JTextField textTotalAwal;
    private JTextField textTotalPrediksi;
    private JTextField textTotalSelisih;
    private double totalAwal = 0.0;
    private double totalPrediksi = 0.0;
    private double totalSelisih = 0.0;
    private PolygonLayer polygonLayerMapSatu;
    private PolygonLayer polygonLayerMapDua;
    private Coordinates coordinates;
    private Coordinates coordinates2;
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private Map<Integer, List<Double>> mapCoordinates;
    Map<Integer, List<Double>>mapCoordinates2;
    public PrediksiView(){
        super("Prediksi Penduduk");
        setLayout(new BorderLayout());
        
        //polygonLayer = new PolygonLayer();
        coordinates = new Coordinates();
        coordinates2 = new Coordinates();
        setLatLon();
        northPanel.setLayout(new BorderLayout(10,5));
        
        topOfNorth.setLayout(new GridLayout(1, 6, 10, 10));
        
        listKecamatan = KecamatanLab.getListKecamatan();
      
        textTahunAwal = new JTextField();
        textTahunAwal.setText("2018");
        textTahunAwal.setEditable(false);
        labelKecamatan = new JLabel[listKecamatan.size()];
        textFieldTahunAwal = new JTextField[listKecamatan.size()];
        textFieldTahunPrediksi = new JTextField[listKecamatan.size()];
        textFieldKepadatanTahunAwal = new JTextField[listKecamatan.size()];
        textFieldKepadatanTahunPrediksi = new JTextField[listKecamatan.size()];
        textFieldSelisih = new JTextField[listKecamatan.size()];
        
        
        /*topOfNorth.add(new JLabel(""));
        topOfNorth.add(new JLabel(""));
        topOfNorth.add(new JLabel(""));
        topOfNorth.add(new JLabel(""));
        topOfNorth.add(new JLabel(""));
        topOfNorth.add(new JLabel(""));*/
        buttonProsesSatu = new JButton("Proses");
        buttonProsesSatu.setActionCommand("ProsesSatu");
        topOfNorth.add(textTahunAwal);
        topOfNorth.add(buttonProsesSatuPanel());
        buttonProsesSatu.addActionListener(this);
        
        
        textTahunPrediksi = new JTextField();
        textTahunPrediksi.setText(new String("2019"));
        topOfNorth.add(textTahunPrediksi);
        buttonProses = new JButton("Proses");
        topOfNorth.add(buttonProsesPanel());
        buttonProses.addActionListener(this);
        
        topOfNorth.add(new JLabel(""));
        topOfNorth.add(new JLabel(""));
        
        
        northPanel.add(topOfNorth, BorderLayout.NORTH);
        
        OverlayMapPanel mapSatu = getOverlayMapPanel();
         
        OverlayMapPanel mapDua = getOverlayMapPanel();
        
        polygonLayerMapSatu = new PolygonLayer();
        polygonLayerMapDua = new PolygonLayer();
        
        mapSatu.addMapComponent(polygonLayerMapSatu);
        mapDua.addMapComponent(polygonLayerMapDua);
        
        
        
        bottomOfNorth.setLayout(new BorderLayout());
        
        bottomOfNorth.add(makeMapPanel(mapSatu, mapDua), BorderLayout.CENTER);
        bottomOfNorth.add(makeAttributePanel(), BorderLayout.SOUTH);
        
        northPanel.add(bottomOfNorth, BorderLayout.SOUTH);
        
        
        add(northPanel, BorderLayout.NORTH);
     
        centerPanel.setLayout(new GridLayout(31,7,10,10));
        
        setFont(atributKecamatan, "Abyssinica SIL", Font.BOLD, 10);
        setFont(atributTahunAwal, "Abyssinica SIL", Font.BOLD, 10);
        setFont(atributPrediksi, "Abyssinica SIL", Font.BOLD, 10);
        setFont(atributKepadatanAwal, "Abyssinica SIL", Font.BOLD, 10);
        setFont(atributKepadatanPrediksi, "Abyssinica SIL", Font.BOLD, 10);
        setFont(atributSelisih, "Abyssinica SIL", Font.BOLD, 10);
        
        
        for(int i=0; i < listKecamatan.size(); i++){
            labelKecamatan[i] = new JLabel((i+1)+ ". "+listKecamatan.get(i).getNama());
            centerPanel.add(labelKecamatan[i]);
            setFont(labelKecamatan[i], "Abyssinica SIL", Font.PLAIN, 13);
            textFieldTahunAwal[i] = new JTextField(15);
            textFieldTahunAwal[i].setEditable(false);
            setFont(textFieldTahunAwal[i], "Abyssinica SIL", Font.PLAIN, 13);
            centerPanel.add(textFieldTahunAwal[i]);
            
            textFieldKepadatanTahunAwal[i] = new JTextField(15);
            setFont(textFieldKepadatanTahunAwal[i], "Abyssinica SIL", Font.PLAIN, 13);
            textFieldKepadatanTahunAwal[i].setEditable(false);
            centerPanel.add(textFieldKepadatanTahunAwal[i]);
            
            textFieldTahunPrediksi[i] = new JTextField(15);
            setFont(textFieldTahunPrediksi[i], "Abyssinica SIL", Font.PLAIN, 13);
            textFieldTahunPrediksi[i].setEditable(false);
            centerPanel.add(textFieldTahunPrediksi[i]);
            
            textFieldKepadatanTahunPrediksi[i]= new JTextField(15);
            setFont(textFieldKepadatanTahunPrediksi[i], "Abyssinica SIL", Font.PLAIN, 13);
            textFieldKepadatanTahunPrediksi[i].setEditable(false);
            centerPanel.add(textFieldKepadatanTahunPrediksi[i]);
            
            textFieldSelisih[i] = new JTextField(15);
            setFont(textFieldSelisih[i], "Abyssinica SIL", Font.PLAIN, 13);
            textFieldSelisih[i].setEditable(false);
            centerPanel.add(textFieldSelisih[i]);
            
            centerPanel.add(makeButtonDetails(String.valueOf(i)));
        }
        setFont(labelTotal, "Abyssinica SIL", Font.PLAIN, 13);
        centerPanel.add(labelTotal);
        
        textTotalAwal = new JTextField(15);
        centerPanel.add(textTotalAwal);
        setFont(textTotalAwal, "Abyssinica SIL", Font.PLAIN, 13);
        
        centerPanel.add(new JLabel(""));
        
        textTotalPrediksi = new JTextField(15);
        centerPanel.add(textTotalPrediksi);
        setFont(textTotalPrediksi, "Abyssinica SIL", Font.PLAIN, 13);
        
        centerPanel.add(new JLabel(""));
        
        textTotalSelisih = new JTextField(15);
        centerPanel.add(textTotalSelisih);
        setFont(textTotalSelisih, "Abyssinica SIL", Font.PLAIN, 13);
        
        scroller = new JScrollPane(centerPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        add(scroller, BorderLayout.CENTER);
        
       
        setFont(textTahunAwal, "Abyssinica SIL", Font.PLAIN, 11);
        setFont(textTahunPrediksi, "Abyssinica SIL", Font.PLAIN, 11);
        setFont(buttonProses, "Abyssinica SIL", Font.PLAIN, 11);
        
        
        
        buttonProsesSatu.addActionListener(this);
        
        
        //add(makeEastPanel(), BorderLayout.EAST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        //setResizable(false);
        //setSize(new Dimension(1250, 700));
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    public OverlayMapPanel getOverlayMapPanel(){
        OverlayMapPanel map = new OverlayMapPanel(new PropertyHandler(new Properties()), true);
        map.create();
        
        ToolPanel toolPanel = new ToolPanel();
        
        toolPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        map.getMapBean().setBackgroundColor(Color.decode("#daebfe"));
        
        map.addMapComponent(toolPanel);
        
        map.addMapComponent(new LayerHandler());
        map.addMapComponent(new MouseDelegator());
        map.addMapComponent(new OMMouseMode());
        
        //map.addMapComponent(new GraticuleLayer());
        toolPanel.add(new JLabel("Skala: "));
        map.addMapComponent(new ScaleTextPanel());
        
        map.getMapBean().setScale(643742);
        map.getMapBean().setCenter(-6.289811487218118, 107.34);
         
        ShapeLayer shapeLayer = new ShapeLayer();
        
        Properties shapeLayerProps = new Properties();
        shapeLayerProps.put("prettyName", "Political Solid");
        //shapeLayerProps.put("BackgroundColor", "#ffffff");
        shapeLayerProps.put("shapeFile", "karawang.shp");
        
        shapeLayer.setProperties(shapeLayerProps);
        
        map.addMapComponent(shapeLayer);
        map.includeExitMenuItem();
        return map;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Proses")){
           try{
               
           
            int tahunPrediksi = Integer.parseInt(textTahunPrediksi.getText());
            polygonLayerMapDua.clear();
            addLabelMap(polygonLayerMapDua);
            if(tahunPrediksi >= 2019){
                for(int i=0; i < listKecamatan.size(); i++){
                    String key = listKecamatan.get(i).getNama();
                    List<Double> listJumlahPenduduk = listKecamatan.get(i).getJumlahPenduduk().get(key);
                    
                    List<Double> listCoordinates = mapCoordinates.get(i);
                    
                    TrendAnalysis trend = new TrendAnalysis(listJumlahPenduduk);
                    //System.out.println();
                    double xTahunPrediksi = (Double.parseDouble(textTahunPrediksi.getText()) - 2018) + trend.getMaximumX();
                    //System.out.println("xTahunPrediksi: "+xTahunPrediksi);
                    double prediksi = trend.getA() + (trend.getB() * xTahunPrediksi);
                    totalPrediksi += prediksi;
                    textFieldTahunPrediksi[i].setText(getDecimalFormat(prediksi)+" jiwa");
                    
                    textFieldKepadatanTahunPrediksi[i].setText(getDecimalFormat(prediksi/listKecamatan.get(i).getLuas())+ " jiwa/km\u00b2");
                    
                    atributPrediksi.setText("Jumlah Penduduk "+textTahunPrediksi.getText());
                    atributKepadatanPrediksi.setText("Kepadatan Penduduk "+textTahunPrediksi.getText());
                    
                    
            
                    double[] latlon = new double[listCoordinates.size()];
                    for(int j=0; j < listCoordinates.size(); j++){
                        if((j%2) ==0){
                            latlon[j] = listCoordinates.get(j+1);
                        }else{
                            latlon[j] = listCoordinates.get(j-1);
                        }
                    }
            
                    setColorMap(prediksi, latlon, polygonLayerMapDua);
                    //System.out.print("latlon size: "+latlon.length);
                }   
                textTotalPrediksi.setText(getDecimalFormat(totalPrediksi)+" jiwa");
                totalPrediksi = 0.0;
                hitungSelisih();
            
            }else{
                JOptionPane.showMessageDialog(null, "Prediksi tahun yang akan datang!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
           }catch(NumberFormatException except){
               JOptionPane.showMessageDialog(null, "Penulisan tahun salah!", "ERROR", JOptionPane.ERROR_MESSAGE);
           }
        }
        if(e.getActionCommand().equals("ProsesSatu")){
            int tahunAwal = Integer.parseInt(textTahunAwal.getText());
            polygonLayerMapSatu.clear();
            addLabelMap(polygonLayerMapSatu);
            for(int i =0; i< listKecamatan.size(); i++){
                String key = listKecamatan.get(i).getNama();
                
                Double tahun = listKecamatan.get(i).getJumlahPenduduk().get(key).get(0);
                 textFieldTahunAwal[i].setText(getDecimalFormat(tahun)+" jiwa");
                 atributTahunAwal.setText("Jumlah Penduduk 2018");
                 atributKepadatanAwal.setText("Kepadatan Penduduk 2018");
                 
                 textFieldKepadatanTahunAwal[i].setText(getDecimalFormat(tahun / listKecamatan.get(i).getLuas())+" jiwa/km\u00b2");
                 
                 totalAwal+= tahun;
                 
                 List<Double> listCoordinates = mapCoordinates.get(i);
                 double[] latlon = new double[listCoordinates.size()];
                 for(int k=0; k < listCoordinates.size(); k++){
                     if((k%2) ==0){
                         latlon[k] = listCoordinates.get(k+1);
                     }else{
                         latlon[k] = listCoordinates.get(k-1);
                     }
                 }
                 setColorMap(tahun, latlon, polygonLayerMapSatu);
            }
      
            textTotalAwal.setText(getDecimalFormat(totalAwal)+" jiwa");
            totalAwal = 0.0;
            hitungSelisih();
        }else{
           for(int i=0; i < listKecamatan.size();i++){
               if(e.getActionCommand().equals(String.valueOf(i))){
                   Detail profile = new Detail(listKecamatan.get(i));
                   
                   break;
               }
               
           } 
        }
    }
    public JPanel buttonProsesPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        
        panel.add(buttonProses);
        panel.add(new JLabel(""));
        
        return panel;
    }
    public JPanel buttonProsesSatuPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        
        panel.add(buttonProsesSatu);
        panel.add(new JLabel(""));
        
        return panel;
    }
    public void setFont(Component component, String fontType, int fontPlain, int fontSize){
        component.setFont(new Font(fontType, fontPlain, fontSize));
    }
    public JPanel makeEastPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        JPanel eastWestPanel = new JPanel();
        JPanel eastCenterPanel = new JPanel();
        
        eastWestPanel.setLayout(new GridLayout(8, 1, 10, 10));
        
        
        eastWestPanel.add(new Kotak(Color.decode(ColorMap.COLOR_ONE)));
        eastWestPanel.add(new Kotak(Color.decode(ColorMap.COLOR_TWO)));
        eastWestPanel.add(new Kotak(Color.decode(ColorMap.COLOR_THREE)));
        eastWestPanel.add(new Kotak(Color.decode(ColorMap.COLOR_FOUR)));
        eastWestPanel.add(new Kotak(Color.decode(ColorMap.COLOR_FIVE)));
        eastWestPanel.add(new Kotak(Color.decode(ColorMap.COLOR_SIX)));
        eastWestPanel.add(new JLabel("       "));
        eastWestPanel.add(new JLabel(""));
        
        
        eastCenterPanel.setLayout(new GridLayout(8, 1, 10, 10));
        JLabel kelasSatu = new JLabel("<22.346 jiwa");
        JLabel kelasDua = new JLabel("22.347 - 44.693 jiwa");
        JLabel kelasTiga = new JLabel("44.694 - 67.041 jiwa");
        JLabel kelasEmpat = new JLabel("67.042 - 89.389 jiwa");
        JLabel kelasLima = new JLabel("89.390 - 111.736 jiwa");
        JLabel kelasEnam = new JLabel(">111.737 jiwa");
        
        //eastCenterPanel.setBackground(Color.decode("#9baecd"));
        
        setFont(kelasSatu, "Abyssinica SIL", Font.PLAIN, 13);
        setFont(kelasDua, "Abyssinica SIL", Font.PLAIN, 13);
        setFont(kelasTiga, "Abyssinica SIL", Font.PLAIN, 13);
        setFont(kelasEmpat, "Abyssinica SIL", Font.PLAIN, 13);
        setFont(kelasLima, "Abyssinica SIL", Font.PLAIN, 13);
        setFont(kelasEnam, "Abyssinica SIL", Font.PLAIN, 13);
        
        eastCenterPanel.add(kelasSatu);
        eastCenterPanel.add(kelasDua);
        eastCenterPanel.add(kelasTiga);
        eastCenterPanel.add(kelasEmpat);
        eastCenterPanel.add(kelasLima);
        eastCenterPanel.add(kelasEnam);
        eastCenterPanel.add(new JLabel(""));
        eastCenterPanel.add(new JLabel(""));
        
        panel.add(eastWestPanel, BorderLayout.WEST);
        panel.add(eastCenterPanel, BorderLayout.CENTER);
        
        panel.setBorder(BorderFactory.createTitledBorder("Keterangan"));
        
        return panel;
        
    }

    
    public void setColorMap(double jumlahPenduduk, double[] latlon, PolygonLayer polygonLayer){
      
        polygonLayer.setMapColor(latlon, jumlahPenduduk);
        
    }
    public void setLatLon(){
        mapCoordinates= coordinates.getCoordinates();
        mapCoordinates2 = coordinates2.getCoordinates();
        mapCoordinates.replace(1, mapCoordinates.get(1),mapCoordinates2.get(17));
        mapCoordinates.replace(2, mapCoordinates.get(2),mapCoordinates2.get(18));
        mapCoordinates.replace(3, mapCoordinates.get(3),mapCoordinates2.get(19));
        mapCoordinates.replace(4, mapCoordinates.get(4),mapCoordinates2.get(1));
        mapCoordinates.replace(5, mapCoordinates.get(5),mapCoordinates2.get(2));
        mapCoordinates.replace(6, mapCoordinates.get(6), mapCoordinates2.get(24));
        mapCoordinates.replace(7, mapCoordinates.get(7), mapCoordinates2.get(3));
        mapCoordinates.replace(8, mapCoordinates.get(8), mapCoordinates2.get(4));
        mapCoordinates.replace(9, mapCoordinates2.get(5));
        mapCoordinates.replace(10, mapCoordinates2.get(22));
        mapCoordinates.replace(11, mapCoordinates2.get(25));
        mapCoordinates.replace(12, mapCoordinates2.get(23));
        mapCoordinates.replace(13, mapCoordinates2.get(6));
        mapCoordinates.replace(14, mapCoordinates2.get(7));
        mapCoordinates.replace(15, mapCoordinates2.get(8));
        mapCoordinates.replace(16, mapCoordinates2.get(21));
        mapCoordinates.replace(17, mapCoordinates2.get(20));
        mapCoordinates.replace(18, mapCoordinates2.get(9));
        mapCoordinates.replace(19, mapCoordinates2.get(10));
        mapCoordinates.replace(20, mapCoordinates2.get(11));
        mapCoordinates.replace(21, mapCoordinates2.get(27));
        mapCoordinates.replace(22, mapCoordinates2.get(26));
        mapCoordinates.replace(23, mapCoordinates2.get(12));
        mapCoordinates.replace(24, mapCoordinates2.get(13));
        mapCoordinates.replace(25, mapCoordinates2.get(29));
        mapCoordinates.replace(26, mapCoordinates2.get(14));
        mapCoordinates.replace(27, mapCoordinates2.get(28));
        mapCoordinates.replace(28, mapCoordinates2.get(15));
        mapCoordinates.replace(29, mapCoordinates2.get(16));
      
    }
    public void addLabelMap(PolygonLayer polygonLayer){
       
       for(int i=0; i < listKecamatan.size(); i++){
           double y = listKecamatan.get(i).getCoordinateY();
           double x = listKecamatan.get(i).getCoordinateX();
           String nama= listKecamatan.get(i).getNama();
           polygonLayer.addLabel(y, x, (i+1)+ ". "+nama);
       }
   }
    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            //UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
            PrediksiView mainPage = new PrediksiView();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void hitungSelisih(){
        if(!textFieldTahunAwal[0].getText().equals("") && !textFieldTahunPrediksi[0].getText().equals("")){
            
            for(int i=0; i < textFieldSelisih.length; i++){
                //String angkaPrediksi = textFieldTahunPrediksi[i].getText
                double prediksi = getNumberFormat(textFieldTahunPrediksi[i].getText());
                
                double awal = getNumberFormat(textFieldTahunAwal[i].getText());
                //System.out.println("prediksi: "+prediksi+"\nawal: "+awal);
                double selisih = prediksi - awal;
                totalSelisih+= selisih;
                textFieldSelisih[i].setText(getDecimalFormat(selisih)+" jiwa");
            }
            textTotalSelisih.setText(getDecimalFormat(totalSelisih)+" jiwa");
            totalSelisih=0.0;
        }
    }
    public String getDecimalFormat(double angka){
        String decimal = decimalFormat.format(angka);
        return decimal;
    }
    public double getNumberFormat(String string){
        String satu = string.replaceAll(",", "");
        //System.out.println(satu);
        double number = Double.parseDouble(satu.substring(0, satu.length()-5));
        //double number = 0.0;
        return number;
    }
    public JPanel makeButtonDetails(String command){
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(1, 2, 5, 5));
        //panel.setBorder(BorderFactory.createEtchedBorder());
        
        JButton buttonSearch = new JButton("Details");
        buttonSearch.addActionListener(this);
        setFont(buttonSearch, "Abyssinica SIL", Font.PLAIN, 13);
        buttonSearch.setActionCommand(command);
        
        panel.add(buttonSearch);
        panel.add(new JLabel(""));
        
        return panel;
        
    }
    public JPanel makeMapPanel(OverlayMapPanel satu, OverlayMapPanel dua){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 3, 10,30));
        p.add(satu);
        p.add(dua);
        p.add(makeEastPanel());
        p.setPreferredSize(new Dimension(1200, 450));
        return p;
    }
    public JPanel makeAttributePanel(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 7,10,10));
        p.add(atributKecamatan);
        p.add(atributTahunAwal);
        p.add(atributKepadatanAwal);
        p.add(atributPrediksi);
        p.add(atributKepadatanPrediksi);
        p.add(atributSelisih);
        p.add(new JLabel(""));
        return p;
    }
}
