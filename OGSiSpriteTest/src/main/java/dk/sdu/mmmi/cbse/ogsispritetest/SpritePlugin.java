/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.ogsispritetest;

import com.badlogic.gdx.assets.AssetManager;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.LifePart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.MovingPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;

/**
 *
 * @author andre
 */
public class SpritePlugin implements IGamePluginService {

    private String spritetestID;
    
    
    public SpritePlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        Entity spritetest = createSpritetestShip(gameData);
        spritetestID = world.addEntity(spritetest);

    }

    private Entity createSpritetestShip(GameData gameData) {
        Entity spritetestShip = new SpriteTest();

        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 3;
        float y = gameData.getDisplayHeight() / 3;
        float radians = 3.1415f / 2;
        spritetestShip.add(new LifePart(3));
        spritetestShip.setRadius(4);
        spritetestShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        spritetestShip.add(new PositionPart(x, y, radians));

        return spritetestShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(spritetestID);
    }

}
