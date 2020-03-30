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
            Entity enemy = createEnemyShip(gameData);
            enemyID = world.addEntity(enemy);
            ShootingPart ep = enemy.getPart(ShootingPart.class);
            ep.setID(enemyID);
        }

    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Enemy();

        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 3 + (float) Math.random() * 1000;
        float y = gameData.getDisplayHeight() / 3 + (float) Math.random() * 1000;

        float radians = 3.1415f / 2;
        enemyShip.add(new LifePart(3));
        enemyShip.setRadius(4);
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(x, y, radians));
        enemyShip.add(new ShootingPart());

        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemyID);
    }

}
