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
public class Node implements Comparable {

    private int x;
    private int y;

    private int height = 16;
    private int width = 16;

    private boolean walkable = true;

    private int costFromStart;
    private int estimatedCostToGoal;

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

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public int getCost() {
        return costFromStart + estimatedCostToGoal;
    }

    public int getCost(Node node) {
        return 1;
    }

    @Override
    public int compareTo(Object other) {
        float thisValue = this.getCost();
        float otherValue = ((Node) other).getCost();

        float v = thisValue - otherValue;
        return (v > 0) ? 1 : (v < 0) ? -1 : 0;
    }

    public int getEstimatedCost(Node node) {
        return Math.abs(node.getEstimatedCostToGoal() - this.getEstimatedCostToGoal());
    }

    public int getCostFromStart() {
        return costFromStart;
    }

    public void setCostFromStart(int costFromStart) {
        this.costFromStart = costFromStart;
    }

    public int getEstimatedCostToGoal() {
        return estimatedCostToGoal;
    }

    public void setEstimatedCostToGoal(int estimatedCostToGoal) {
        this.estimatedCostToGoal = estimatedCostToGoal;
    }

}
