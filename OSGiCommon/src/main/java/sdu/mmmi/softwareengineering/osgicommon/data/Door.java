/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicommon.data;

import sdu.mmmi.softwareengineering.osgicommon.data.UnplayableArea;

/**
 *
 * @author Andreas Ibsen Cor
 */
public class Door extends UnplayableArea{
    private String levelID;
    private boolean isActivated = false;
    
    public Door(String levelID){
        this.levelID = levelID;
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
    
    
    
}
