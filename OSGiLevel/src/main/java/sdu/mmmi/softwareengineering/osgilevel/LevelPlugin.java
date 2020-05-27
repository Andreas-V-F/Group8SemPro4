/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgilevel;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import sdu.mmmi.softwareengineering.osgicommon.data.Door;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.Index;
import sdu.mmmi.softwareengineering.osgicommon.data.Level;
import sdu.mmmi.softwareengineering.osgicommon.data.Node;
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
        fillMap(world.getNumberOfRooms(), world, gameData);
        createMissingDoors(world, gameData);
        world.fillLevelGrids();
        addRandomStructures(world, gameData.getDisplayWidth(), gameData.getDisplayHeight());
        setWalkableNodes(world);
    }

    @Override
    public void stop(GameData gameData, World world) {
        Level l = new Level(0, 0);
        world.addLevel(l);
        world.setCurrentLevel(l.getID());
    }

    public Level createLevel(GameData gameData, World world) {
        Level level = new Level(0, 0);
        return level;
    }

    public Level createDoor(Level level, Level level2, String rotation, World world, GameData gameData) {
        if (level2 == null) {
            level2 = createLevel(gameData, world);
            world.addLevel(level2);
        }

        level.addUnplayableArea(new Door(level2.getID(), rotation));

        switch (rotation) {
            case "LEFT":
                level2.addUnplayableArea(new Door(level.getID(), "RIGHT"));
                level2.setX(level.getX() - 1);
                level2.setY(level.getY());
                break;
            case "RIGHT":
                level2.addUnplayableArea(new Door(level.getID(), "LEFT"));
                level2.setX(level.getX() + 1);
                level2.setY(level.getY());
                break;
            case "UP":
                level2.addUnplayableArea(new Door(level.getID(), "DOWN"));
                level2.setX(level.getX());
                level2.setY(level.getY() + 1);
                break;
            case "DOWN":
                level2.setX(level.getX());
                level2.setY(level.getY() - 1);
                level2.addUnplayableArea(new Door(level.getID(), "UP"));
        }

        return level2;
    }

    public void addRandomStructures(World world, float width, float height) {
        for (Level l : world.getLevels()) {
            Node n = new Node();
            HashMap<Index, Node> hash = l.getGrid();

            //left wall            
            Wall wall = new Wall(hash.get(new Index(0, 0)), hash.get(new Index(0, (int) height / n.getHeight() - 1)));
            l.addUnplayableArea(wall);

            //right wall
            wall = new Wall(hash.get(new Index((int) width / n.getWidth() - 1, (int) height / n.getHeight() - 1)), hash.get(new Index((int) width / n.getWidth() - 1, 0)));
            l.addUnplayableArea(wall);

            //upper wall
            wall = new Wall(hash.get(new Index(0, (int) height / n.getHeight() - 1)), hash.get(new Index((int) width / n.getWidth() - 1, (int) height / n.getHeight() - 1)));
            l.addUnplayableArea(wall);

            //lower wall
            wall = new Wall(hash.get(new Index(0, 0)), hash.get(new Index((int) width / n.getWidth() - 1, 0)));
            l.addUnplayableArea(wall);

            //random wall
            wall = new Wall(hash.get(new Index(30, 30)), hash.get(new Index(20, 30)));
            l.addUnplayableArea(wall);

            //random wall2
            wall = new Wall(hash.get(new Index(30, 30)), hash.get(new Index(30, 20)));
            l.addUnplayableArea(wall);

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

    public boolean roomIsDuplicate(Level level, String rotation, World world, GameData gameData) {
        int x = level.getX();
        int y = level.getY();
        switch (rotation) {
            case "UP":
                y += 1;
                break;
            case "DOWN":
                y -= 1;
                break;
            case "LEFT":
                x -= 1;
                break;
            case "RIGHT":
                x += 1;
                break;
        }
        for (Level l : world.getLevels()) {
            if (l.getX() == x && l.getY() == y) {
                return true;
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

            if (!doorIsDuplicate(level, rota) && !roomIsDuplicate(level, rota, world, gameData)) {
                createDoor(level, null, rota, world, gameData);
            }

        }
    }

    public void createMissingDoors(World world, GameData gameData) {
        String rota = "";
        for (Level l : world.getLevels()) {
            for (Level x : world.getLevels()) {
                if (l.getID().equals(x.getID())) {
                    continue;
                }
                if (l.getX() + 1 == x.getX() && l.getY() == x.getY()) {
                    rota = "RIGHT";
                    if (!doorIsDuplicate(l, rota)) {
                        createDoor(l, x, rota, world, gameData);
                    }
                } else if (l.getX() - 1 == x.getX() && l.getY() == x.getY()) {
                    rota = "LEFT";
                    if (!doorIsDuplicate(l, rota)) {
                        createDoor(l, x, rota, world, gameData);
                    }
                } else if (l.getX() == x.getX() && l.getY() + 1 == x.getY()) {
                    rota = "UP";
                    if (!doorIsDuplicate(l, rota)) {
                        createDoor(l, x, rota, world, gameData);
                    }
                } else if (l.getX() == x.getX() && l.getY() - 1 == x.getY()) {
                    rota = "DOWN";
                    if (!doorIsDuplicate(l, rota)) {
                        createDoor(l, x, rota, world, gameData);
                    }
                }
            }
        }
    }

    private void setWalkableNodes(World world) {
        for (Level level : world.getLevels()) {
            for (Map.Entry<Index, Node> entry : world.getGrid().entrySet()) {

                float nodeMinX = entry.getValue().getX() * entry.getValue().getWidth();
                float nodeMinY = entry.getValue().getY() * entry.getValue().getHeight();

                float nodeMaxX = entry.getValue().getX() * entry.getValue().getWidth() + entry.getValue().getWidth();
                float nodeMaxY = entry.getValue().getY() * entry.getValue().getHeight() + entry.getValue().getHeight();

                int entityWidthandHeight = 32;

                for (UnplayableArea un : level.getUnplayableAreas()) {
                    if ((nodeMinX >= un.getShapeX()[0] - entityWidthandHeight && nodeMaxX <= un.getShapeX()[2] + entityWidthandHeight) && (nodeMinY >= un.getShapeY()[0] - entityWidthandHeight && nodeMaxY <= un.getShapeY()[2] + entityWidthandHeight)) {
                        entry.getValue().setWalkable(false);
                    }
                }
            }
        }

    }
}
