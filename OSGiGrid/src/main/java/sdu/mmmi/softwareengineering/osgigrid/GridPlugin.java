/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgigrid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;

/**
 *
 * @author Mikkel Høyberg
 */
public class GridPlugin implements IGamePluginService {

    private List<Node> open;
    private List<Node> closed;
    private List<Node> path;

    ArrayList<Node> nodeGrid = new ArrayList<>();

    public GridPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        System.out.println("hallo from gridPlugin");
        System.out.println(Gdx.graphics.getHeight() + " H");
        System.out.println(Gdx.graphics.getWidth() + " W");
//        int row = 10;
//        int col = 20;
//        Node[][] grid = new Node[row][col];
        Node[][] grid = new Node[gameData.getDisplayHeight()][gameData.getDisplayWidth()];

//        open = new ArrayList<>();
//        closed = new ArrayList<>();
//        path = new ArrayList<>();
//        
//        
//        // Create GRID for the world
//        int[][] twoD_arr = new int[gameData.getDisplayWidth()][gameData.getDisplayHeight()];
        createGrid(grid);
        printGrid(grid);
    }

    private void createGrid(Node[][] grid) {
        System.out.println("Am i called? - createGrid");
        //        Entity node = new Node();

        for (int x = 0; x <= Gdx.graphics.getHeight(); x += 10) {
            for (int y = 0; y <= Gdx.graphics.getWidth(); y += 10) {
                grid[x][y] = new Node(x, y);
            }
        }
//        for (int x = 0; x < 10; x++) {
//            for (int y = 0; y < 20; y++) {
//                grid[x][y] = new Node(x, y);
//            }
//        }
    }

    private void printGrid(Node[][] grid) {
        System.out.println("Am i called? - printGrid");
//        for (int k = 0; k < 10; k++) {
//            for (int j = 0; j < 20; j++) {
//                System.out.println(grid[k][j] + " - ");
//            }
//        }
        for (int k = 0; k <= Gdx.graphics.getHeight(); k += 10) {
            for (int j = 0; j <= Gdx.graphics.getWidth(); j += 10) {
                System.out.print(grid[k][j] + " - ");
            }
            System.out.println("");
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//    
//    
//    // Node class for convienience
//    static class Node implements Comparable {
//        public Node parent;
//        public int x, y;
//        public double g;
//        public double h;
//        Node(Node parent, int xpos, int ypos, double g, double h) {
//            this.parent = parent;
//            this.x = xpos;
//            this.y = ypos;
//            this.g = g;
//            this.h = h;
//       }
//       // Compare by f value (g + h)
//       @Override
//       public int compareTo(Object o) {
//           Node that = (Node) o;
//           return (int)((this.g + this.h) - (that.g + that.h));
//       }
//   }

}
//public class PlayerPlugin implements IGamePluginService {
//    private String playerID;
//    
//    public PlayerPlugin() {
//    }
//    
//    @Override
//    public void start(GameData gameData, World world) {
//        // Add entities to the world
//        Entity player = createPlayerShip(gameData);
//        player.setIsPlayer(true);
//        playerID = world.addEntity(player);
//        
//    }
//
//    private Entity createPlayerShip(GameData gameData) {
//        Entity playerShip = new Player();
//
//        float maxSpeed = 300;
//        float x = 500;
//        float y = 500;
//        float radians = 3.1415f / 2;
//        playerShip.add(new LifePart(3));
//        playerShip.setRadius(4);
//        playerShip.add(new MovingPart(maxSpeed));
//        playerShip.add(new PositionPart(x, y, radians));
//        playerShip.add(new ShootingPart(playerID));
//        
//        return playerShip;
//    }
//
//    @Override
//    public void stop(GameData gameData, World world) {
//        // Remove entities
//        world.removeEntity(playerID);
//    }
//}
