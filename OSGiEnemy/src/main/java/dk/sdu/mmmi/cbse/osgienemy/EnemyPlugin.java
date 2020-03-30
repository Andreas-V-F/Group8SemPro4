/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgienemy;

import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.LifePart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.MovingPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;

/**
 *
 * @author madsm
 */
public class EnemyPlugin implements IGamePluginService{
    
    private String enemyID; 

    @Override
    public void start(GameData gameData, World world) {
        Entity enemy = createSpritetestShip(gameData);
        enemyID = world.addEntity(enemy);
    }
    
        private Entity createSpritetestShip(GameData gameData) {
        Entity enemy = new Enemy();

        float deacceleration = 20;
        float acceleration = 45;
        float maxSpeed = 45;
        float rotationSpeed = 5;
        float x = 0;
        float y = 0;
        float radians = 3.1415f / 2;
        enemy.add(new LifePart(3));
        enemy.setRadius(4);
        enemy.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemy.add(new PositionPart(x, y, radians));

        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemyID);
    }
    
}
