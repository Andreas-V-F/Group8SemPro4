package dk.sdu.mmmi.cbse.osgiplayer;

import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.*;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private String playerID;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        Entity player = createPlayerShip(gameData);
        player.setIsPlayer(true);
        playerID = world.addEntity(player);
    }

    public Entity createPlayerShip(GameData gameData) {
        Entity playerShip = new Player();

        float maxSpeed = 300;
        float x = 500;
        float y = 500;
        float radians = 3.1415f / 2;
        playerShip.add(new LifePart(3));
        playerShip.setRadius(4);
        playerShip.add(new MovingPart(maxSpeed));
        playerShip.add(new PositionPart(x, y, radians));
        playerShip.add(new ShootingPart(playerID));

        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(playerID);
    }
}
