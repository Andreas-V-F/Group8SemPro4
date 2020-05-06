/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgigrid;

import sdu.mmmi.softwareengineering.osgicommon.data.Entity;

/**
 *
 * @author Mikkel Høyberg
 */
public class Node extends Entity {

    private int x;
    private int y;
    private float[] shape;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "{x=" + x + ", y=" + y + '}';
    }
}
