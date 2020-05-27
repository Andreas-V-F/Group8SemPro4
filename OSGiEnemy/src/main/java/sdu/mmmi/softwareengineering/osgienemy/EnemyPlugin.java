/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgienemy;

import java.util.Random;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.Level;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
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
        for (Level l : world.getLevels()) {
            if (l.getID().equals(world.getCurrentLevel().getID())) {
                continue;
            }
            Random rand = new Random();
            for (int i = 0; i < rand.nextInt(1) + 1; i++) {
                Entity enemy = createEnemy(gameData);
                enemyID = l.addEntity(enemy);
                ShootingPart ep = enemy.getPart(ShootingPart.class);
                ep.setID(enemyID);
            }
        }
    }

    private Entity createEnemy(GameData gameData) {
        Entity enemy = new Enemy();

        float maxSpeed = 250;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;

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
