package sdu.mmmi.softwareengineering.osgicommon.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author jcs
 */
public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    private final Map<String, Level> levelMap = new ConcurrentHashMap<>();
    private String currentLevelID;
    private Level tempLevel = new Level();
    
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
    
    public String addLevel(Level level){
        levelMap.put(level.getID(), level);
        return level.getID();
    }
    
    public void removeLevel(Level level){
        levelMap.remove(level);
    }
    
    public void removeLevel(String levelID){
        levelMap.remove(levelID);
    }
    
    public Level getLevel(String levelID){
        return levelMap.get(levelID);
    }
    
    public Level getCurrentLevel(){
        return tempLevel;
    }
    
    public void setCurrentLevel(String ID){
        tempLevel = levelMap.get(ID);
    }
    
    public List<Level> getLevels() {
        List<Level> levelList = new ArrayList<>();
        for(Level l : levelMap.values()){
            levelList.add(l);
        }
        return levelList;
    }

}
