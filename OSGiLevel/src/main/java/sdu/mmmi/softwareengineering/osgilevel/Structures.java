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
//        StructureList.add(arrayList);
//        
//        arrayList = new ArrayList<>();
//        wall = new Wall(300, 170, 315, 100);
//        arrayList.add(wall);
//        wall = new Wall(300, 200, 315, 180);
//        arrayList.add(wall);
////        StructureList.add(arrayList);
//        
//        arrayList = new ArrayList<>();
//        wall = new Wall(350, 200, 360, 100);
//        arrayList.add(wall);
//        wall = new Wall(360, 160, 410, 150);
//        arrayList.add(wall);
//        wall = new Wall(400, 160, 410, 200);
//        arrayList.add(wall);
//        wall = new Wall(385, 150, 395, 100);
//        arrayList.add(wall);
//        StructureList.add(arrayList);
        
    }

    public ArrayList<ArrayList<Wall>> getStructureList() {
        return StructureList;
    }
    
    

}
