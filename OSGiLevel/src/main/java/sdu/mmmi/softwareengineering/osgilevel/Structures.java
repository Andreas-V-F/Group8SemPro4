/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgilevel;

import java.util.ArrayList;

/**
 *
 * @author Andreas Ibsen Cor
 */
public class Structures {

    private ArrayList<ArrayList<Wall>> StructureList;

    public Structures(float width, float height) {
        StructureList = new ArrayList<>();
        ArrayList<Wall> arrayList = new ArrayList<>();
        Wall wall = new Wall(200, 200, 300, 350);
        arrayList.add(wall);
    }

    public ArrayList<ArrayList<Wall>> getStructureList() {
        return StructureList;
    }

}
