/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgiplayer;

import sdu.mmmi.softwareengineering.osgicommon.data.*;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.*;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

/**
 *
 * @author menta
 */
public class PlayerProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
            LifePart lifePart = player.getPart(LifePart.class);
            ShootingPart shootingPart = player.getPart(ShootingPart.class);

            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.A));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.D));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.W));
            movingPart.setDown(gameData.getKeys().isDown(GameKeys.S));

            if (gameData.getKeys().isDown(GameKeys.UP)) {
                shootingPart.setDirection("UP");
                shootingPart.setIsShooting(true);
            } else if (gameData.getKeys().isDown(GameKeys.DOWN)) {
                shootingPart.setDirection("DOWN");
                shootingPart.setIsShooting(true);
            } else if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                shootingPart.setDirection("LEFT");
                shootingPart.setIsShooting(true);
            } else if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                shootingPart.setDirection("RIGHT");
                shootingPart.setIsShooting(true);
            }

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);
            lifePart.process(gameData, player);

            updateShape(player);

        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

//    //TODO: Dependency injection via Declarative Services
//    public void setBulletService(BulletSPI bulletService) {
//        this.bulletService = bulletService;
//    }
//
//    public void removeBulletService() {
//        this.bulletService = null;
//    }
}
