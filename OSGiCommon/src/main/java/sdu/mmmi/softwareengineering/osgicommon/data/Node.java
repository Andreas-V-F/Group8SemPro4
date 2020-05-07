/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdu.mmmi.softwareengineering.osgicommon.data;

/**
 *
 * @author Mikkel Høyberg
 */
public class Node {

    private int x;
    private int y;
    
    private int height = 16;
    private int width = 16;
    
    private boolean walkable;

    public Node() {
    }

    @Override
    public String toString() {
        return "Node{" + "x=" + x + ", y=" + y + '}';
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isWalkable() {
        return walkable;
    }
    
    
}
