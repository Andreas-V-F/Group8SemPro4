/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgispritetest;

import static java.lang.System.currentTimeMillis;
import sdu.mmmi.softwareengineering.osgicommon.bullet.BulletSPI;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.GameKeys;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.LifePart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.MovingPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.ShootingPart;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

/**
 *
 * @author andre
 */
public class SpriteProcessing implements IEntityProcessingService {

    private long time;

//    private SpriteManager spritemanager = new SpriteManager();
//    
//    private Texture texture = new Texture("C:\\Users\\andre\\Documents\\GitHub\\Group8SemPro4\\OGSiSpriteTest\\src\\main\\java\\assets\\char.png");
    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(SpriteTest.class)) {

            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            ShootingPart shootingPart = entity.getPart(ShootingPart.class);
            LifePart lifePart = entity.getPart(LifePart.class);

            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.A));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.D));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.W));

            if (currentTimeMillis() - time > 100) {
                if (gameData.getKeys().isDown(GameKeys.UP)) {
                shootingPart.setIsShooting(true);
                shootingPart.setDirection("UP");
            } else if (gameData.getKeys().isDown(GameKeys.DOWN)) {
                shootingPart.setIsShooting(true);
                shootingPart.setDirection("DOWN");
            } else if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                shootingPart.setIsShooting(true);
                shootingPart.setDirection("LEFT");
            } else if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                shootingPart.setIsShooting(true);
                shootingPart.setDirection("RIGHT");
            }
                time = currentTimeMillis();
            }

            

            movingPart.process(gameData, entity);
            positionPart.process(gameData, entity);
            shootingPart.process(gameData, entity);
            lifePart.process(gameData, entity);

            updateShape(entity);

        }
    }

    private void updateShape(Entity entity) {

        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x - 1 * 8);
        shapey[0] = (float) (y - 1 * 8);

        shapex[1] = (float) (x - 1 * 8);
        shapey[1] = (float) (y + 1 * 8);

        shapex[2] = (float) (x + 1 * 8);
        shapey[2] = (float) (y + 1 * 8);

        shapex[3] = (float) (x + 1 * 8);
        shapey[3] = (float) (y - 1 * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
//        entity.setTexture("C:\\Users\\andre\\Documents\\GitHub\\Group8SemPro4\\OSGiCore\\src\\main\\java\\assets\\char.png");
    }
}
