/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicommon.data.entityParts;

import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;

/**
 *
 * @author Phillip O
 */
public class SplitterPart implements EntityPart {

    boolean shouldSplit = false;
    boolean hasSplit = false;
    String ID;

    @Override
    public void process(GameData gameData, Entity entity) {

    }

    public boolean ShouldSplit() {
        return shouldSplit;
    }

    public void setShouldSplit(boolean shouldSplit) {
        this.shouldSplit = shouldSplit;
    }

    public SplitterPart(String id) {
        this.ID = id;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean hasSplit() {
        return hasSplit;
    }

    public void setHasSplit(boolean hasSplit) {
        this.hasSplit = hasSplit;
    }

}
