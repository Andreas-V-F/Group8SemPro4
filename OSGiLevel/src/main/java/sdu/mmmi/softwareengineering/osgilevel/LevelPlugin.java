/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgilevel;

import sdu.mmmi.softwareengineering.osgicommon.data.Door;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.Level;
import sdu.mmmi.softwareengineering.osgicommon.data.UnplayableArea;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;

/**
 *
 * @author Andreas Ibsen Cor
 */
public class LevelPlugin implements IGamePluginService {

    public LevelPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        Level level = createLevel();
        String id = world.addLevel(level);

        world.setCurrentLevel(id);

        Level level2 = createLevel();
        String id2 = world.addLevel(level2);

        Door door = createDoor(id2);
        level.addUnplayableArea(door);

        Door door2 = createDoor2(id);
        level2.addUnplayableArea(door2);

        addRandomStructures(level);

    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Level createLevel() {
        return new Level();
    }

    public Door createDoor(String levelID) {
        Door door = new Door(levelID);

        door.setShapeX(new float[]{600, 600, 650, 650});
        door.setShapeY(new float[]{600, 650, 650, 600});

        return door;
    }

    public Door createDoor2(String levelID) {
        Door door = new Door(levelID);
        door.setShapeX(new float[]{800, 800, 850, 850});
        door.setShapeY(new float[]{800, 850, 850, 800});

        return door;
    }

    public void addRandomStructures(Level level) {
        //P
        Wall wall = new Wall(200, 200, 210, 100);
        level.addUnplayableArea(wall);
        wall = new Wall(210, 200, 260, 190);
        level.addUnplayableArea(wall);
        wall = new Wall(250, 190, 260, 150);
        level.addUnplayableArea(wall);
        wall = new Wall(210, 160, 250, 150);
        level.addUnplayableArea(wall);

        //I
        wall = new Wall(300, 170, 315, 100);
        level.addUnplayableArea(wall);
        wall = new Wall(300, 200, 315, 180);
        level.addUnplayableArea(wall);

        //K
        wall = new Wall(350, 200, 360, 100);
        level.addUnplayableArea(wall);
        wall = new Wall(360, 160, 410, 150);
        level.addUnplayableArea(wall);
        wall = new Wall(400, 160, 410, 200);
        level.addUnplayableArea(wall);
        wall = new Wall(385, 150, 395, 100);
        level.addUnplayableArea(wall);
    }

}
