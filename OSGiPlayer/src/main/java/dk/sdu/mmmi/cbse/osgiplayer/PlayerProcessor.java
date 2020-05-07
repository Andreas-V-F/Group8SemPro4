/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgiplayer;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import sdu.mmmi.softwareengineering.osgicommon.data.*;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.*;
import sdu.mmmi.softwareengineering.osgicommon.managers.AssetMan;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

/**
 *
 * @author menta
 */
public class PlayerProcessor implements IEntityProcessingService {

    private long time;
    private long delay = 100;
    private boolean first = true;
    

    @Override
    public void process(GameData gameData, World world) {
        
        for (Entity player : world.getEntities(Player.class)) {
            
            if(first){
                player.setTexture(AssetMan.manager.get(AssetMan.characterDown));
                first = false;
            }
            
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
            ShootingPart shootingPart = player.getPart(ShootingPart.class);
            
            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.A));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.D));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.W));
            movingPart.setDown(gameData.getKeys().isDown(GameKeys.S));

            if (System.currentTimeMillis() - time > delay) {
                if (gameData.getKeys().isDown(GameKeys.UP)) {
                    shootingPart.setDirection("UP");
                    shootingPart.setIsShooting(true);
                } else if (gameData.getKeys().isDown(GameKeys.DOWN)) {
                    shootingPart.setDirection("DOWN");
                    shootingPart.setIsShooting(true);
                } else if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                    shootingPart.setDirection("LEFT");
                    shootingPart.setIsShooting(true);
                } else if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                    shootingPart.setDirection("RIGHT");
                    shootingPart.setIsShooting(true);
                }
                time = System.currentTimeMillis();
            }

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);

            updatePlayer(gameData, player, AssetMan.characterLeft, AssetMan.characterRight, AssetMan.characterUp, AssetMan.characterDown);

            updateHitbox(player);

        }
    }

    private void updatePlayer(GameData gameData, Entity entity, AssetDescriptor<Texture> left, AssetDescriptor<Texture> right, AssetDescriptor<Texture> up, AssetDescriptor<Texture> down) {

        // Sets the texture on the player to the way he moves
        if (gameData.getKeys().isDown(GameKeys.W)) {
            entity.setTexture(AssetMan.manager.get(up));
        }
        if (gameData.getKeys().isDown(GameKeys.S)) {
            entity.setTexture(AssetMan.manager.get(down));
        }
        if (gameData.getKeys().isDown(GameKeys.A)) {
            entity.setTexture(AssetMan.manager.get(left));
        }
        if (gameData.getKeys().isDown(GameKeys.D)) {
            entity.setTexture(AssetMan.manager.get(right));
        }
    }

    private void updateHitbox(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();

        final int player_width = entity.getTexture().getWidth();
        final int player_height = entity.getTexture().getHeight();

        //Lower left corner
        shapex[0] = (float) (x - player_width / 2);
        shapey[0] = (float) (y - (player_height / 2));

        //Upper left corner
        shapex[1] = (float) (x - player_width / 2);
        shapey[1] = (float) (y + (player_height / 2));

        //Upper right corner 
        shapex[2] = (float) (x + player_width / 2);
        shapey[2] = (float) (y + (player_height / 2));

        //Lower right corner
        shapex[3] = (float) (x + player_width / 2);
        shapey[3] = (float) (y - (player_height / 2));

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

//    //TODO: Dependency injection via Declarative Services
//    public void setBulletService(BulletSPI bulletService) {
//        this.bulletService = bulletService;
//    }
//
//    public void removeBulletService() {
//        this.bulletService = null;
//    }
}
