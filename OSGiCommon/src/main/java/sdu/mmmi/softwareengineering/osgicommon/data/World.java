package sdu.mmmi.softwareengineering.osgicommon.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import sdu.mmmi.softwareengineering.osgicommon.bullet.Bullet;

/**
 *
 * @author jcs
 */
public class World {

    private final Map<String, Level> levelMap = new ConcurrentHashMap<>();
    private String currentLevelID;
    private Level tempLevel = new Level();
    private int numberOfRooms = 8;

    public World() {
        addLevel(tempLevel);
    }

    public String addEntity(Entity entity) {
        return tempLevel.addEntity(entity);
    }

    public void removeEntity(String entityID) {
        tempLevel.removeEntity(entityID);
    }

    public void removeEntity(Entity entity) {
        tempLevel.removeEntity(entity);
    }

    public Collection<Entity> getEntities() {
        return tempLevel.getEntities();
    }

    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : tempLevel.getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    public Entity getEntity(String ID) {
        return tempLevel.getEntity(ID);
    }

    public String addLevel(Level level) {
        levelMap.put(level.getID(), level);
        return level.getID();
    }

    public void removeLevel(Level level) {
        levelMap.remove(level);
    }

    public void removeLevel(String levelID) {
        levelMap.remove(levelID);
    }

    public Level getLevel(String levelID) {
        return levelMap.get(levelID);
    }

    public Level getCurrentLevel() {
        return tempLevel;
    }

    public void setCurrentLevel(String ID) {
        Entity player = null;
        for (Entity e : getEntities()) {
            if (e.getClass().equals(Bullet.class)) {
                removeEntity(e);
            }

            if (e.getIsPlayer()) {
                player = e;
            }
        }
    
    tempLevel  = levelMap.get(ID);

    tempLevel.addEntity (player);

}

public List<Level> getLevels() {
        List<Level> levelList = new ArrayList<>();
        for (Level l : levelMap.values()) {
            levelList.add(l);
        }
        return levelList;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

}
