/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigkarawang.lab;

import com.bbn.openmap.dataAccess.shape.DbfFile;
import com.bbn.openmap.io.BinaryBufferedFile;
import com.bbn.openmap.layer.shape.BufferedShapeLayer;
import com.bbn.openmap.layer.shape.ShapeLayer;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import sigkarawang.model.Kecamatan;

public class KecamatanLab{
    private ShapeLayer shapeLayer;
    
    public KecamatanLab(){
        
    }
    public KecamatanLab(ShapeLayer shpL){
        shapeLayer = shpL;
        
    }
    public static List<Kecamatan> getListKecamatan(){
        ArrayList<Kecamatan> listKecamatan = new ArrayList<>();
        //String kecamatan = (String)shapeLayer.getAttribute("Kecamatan");
        //System.out.println("Kecamatan: "+kecamatan);
        DbfFile file = null;
        try{
           BinaryBufferedFile bbf = new BinaryBufferedFile("sigkarawang/file/karawang.dbf");
           file = new DbfFile(bbf);
           //System.out.println("Attribute: "+file.getColumnName(0));
           //System.out.println("Record length: "+file.getRecordLength());
           //System.out.println(file.getColumnIndexForName("xcoord"));
           //System.out.println(file.getColumnIndexForName("ycoord"));
           
          /* for(int i=0; i < file.getColumnCount(); i++){
               System.out.println("Column ke "+i+ " "+ file.getColumnName(i));
           }*/
           for(int i=0; i < file.getRecordLength(); i++){
               //System.out.println(i + ": "+file.getRecordData(i).);
               Kecamatan kecamatan = new Kecamatan();
               kecamatan.setId((Double)file.getRecordData(i).get(0));
               kecamatan.setNama((String)file.getRecordData(i).get(1));
               kecamatan.setCoordinateX((Double)file.getRecordData(i).get(2));
               kecamatan.setCoordinateY((Double) file.getRecordData(i).get(3));
               //Integer[] tahun = {2018, 2017, 2016, 2015, 2014, 2013, 2012, 2011, 2010};
               Map<String, List<Double>> jumlahPenduduk =new HashMap<String, List<Double>>();
               List<Double> listTahun = new ArrayList<>();
               for(int j=4; j < file.getColumnCount()-5; j++){
                   //System.out.println(file.getRecordData(i).get(1)+ ": "+file.getColumnName(j) +": "+file.getRecordData(i).get(j));
                   listTahun.add((Double)file.getRecordData(i).get(j));
               }
               jumlahPenduduk.put((String)file.getRecordData(i).get(1), listTahun);
               //System.out.println("key: "+file.getRecordData(i).get(2));
               kecamatan.setJumlahPenduduk(jumlahPenduduk);
               kecamatan.setLuas((Double) file.getRecordData(i).get(13));
               kecamatan.setPenduduk2018((Double) file.getRecordData(i).get(4));
               kecamatan.setIbukota((String)file.getRecordData(i).get(14));
               kecamatan.setTinggi((Double)file.getRecordData(i).get(15));
               kecamatan.setRasioJK((Double)file.getRecordData(i).get(16));
               kecamatan.setJumlahDesa((Double)file.getRecordData(i).get(17));
               //System.out.println("coulmn ke"+i+ " "+file.getColumnName(i));
               listKecamatan.add(kecamatan);
           }
           file.close();
       }catch(EOFException eofE){
           //System.out.println("Fine aja qoq");
       }catch(Exception e){
           e.printStackTrace();
       }
        Collections.sort(listKecamatan, new SortbyId());
        return listKecamatan;
    }
    /*public static void main(String[] args){
        
        KecamatanLab lab = new KecamatanLab();
        lab.getListKecamatan();
        
    }*/
     
}
class SortbyId implements Comparator<Kecamatan> { 
    // Used for sorting in ascending order of 
    // roll number 
        public int compare(Kecamatan a, Kecamatan b) { 
            int satu = (int) a.getId();
            int dua = (int) b.getId();
            return satu - dua; 
        }    
 }
