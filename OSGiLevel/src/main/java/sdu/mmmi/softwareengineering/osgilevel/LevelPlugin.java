/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgilevel;

import com.badlogic.gdx.assets.AssetManager;
import java.util.ArrayList;
import java.util.Random;
import sdu.mmmi.softwareengineering.osgicommon.data.Door;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.Level;
import sdu.mmmi.softwareengineering.osgicommon.data.UnplayableArea;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.managers.AssetMan;
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
        Level level = createLevel(gameData);
        String id = world.addLevel(level);
        world.setCurrentLevel(id);
//        createDoor(level, id, "LEFT", world, gameData);
//        createDoor(level, id, "UP", world, gameData);
//        createDoor(level, id, "DOWN", world, gameData);
//        createDoor(level, id, "RIGHT", world, gameData);
        fillMap(8, world, gameData);
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Level createLevel(GameData gameData) {
        Level level = new Level();
        addRandomStructures(level, 1200, 1000);
        System.out.println("height    " + gameData.getDisplayHeight());
        return level;
    }

    public Level createDoor(Level level, String levelID, String rotation, World world, GameData gameData) {
        Level level2 = createLevel(gameData);
        String level2ID = world.addLevel(level2);

        level.addUnplayableArea(new Door(level2ID, rotation));

        switch (rotation) {
            case "LEFT":
                level2.addUnplayableArea(new Door(levelID, "RIGHT"));
                break;
            case "RIGHT":
                level2.addUnplayableArea(new Door(levelID, "LEFT"));
                break;
            case "UP":
                level2.addUnplayableArea(new Door(levelID, "DOWN"));
                break;
            case "DOWN":
                level2.addUnplayableArea(new Door(levelID, "UP"));
        }

        return level2;
    }

    public void addRandomStructures(Level level, float width, float height) {
        int thickness = 20;

        //left wall
        Wall wall = new Wall(0, height, thickness, 0);
        level.addUnplayableArea(wall);

        //right wall
        wall = new Wall(width - thickness, height, width, 0);
        level.addUnplayableArea(wall);

        //upper wall
        wall = new Wall(0, height, width, height - thickness);
        level.addUnplayableArea(wall);

        //lower wall
        wall = new Wall(0, thickness, width, 0);
        level.addUnplayableArea(wall);

        Structures structures = new Structures(width, height);
        for (ArrayList<Wall> w : structures.getStructureList()) {
            double num = Math.random();
            if (num < 0.5) {
                for (Wall walls : w) {
                    level.addUnplayableArea(walls);
                }
            }
        }
    }

    public boolean doorIsDuplicate(Level level, String rotation) {
        for (UnplayableArea un : level.getUnplayableAreas()) {
            if (un.getClass().equals(Door.class)) {
                Door d = (Door) un;
                if (d.getRotation().equals(rotation)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void fillMap(int num, World world, GameData gameData) {
        for (int i = 0; i < num; i++) {
            Random r = new Random();
            int max = world.getLevels().size();
            int rand = r.nextInt(max);
            Level level = world.getLevels().get(rand);
            rand = r.nextInt(4);
            String rota = "";
            switch (rand) {
                case 0:
                    rota = "UP";
                    break;
                case 1:
                    rota = "DOWN";
                    break;
                case 2:
                    rota = "LEFT";
                    break;
                case 3:
                    rota = "RIGHT";
                    break;
            }

            if (!doorIsDuplicate(level, rota)) {
                createDoor(level, level.getID(), rota, world, gameData);
            }

        }
    }
}
