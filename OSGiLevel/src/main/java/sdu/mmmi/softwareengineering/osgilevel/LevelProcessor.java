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
import sdu.mmmi.softwareengineering.osgicommon.managers.AssetMan;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

/**
 *
 * @author Andreas Ibsen Cor
 */
public class LevelProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (UnplayableArea un : world.getCurrentLevel().getUnplayableAreas()) {
            if (un.getClass().equals(Door.class)) {
                Door door = (Door) un;
                if (door.getIsActivated()) {
                    door.setIsActivated(false);
                    world.setCurrentLevel(door.getLevelID());
                    break;
                }
            }
        }
        setTextures(world, gameData);
    }

    public void setTextures(World world, GameData gameData) {
        for (Level level : world.getLevels()) {
            for (UnplayableArea un : level.getUnplayableAreas()) {
                if (un.getClass().equals(Door.class)) {
                    Door door = (Door) un;
                    int doorSize = door.getSize();
                    if (door.getRotation().equals("UP")) {
                        door.setShapeX(new float[]{gameData.getDisplayWidth() / 2 - doorSize / 2, gameData.getDisplayWidth() / 2 - doorSize / 2, gameData.getDisplayWidth() / 2 + doorSize / 2, gameData.getDisplayWidth() / 2 + doorSize / 2});
                        door.setShapeY(new float[]{gameData.getDisplayHeight() - doorSize, gameData.getDisplayHeight(), gameData.getDisplayHeight(), gameData.getDisplayHeight() - doorSize});
                        door.setTextureRegion(AssetMan.manager.get(AssetMan.doorUp));
                    } else if (door.getRotation().equals("DOWN")) {
                        door.setShapeX(new float[]{gameData.getDisplayWidth() / 2 - doorSize / 2, gameData.getDisplayWidth() / 2 - doorSize / 2, gameData.getDisplayWidth() / 2 + doorSize / 2, gameData.getDisplayWidth() / 2 + doorSize / 2});
                        door.setShapeY(new float[]{0, doorSize, doorSize, 0});
                        door.setTextureRegion(AssetMan.manager.get(AssetMan.doorDown));
                    } else if (door.getRotation().equals("LEFT")) {
                        door.setShapeX(new float[]{0, 0, doorSize, doorSize});
                        door.setShapeY(new float[]{gameData.getDisplayHeight() / 2 - doorSize / 2, gameData.getDisplayHeight() / 2 + doorSize / 2, gameData.getDisplayHeight() / 2 + doorSize / 2, gameData.getDisplayHeight() / 2 - doorSize / 2});
                        door.setTextureRegion(AssetMan.manager.get(AssetMan.doorLeft));
                    } else {
                        door.setShapeX(new float[]{gameData.getDisplayWidth() - doorSize, gameData.getDisplayWidth() - doorSize, gameData.getDisplayWidth(), gameData.getDisplayWidth()});
                        door.setShapeY(new float[]{gameData.getDisplayHeight() / 2 - doorSize / 2, gameData.getDisplayHeight() / 2 + doorSize / 2, gameData.getDisplayHeight() / 2 + doorSize / 2, gameData.getDisplayHeight() / 2 - doorSize / 2});
                        door.setTextureRegion(AssetMan.manager.get(AssetMan.doorRight));
                    }
                }
            }
        }
    }

}
