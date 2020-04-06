/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgienemy;

import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.EntityPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.LifePart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.MovingPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.ShootingPart;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;

/**
 *
 * @author andre
 */
public class EnemyPlugin implements IGamePluginService {

    private String enemyID;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        for (int i = 0; i < 1; i++) {
            Entity enemy = createEnemy(gameData);
            enemyID = world.addEntity(enemy);
            ShootingPart ep = enemy.getPart(ShootingPart.class);
            ep.setID(enemyID);
        }

    }

    private Entity createEnemy(GameData gameData) {
        Entity enemy = new Enemy();

        float maxSpeed = 250;

        float x = 300;
        float y = 300;

        float radians = 3.1415f / 2;
        enemy.add(new LifePart(3));
        enemy.add(new MovingPart(maxSpeed));
        enemy.add(new PositionPart(x, y, radians));
        enemy.add(new ShootingPart());

        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemyID);
    }

}
