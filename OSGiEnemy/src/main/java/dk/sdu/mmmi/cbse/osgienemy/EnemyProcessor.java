/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgienemy;

import java.util.Random;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.GameKeys;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.MovingPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.managers.AssetMan;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

/**
 *
 * @author madsm
 */
public class EnemyProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Enemy.class)) {

            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);

            Random rand = new Random();

            float rng = rand.nextFloat();

            if (gameData.getKeys().isDown(GameKeys.UP)) {
                entity.setTexture(AssetMan.manager.get(AssetMan.enemyUp));
                movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));
            }

            if(gameData.getKeys().isDown(GameKeys.LEFT)) {
                entity.setTexture(AssetMan.manager.get(AssetMan.enemyLeft));
                movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            }

            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                entity.setTexture(AssetMan.manager.get(AssetMan.enemyRight));
                movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            }

            movingPart.process(gameData, entity);
            positionPart.process(gameData, entity);

        }
    }

}
