/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgiai;

import sdu.mmmi.softwareengineering.osgicommon.bullet.Bullet;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.MovingPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

/**
 *
 * @author Mikkel Høyberg
 */
public class AIProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        aStar(gameData, world);

    }

    private void aStar(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            if (!entity.getIsPlayer()) {

                PositionPart positionPart = entity.getPart(PositionPart.class);
                MovingPart movingPart = entity.getPart(MovingPart.class);

            }

        }

    }

    private void BestPath(GameData gameData, World world) {

        Entity player;
        PositionPart playerPositionPart = null;

        for (Entity entity : world.getEntities()) {
            if (entity.getIsPlayer()) {
                player = entity;
                playerPositionPart = entity.getPart(PositionPart.class);
                break;
            }
        }
        int playerNodeX = (int) Math.floor(playerPositionPart.getX() / world.getGrid().getGrid().get(0).getWidth());
        int playerNodeY = (int) Math.floor(playerPositionPart.getY() / world.getGrid().getGrid().get(0).getHeight());

        for (Entity entity : world.getEntities()) {
            if (!entity.getIsPlayer() && !entity.getClass().equals(Bullet.class)) {
                PositionPart enmemyPositionPart = entity.getPart(PositionPart.class);
                int enemyNodeX = (int) Math.floor(enmemyPositionPart.getX() / world.getGrid().getGrid().get(0).getWidth());
                int enemyNodeY = (int) Math.floor(enmemyPositionPart.getY() / world.getGrid().getGrid().get(0).getHeight());
                
                
                
                
            }
        }

    }
}
