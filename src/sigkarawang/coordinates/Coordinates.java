/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigkarawang.coordinates;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;


/**
 *
 * @author ihsan
 */
public class Coordinates {
    String coordinates4;
    String[] coordinates5;
    double[] coordinates7;
    private Map<Integer, List<Double>> mapCoordinates = new HashMap<>();
    private double[] latlon;
    private int index =0;
    public Coordinates(){
    
    Map<String, Object> communities = new HashMap<>();
    try {
        File f = new File("karawang.shp");
        ShapefileDataStore shapefile = new ShapefileDataStore(f.toURI().toURL());
        
        SimpleFeatureIterator features = shapefile.getFeatureSource().getFeatures().features();
        SimpleFeature shp;
        
        while (features.hasNext()) {
            shp = features.next();
            
            String coordinates = shp.getDefaultGeometry().toString();
            String coordinates2 = coordinates.substring(16, coordinates.length()-3);
            String coordinates3 = coordinates2.replaceAll(",", "");
            coordinates5 = coordinates3.split(" ");
            coordinates7 = Arrays.stream(coordinates5)
                        .mapToDouble(Double::parseDouble)
                        .toArray();
            List<Double> listCoordinates = new ArrayList<>();
            for(int i=0; i < coordinates7.length;i++){
                listCoordinates.add(coordinates7[i]);
                //System.out.print(coordinates7[i] + " ");
            }
            mapCoordinates.put(index, listCoordinates);
            //System.out.println("index: "+ index);
            index++;
            
        }
        features.close();
        shapefile.dispose();
    } catch (IOException e) {
        System.out.println("error nyet");
        e.printStackTrace();
    }
    }
    public Map<Integer, List<Double>>  getCoordinates(){
        return mapCoordinates;
        
    }
    public double[] getLatLon(){
        for(Map.Entry<Integer, List<Double>> entry: mapCoordinates.entrySet()){
            List<Double> listCoordinates = entry.getValue();
            System.out.println("list size: "+listCoordinates.size());
            latlon = new double[listCoordinates.size()];
            for(int i=0; i < listCoordinates.size(); i++){
                if((i%2) ==0){
                    latlon[i] = listCoordinates.get(i+1);
                }else{
                    latlon[i] = listCoordinates.get(i-1);
                }
            }
            
        }
        return latlon;
    }
    
}


