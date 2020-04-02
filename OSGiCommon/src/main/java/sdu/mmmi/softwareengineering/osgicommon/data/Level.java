/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicommon.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import sdu.mmmi.softwareengineering.osgicommon.data.UnplayableArea;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Andreas Ibsen Cor
 */
public class Level implements Serializable{

    private final UUID ID = UUID.randomUUID();

    private final Map<String, UnplayableArea> unplayableAreaMap = new ConcurrentHashMap<>();
    
    public Level(){
                
        //left wall
        UnplayableArea unplayableArea = createUnplayableArea(new float[]{0, 0, 20, 20}, new float[]{0, 1000, 1000, 0});
        addUnplayableArea(unplayableArea);
        
        //right wall
        unplayableArea = createUnplayableArea(new float[]{1180, 1180, 1200, 1200}, new float[]{0, 1000, 1000, 0});
        addUnplayableArea(unplayableArea);
        
        //upper wall
        unplayableArea = createUnplayableArea(new float[]{0, 0, 1200, 1200}, new float[]{980, 1000, 1000, 980});
        addUnplayableArea(unplayableArea);
        
        //lower wall
        unplayableArea = createUnplayableArea(new float[]{0, 0, 1200, 1200}, new float[]{0, 20, 20, 0});
        addUnplayableArea(unplayableArea);
    }
    
    public String addUnplayableArea(UnplayableArea unplayableArea){
        unplayableAreaMap.put(unplayableArea.getID(), unplayableArea);
        return unplayableArea.getID();
    }
    
    public void removeUnplayableArea(UnplayableArea unplayableArea){
        unplayableAreaMap.remove(unplayableArea);
    }
    
    public void removeUnplayableArea(String unplayableAreaID){
        unplayableAreaMap.remove(unplayableAreaID);
    }
    
    public UnplayableArea getUnplayableArea(String unplayableAreaID){
        return unplayableAreaMap.get(unplayableAreaID);
    }
    
    public List<UnplayableArea> getUnplayableAreas() {
        List<UnplayableArea> unplayableAreaList = new ArrayList<>();
        for(UnplayableArea un : unplayableAreaMap.values()){
            unplayableAreaList.add(un);
        }
        return unplayableAreaList;
    }

    public String getID() {
        return ID.toString();
    }
    
    private UnplayableArea createUnplayableArea(float[] shapex, float[] shapey) {
        UnplayableArea unplayableArea = new UnplayableArea();
        
        unplayableArea.setShapeX(shapex);
        unplayableArea.setShapeY(shapey);
        
        return unplayableArea;
    }
    

}
