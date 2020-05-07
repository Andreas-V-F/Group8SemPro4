/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdu.mmmi.softwareengineering.osgicommon.data;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

/**
 *
 * @author Mikkel Høyberg
 */
public class Grid {

    private ArrayList<Node> grid = new ArrayList<>();
    private Node node = new Node();

    public ArrayList<Node> getGrid() {
        return grid;
    }
    
    public void fillGrid() {
        for (int i = 0; i < (Gdx.graphics.getWidth() / node.getWidth()); i++) {
            for (int j = 0; j < (Gdx.graphics.getHeight() / node.getHeight()); j++) {
                node = new Node(i, j);
                grid.add(node);
            }
        }
    }
    
}
