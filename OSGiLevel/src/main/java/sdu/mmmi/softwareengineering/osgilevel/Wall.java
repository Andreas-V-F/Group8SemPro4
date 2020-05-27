/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgilevel;

import sdu.mmmi.softwareengineering.osgicommon.data.Node;
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

    public Wall(Node n1, Node n2) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];

        float minX = 0;
        float maxX = 0;
        float minY = 0;
        float maxY = 0;

        if (n1.getX() <= n2.getX()) {
            minX = n1.getX();
            maxX = n2.getX();
        } else {
            minX = n2.getX();
            maxX = n1.getX();
        }

        if (n1.getY() <= n2.getY()) {
            minY = n1.getY();
            maxY = n2.getY();
        } else {
            minY = n2.getY();
            maxY = n1.getY();
        }

        //Lower left corner
        shapex[0] = minX * n1.getWidth();
        shapey[0] = minY * n1.getHeight();

        //Upper left corner
        shapex[1] = minX * n1.getWidth();
        shapey[1] = maxY * n1.getHeight() + n1.getHeight();

        //Upper right corner 
        shapex[2] = maxX * n1.getWidth() + n1.getWidth();
        shapey[2] = maxY * n1.getHeight() + n1.getHeight();

        //Lower right corner
        shapex[3] = maxX * n1.getWidth() + n1.getWidth();
        shapey[3] = minY * n1.getHeight();

        setShapeX(shapex);
        setShapeY(shapey);
    }

}
