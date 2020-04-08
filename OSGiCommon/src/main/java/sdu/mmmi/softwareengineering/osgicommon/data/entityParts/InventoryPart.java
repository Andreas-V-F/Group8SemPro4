/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicommon.data.entityParts;

import java.util.ArrayList;

/**
 *
 * @author Mikkel Høyberg
 */
public class InventoryPart {

    // should maybe take a map (key:value) type, so if when an item is in there it was an ID and a name
    private ArrayList<?> inventory;

    public InventoryPart() {
        this.inventory = inventory;
    }

    public ArrayList<?> getList() {
        return inventory;
    }

    public void setList(ArrayList<?> list) {
        this.inventory = list;
    }
}
