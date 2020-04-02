/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicommon.data;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author Andreas Ibsen Cor
 */
public class UnplayableArea implements Serializable{
    
    private final UUID ID = UUID.randomUUID();
    
    private float[] shapeX = new float[4];
    private float[] shapeY = new float[4];
    
    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }

    public String getID() {
        return ID.toString();
    }
    
}
