/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgigrid;

import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.GameKeys;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

/**
 *
 * @author Mikkel Høyberg
 */
public class GridProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        if (gameData.getKeys().isDown(GameKeys.SHIFT)) {
            System.out.println("Hallo from SHIFT!");
            
            drawGrid(gameData);
        }
    }

    public void drawGrid(GameData gameData) {
        for (int x = 0; x < gameData.getDisplayHeight(); x++) {
            for (int y = 0; y < gameData.getDisplayWidth(); y++) {
//                
//                float[] shapex = entity.getShapeX();
//                float[] shapey = entity.getShapeY();
//                PositionPart positionPart = entity.getPart(PositionPart.class);
//                float x = positionPart.getX();
//                float y = positionPart.getY();
//
//                final int player_width = entity.getTexture().getWidth();
//                final int player_height = entity.getTexture().getHeight();
//
//                //Lower left corner
//                shapex[0] = (float) (x - player_width / 2);
//                shapey[0] = (float) (y - (player_height / 2));
//
//                //Upper left corner
//                shapex[1] = (float) (x - player_width / 2);
//                shapey[1] = (float) (y + (player_height / 2));
//
//                //Upper right corner 
//                shapex[2] = (float) (x + player_width / 2);
//                shapey[2] = (float) (y + (player_height / 2));
//
//                //Lower right corner
//                shapex[3] = (float) (x + player_width / 2);
//                shapey[3] = (float) (y - (player_height / 2));
//
//                entity.setShapeX(shapex);
//                entity.setShapeY(shapey);
            }
        }

    }

}
