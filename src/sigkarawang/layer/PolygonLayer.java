/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigkarawang.layer;

import com.bbn.openmap.layer.OMGraphicHandlerLayer;
import com.bbn.openmap.omGraphics.OMGraphic;
import com.bbn.openmap.omGraphics.OMGraphicConstants;
import com.bbn.openmap.omGraphics.OMGraphicList;
import com.bbn.openmap.omGraphics.OMPoint;
import com.bbn.openmap.omGraphics.OMPoly;
import com.bbn.openmap.omGraphics.OMTextLabeler;
import java.awt.Color;
import java.awt.Font;
import javax.jws.soap.SOAPBinding.Use;
import sigkarawang.color.ColorMap;

/**
 *
 * @author ihsan
 */
public class PolygonLayer extends OMGraphicHandlerLayer{
    private OMGraphicList recordPoints = new OMGraphicList();
    
    private int recordPointGeometry = 1;
    
    public PolygonLayer(){
        setName("Polygon Layer");
    }
    
    @Override 
    public synchronized OMGraphicList prepare(){
        OMGraphicList ret = new OMGraphicList(getRecordPoints());
        ret.generate(getProjection());
        
        return ret;
    }
    public OMGraphicList getRecordPoints(){
        return recordPoints;
    }
    public void setMapColor(double[] latlon, double jumlahPenduduk){
        
        
        OMPoly point= new OMPoly(latlon, OMGraphic.DECIMAL_DEGREES, OMGraphic.GRAPHICTYPE_POLY);
        
        
        //point.setLocation(y, OMGraphicConstants.GRAPHICTYPE_POLY);
        //OMPoly poly= new OMPoly(y, x, 5);
        //poly.se
        //OMPoint point = new OMPoint(y, x, width);
        //getRecordPoints().clear();
        if(jumlahPenduduk <= 22346.0){
            point.setFillPaint(Color.decode(ColorMap.COLOR_ONE));
        }
        if(jumlahPenduduk >= 22347.0 && jumlahPenduduk <= 44693.0){
            point.setFillPaint(Color.decode(ColorMap.COLOR_TWO));
        }
        if(jumlahPenduduk >= 44694.0 && jumlahPenduduk <= 67041.0){
            point.setFillPaint(Color.decode(ColorMap.COLOR_THREE));
        }
        if(jumlahPenduduk >= 67042.0 && jumlahPenduduk <= 89389.0){
            point.setFillPaint(Color.decode(ColorMap.COLOR_FOUR));
        }
        if(jumlahPenduduk >= 89390.0 && jumlahPenduduk <= 111736.0){
            point.setFillPaint(Color.decode(ColorMap.COLOR_FIVE));
        }
        if(jumlahPenduduk >=111737){
            point.setFillPaint(Color.decode(ColorMap.COLOR_SIX));
        }
        
        //point.putAttribute(OMGraphicConstants.LABEL, new OMTextLabeler("Rectangle Label"));
        
        
        getRecordPoints().add(point);
       // getRecordPoints().sort();
        doPrepare();
                
    }
    
    public void addLabel(double y, double x, String nama){
        OMPoint point =new OMPoint(y, x, 1);
        OMTextLabeler label = new OMTextLabeler(nama);
        
        label.setFont(new Font("Calibri", Font.PLAIN, 11));
        
        point.setFillPaint(Color.red);
        point.putAttribute(OMGraphicConstants.LABEL, label);
        
        getRecordPoints().add(point);
        doPrepare();
        
    }
    public void clear(){
        getRecordPoints().clear();
        doPrepare();
    }
    public void sort(){
        getRecordPoints().sort();
        doPrepare();
        
    }

    
}
