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
    }

}
