/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgiplayer;

import sdu.mmmi.softwareengineering.osgicommon.data.*;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.*;
import sdu.mmmi.softwareengineering.osgicommon.managers.AssetMan;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

/**
 *
 * @author menta
 */
public class PlayerProcessor implements IEntityProcessingService {
    
    private long time;
    private long delay = 100;
    
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
            
            if(System.currentTimeMillis() - time > delay){
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
                time = System.currentTimeMillis();
            }
            

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);
            

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

        shapex[0] = (float) (x - 15.5);
        shapey[0] = (float) (y - 15.5);

        shapex[1] = (float) (x - 15.5);
        shapey[1] = (float) (y + 15.5);

        shapex[2] = (float) (x + 15.5);
        shapey[2] = (float) (y + 15.5);

        shapex[3] = (float) (x + 15.5);
        shapey[3] = (float) (y - 15.5);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
        entity.setTexture(AssetMan.manager.get(AssetMan.bullet));
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
