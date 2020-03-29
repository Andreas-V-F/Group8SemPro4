/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.ogsispritetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
 * @author andre
 */
public class SpriteProcessing implements IEntityProcessingService {
    
    @Override
    public void process(GameData gameData, World world) {
        
        for (Entity entity : world.getEntities(SpriteTest.class)) {
            
            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));
//            if (gameData.getKeys().isDown(GameKeys.UP)) {
//                entity.setTexture(AssetMan.manager.get(AssetMan.characterUp));
//                movingPart.setUp(true);
//            }
            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
//            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
//                entity.setTexture(AssetMan.manager.get(AssetMan.characterLeft));
//                movingPart.setLeft(true);
//            }
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
//            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
//                entity.setTexture(AssetMan.manager.get(AssetMan.characterRight));
//                movingPart.setRight(true);
//            }

            System.out.println("process method: x: " + positionPart.getX() + " - y: " + positionPart.getY());
            
            movingPart.process(gameData, entity);
            positionPart.process(gameData, entity);
            updateShape(gameData, entity);
            System.out.println("process method 2: x: " + positionPart.getX() + " - y: " + positionPart.getY());
            
        }
    }
    
    private void updateShape(GameData gameData, Entity entity) {
        
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        
        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);
        
        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);
        
        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);
        
        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);
        System.out.println("updateShape method: x: " + positionPart.getX() + " - y: " + positionPart.getY());
        
        if (gameData.getKeys().isDown(GameKeys.UP)) {
            entity.setTexture(AssetMan.manager.get(AssetMan.characterUp));
        }
        if (gameData.getKeys().isDown(GameKeys.DOWN)) {
//              entity.setTexture(AssetMan.manager.get(AssetMan.characterDown));
            entity.setTexture(AssetMan.manager.get("assets/character/CharacterDown.png"));
        }
        if (gameData.getKeys().isDown(GameKeys.LEFT)) {
            entity.setTexture(AssetMan.manager.get(AssetMan.characterLeft));
        }
        if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
            entity.setTexture(AssetMan.manager.get(AssetMan.characterRight));
        }
        
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
//        entity.setTexture("C:\\Users\\andre\\Documents\\GitHub\\Group8SemPro4\\OSGiCore\\src\\main\\java\\assets\\char.png");
    }
}
