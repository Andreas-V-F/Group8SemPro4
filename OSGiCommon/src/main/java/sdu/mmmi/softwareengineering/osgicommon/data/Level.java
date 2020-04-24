/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicommon.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    
    public Level(){
       
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
    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }

    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

}
