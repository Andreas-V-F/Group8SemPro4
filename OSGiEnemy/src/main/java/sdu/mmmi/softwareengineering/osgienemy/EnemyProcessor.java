package sdu.mmmi.softwareengineering.osgienemy;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.GameKeys;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.MovingPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.ShootingPart;
import sdu.mmmi.softwareengineering.osgicommon.managers.AssetMan;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

public class EnemyProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(Enemy.class)) {

            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            ShootingPart shootingPart = entity.getPart(ShootingPart.class);

            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));
            movingPart.setDown(gameData.getKeys().isDown(GameKeys.DOWN));

            movingPart.process(gameData, entity);
            positionPart.process(gameData, entity);

            updateEnemy(gameData, entity, AssetMan.happy_boiii_left, AssetMan.happy_boiii_right, AssetMan.happy_boiii_up, AssetMan.happy_boiii_down);
            
           
                updateHitbox(gameData, entity);
            
        }
    }

    private void updateEnemy(GameData gameData, Entity entity, AssetDescriptor<Texture> left, AssetDescriptor<Texture> right, AssetDescriptor<Texture> up, AssetDescriptor<Texture> down) {

        // Sets the texture on the enemy to the way he moves
        if (gameData.getKeys().isDown(GameKeys.UP)) {
            entity.setTexture(AssetMan.manager.get(up));
        }
        if (gameData.getKeys().isDown(GameKeys.DOWN)) {
            entity.setTexture(AssetMan.manager.get(down));
        }
        if (gameData.getKeys().isDown(GameKeys.LEFT)) {
            entity.setTexture(AssetMan.manager.get(left));
        }
        if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
            entity.setTexture(AssetMan.manager.get(right));
        }
    }

    private void updateHitbox(GameData gameData, Entity entity) {

        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();

        PositionPart positionPart = entity.getPart(PositionPart.class);

        float x;
        float y; 
        if (gameData.getKeys().isDown(GameKeys.ALT)) {
             x = positionPart.getX();
             y = positionPart.getY();

            final int enemy_width = 64;
            final int enemy_height = 64;

            //Lower left corner
            shapex[0] = (float) (x - enemy_width / 2);
            shapey[0] = (float) (y - (enemy_height / 2));

            //Upper left corner
            shapex[1] = (float) (x - enemy_width / 2);
            shapey[1] = (float) (y + (enemy_height / 2));

            //Upper right corner 
            shapex[2] = (float) (x + enemy_width / 2);
            shapey[2] = (float) (y + (enemy_height / 2));

            //Lower right corner
            shapex[3] = (float) (x + enemy_width / 2);
            shapey[3] = (float) (y - (enemy_height / 2));

        }
                if (gameData.getKeys().isDown(GameKeys.CTRL)) {
                    
                    x = 0; 
                    y = 0; 

                  
                  
                    
                    
                    
                    

        }
    }
}
