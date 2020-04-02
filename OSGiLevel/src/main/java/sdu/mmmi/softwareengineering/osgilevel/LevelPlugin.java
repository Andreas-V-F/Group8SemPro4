/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgilevel;

import sdu.mmmi.softwareengineering.osgicommon.data.Door;
import java.awt.Shape;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.Level;
import sdu.mmmi.softwareengineering.osgicommon.data.UnplayableArea;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;

/**
 *
 * @author Andreas Ibsen Cor
 */
public class LevelPlugin implements IGamePluginService{
    
    public LevelPlugin(){
    }

    @Override
    public void start(GameData gameData, World world) {
        Level level = createLevel();
        String id = world.addLevel(level);
        world.setCurrentLevel(id);
        System.out.println(world.getCurrentLevel().getID());
        
        Level level2 = createLevel();
        String id2 = world.addLevel(level2);
        
        Door door = createDoor(id2);
        level.addUnplayableArea(door);
        
        Door door2 = createDoor2(id);
        level2.addUnplayableArea(door2);
        
        
        
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Level createLevel(){
        return new Level();
    }
    
    public Door createDoor(String levelID){
        Door door = new Door(levelID);
        
        door.setShapeX(new float[]{600, 600, 650, 650});
        door.setShapeY(new float[]{600, 650, 650, 600});
        
        return door;
    }
    
     public Door createDoor2(String levelID){
        Door door = new Door(levelID);
        
        door.setShapeX(new float[]{800, 800, 850, 850});
        door.setShapeY(new float[]{800, 850, 850, 800});
        
        return door;
    }
    
}
