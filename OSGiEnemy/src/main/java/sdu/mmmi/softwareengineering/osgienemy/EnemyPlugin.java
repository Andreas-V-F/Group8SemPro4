/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgienemy;

import com.badlogic.gdx.Gdx;
import java.util.Random;
import org.openide.util.Exceptions;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.Level;
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

//        System.out.println("Get Levels Size " + world.getLevels().size());
        for (Level l : world.getLevels()) {
//            System.out.println("Level ID: " + l.getID());
            if (l.getID().equals(world.getCurrentLevel().getID())) {
                continue;
            }
            Random rand = new Random();
            for (int i = 0; i < rand.nextInt(4) + 1; i++) {
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
        Random rand = new Random();
        
        float x = rand.nextInt(gameData.getDisplayWidth() - 200) + 100;
        float y = rand.nextInt(gameData.getDisplayHeight() - 200) + 100;

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
