/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigkarawang.trend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sigkarawang.lab.KecamatanLab;
import sigkarawang.model.Kecamatan;

/**
 *
 * @author ihsan
 */
public class TrendAnalysis {
    private double a;
    private double b;
    private double sigmaX = 0;
    private double sigmaY = 0;
    private double sigmaXKuadrat = 0;
    private double sigmaXY = 0;
    private double[] x;
    private List<Double> jumlahPenduduk;
    public TrendAnalysis(){
        
    }
    public TrendAnalysis(List<Double> jp){
        jumlahPenduduk = jp;
        //System.out.println("Size of jumlahPenduduk: "+jumlahPenduduk.size());
        for(int i=0; i < jumlahPenduduk.size(); i++){
            sigmaY += jumlahPenduduk.get(i);
          //  System.out.print("Jumlah penduduk ke-"+i+": "+jumlahPenduduk.get(i)+" ");
            
        }
        
        
        x = new double[jumlahPenduduk.size()];
        int nilaiTengah = 0;
        
        for(int i=((jumlahPenduduk.size()/2)); i >= 0; i--){
            x[i] = nilaiTengah;
            nilaiTengah-=1;
        } 
        nilaiTengah =0;
        for(int i=((jumlahPenduduk.size()/2)+1); i < jumlahPenduduk.size(); i++){
            nilaiTengah+=1;
            x[i] = nilaiTengah;            
        }
        
        for(int i=0; i < x.length; i++){
            //System.out.print(x[i] + " ");
            sigmaX += x[i];
        }
        for(int i=0; i < x.length; i++){
            sigmaXKuadrat+= x[i]*x[i];
        }
        //System.out.print("\nxi kali jumPenduduk: ");
        for(int i=0; i < x.length; i++){
            sigmaXY += x[i]*jumlahPenduduk.get(jumlahPenduduk.size()-(i+1));
            //System.out.print(x[i] * jumlahPenduduk.get(i) +" ");
        }
       // System.out.println();
    }
    public double getA(){
        a = sigmaY / jumlahPenduduk.size();
        return a;
    }
    public double getB(){
        b = sigmaXY / sigmaXKuadrat;
        return b;
    }
    public double getMaximumX(){
        for(int i=x.length - 1; i >= 1; i--){
            for(int j= 0; j <= i; j++){
                if(x[j] > x[i]){
                    double temp= x[i];
                    x[i] = x[j];
                    x[j] = temp;
                }
            }
        }
        return x[x.length-1];
    }
   
}
