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

    private boolean walkable = true;

    private int h = 0;
    private int g = 0;

    private int f = g + h;

    private Node parentNode;

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

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
    
    public void calcF() {
        this.f = this.g + this.h;
    }

}
