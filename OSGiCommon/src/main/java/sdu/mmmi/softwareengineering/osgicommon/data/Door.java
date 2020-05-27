/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicommon.data;

/**
 *
 * @author Andreas Ibsen Cor
 */
public class Door extends UnplayableArea {

    private String levelID;
    private boolean isActivated = false;
    private String rotation = "UP";
    private int size = 80;

    public Door(String levelID, String rotation) {
        this.levelID = levelID;
        this.rotation = rotation.toUpperCase();
    }

    public String getLevelID() {
        return levelID;
    }

    public boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public String getRotation() {
        return rotation;
    }

    public int getSize() {
        return size;
    }

}
