/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigkarawang.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ihsan
 */
public class Kecamatan {
    private double id;
    private String nama;
    private Map<String, List<Double>> jumlahPenduduk;
    private Map<Integer, Integer> lpp;
    private Map<Integer,Integer> kepadatanPenduduk;
    private double coordinateX;
    private double coordinateY;
    private double luas;
    private double penduduk2018;
    private String ibukota;
    private double tinggi;
    private double jumlahDesa;
    private double rasioJK;
    
    public void setId(double id){
        this.id= id;
    }
    public void setNama(String nama){
        this.nama= nama;
    }
    public void setJumlahPenduduk(Map<String, List<Double>> jumlahPenduduk){
        this.jumlahPenduduk = jumlahPenduduk;
    }
    public void setLPP(Map<Integer, Integer> lpp){
        this.lpp = lpp;
    }
    public void setKepadatanPenduduk(Map<Integer, Integer> kepadatanPenduduk){
        this.kepadatanPenduduk=kepadatanPenduduk;
    }
    public double getId(){
        return this.id;
    }
    public String getNama(){
        return this.nama;
    }
    public Map<String, List<Double>> getJumlahPenduduk(){
        return this.jumlahPenduduk;
    }
    public Map<Integer, Integer> getLPP(){
        return this.lpp;
    }
    public Map<Integer, Integer> getKepadatanPenduduk(){
        return this.lpp;
    }
    public void setLuas(double l){
        luas = l;
    }
    public double getLuas(){
        return luas;
    }
    public void setCoordinateX(double x){
        coordinateX = x;
    }
    public void setCoordinateY(double y){
        coordinateY = y;
    }
    public double getCoordinateX(){
        return coordinateX;
    }
    public double getCoordinateY(){
        return coordinateY;
    }
    public void setPenduduk2018(double penduduk){
        penduduk2018 = penduduk;
    }
    public double getPenduduk2018(){
        return penduduk2018;
    }
    public void setIbukota(String ibukota){
        this.ibukota = ibukota;
    }
    public String getIbukota(){
        return ibukota;
    }
    public void setTinggi(double tinggi){
        this.tinggi = tinggi;
    }
    public double getTinggi(){
        return tinggi;
    }
    public void setJumlahDesa(double jDesa){
        jumlahDesa= jDesa;
    }
    public double getJumlahDesa(){
        return jumlahDesa;
    }
    public void setRasioJK(double rasio){
        rasioJK = rasio;
    }
    public double getRasioJK(){
        return rasioJK;
    }
    
}
