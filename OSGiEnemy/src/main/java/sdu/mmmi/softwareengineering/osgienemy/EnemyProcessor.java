/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgienemy;

import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.GameKeys;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.MovingPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.ShootingPart;
import sdu.mmmi.softwareengineering.osgicommon.managers.AssetMan;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

/**
 *
 * @author andre
 */
public class EnemyProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(Enemy.class)) {

            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            ShootingPart shootingPart = entity.getPart(ShootingPart.class);
            double random = Math.random();
            movingPart.setLeft(random < 0.1);
            movingPart.setRight(random > 0.2 && random < 0.4);
            movingPart.setUp(random > 0.5 && random < 0.7);
            movingPart.setDown(random > 0.8 && random < 0.9);
            if (random > 0.96) {
                if (random > 0.96 && random < 0.97) {
                    shootingPart.setIsShooting(true);
                    shootingPart.setDirection("UP");
                } else if (random > 0.97 && random < 0.98) {
                    shootingPart.setIsShooting(true);
                    shootingPart.setDirection("DOWN");
                } else if (random > 0.98 && random < 0.99) {
                    shootingPart.setIsShooting(true);
                    shootingPart.setDirection("LEFT");
                } else {
                    shootingPart.setIsShooting(true);
                    shootingPart.setDirection("RIGHT");
                }
            }

            movingPart.process(gameData, entity);
            positionPart.process(gameData, entity);
            updateShape(gameData, entity);
        }
    }

    private void updateShape(GameData gameData, Entity entity) {
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

        // Sets the texture on the character to the way he moves
        if (gameData.getKeys().isDown(GameKeys.UP)) {
            entity.setTexture(AssetMan.manager.get(AssetMan.characterUp));
        }
        if (gameData.getKeys().isDown(GameKeys.DOWN)) {
//              entity.setTexture(AssetMan.manager.get(AssetMan.characterDown));
            entity.setTexture(AssetMan.manager.get("assets/character/CharacterDown.png"));
        }
        if (gameData.getKeys().isDown(GameKeys.LEFT)) {
            entity.setTexture(AssetMan.manager.get(AssetMan.characterLeft));
        }
        if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
            entity.setTexture(AssetMan.manager.get(AssetMan.characterRight));
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
