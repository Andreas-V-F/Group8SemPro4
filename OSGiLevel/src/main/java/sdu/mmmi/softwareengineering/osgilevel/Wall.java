/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgilevel;

import java.util.Arrays;
import sdu.mmmi.softwareengineering.osgicommon.data.UnplayableArea;

/**
 *
 * @author Andreas Ibsen Cor
 */
public class Wall extends UnplayableArea {

    public Wall(float x, float y, float x2, float y2) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];

        //Lower left corner
        shapex[0] = (float) (x);
        shapey[0] = (float) (y2);

        //Upper left corner
        shapex[1] = (float) (x);
        shapey[1] = (float) (y);

        //Upper right corner 
        shapex[2] = (float) (x2);
        shapey[2] = (float) (y);

        //Lower right corner
        shapex[3] = (float) (x2);
        shapey[3] = (float) (y2);
        
        setShapeX(shapex);
        setShapeY(shapey);
    }

}
