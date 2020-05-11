/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgiai;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.LinkedList;
import sdu.mmmi.softwareengineering.osgicommon.bullet.Bullet;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.Node;
import sdu.mmmi.softwareengineering.osgicommon.data.UnplayableArea;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.MovingPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

/**
 *
 *
 * @author Mikkel Høyberg
 */
public class AIProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        System.out.println(aStar(world));

    }

    private LinkedList<Node> aStar(World world) {
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();
        ArrayList<Node> childrenList = new ArrayList<>();

        Node startNode;

        for (Entity entity : world.getEntities()) {
            if (!entity.getIsPlayer() && !entity.getClass().equals(Bullet.class)) {
                PositionPart enmemyPositionPart = entity.getPart(PositionPart.class);
                int enemyNodeX = (int) Math.floor(enmemyPositionPart.getX() / world.getGrid().getGrid().get(0).getWidth());
                int enemyNodeY = (int) Math.floor(enmemyPositionPart.getY() / world.getGrid().getGrid().get(0).getHeight());

                for (Node node : world.getGrid().getGrid()) {
                    if (node.getX() == enemyNodeX && node.getY() == enemyNodeY) {
                        startNode = node;
                        openList.add(startNode);
                    }
                }

                while (openList.size() > 0) {
                    Node currentNode = openList.get(0);
                    int currentIndex = 0;

                    for (Node node : openList) {
                        if (node.getF() < currentNode.getF()) {
                            currentNode = node;
                            currentIndex = openList.indexOf(node);
                        }
                    }

                    openList.remove(currentIndex);
                    closedList.add(currentNode);

                    if (closedList.get(closedList.size() - 1) == getPlayerNode(world)) {
                        System.out.println("Im here");
                        return getPath(currentNode);
                    }

                    for (Node node : expandNode(currentNode, world)) {
                        childrenList.add(node);
                    }

                    for (Node node : childrenList) {
                        if (closedList.contains(node)) {
                            continue;
                        }

                        node.setG(currentNode.getG() + 1);
                        node.setH((int) Math.pow((node.getX() - getPlayerNode(world).getX()), 2)
                                + (int) Math.pow((node.getY() - getPlayerNode(world).getY()), 2));
                        node.calcF();

                        for (Node openNode : openList) {
                            if (node == openNode && node.getG() > openNode.getG()) {
                                continue;
                            }
                        }
                        openList.add(node);
                    }

                }
            }
        }
        return null;
    }

    private boolean checkIsWalkable(World world, Node node) {

        for (UnplayableArea un : world.getCurrentLevel().getUnplayableAreas()) {
            float unMinX = un.getShapeX()[0];
            float unMinY = un.getShapeY()[0];

            float unMaxX = un.getShapeX()[1];
            float unMaxY = un.getShapeY()[1];

            int nodeMinX = node.getX() * node.getWidth();
            int nodeMinY = node.getY() * node.getHeight();

            int nodeMaxX = node.getX() * node.getWidth() + node.getWidth();
            int nodeMaxY = node.getY() * node.getHeight() + node.getHeight();

            if (nodeMinX >= unMinX && nodeMinY >= unMinY && unMaxX >= nodeMinX && unMaxY >= nodeMinY) {
                node.setWalkable(false);

            }
        }
        return node.isWalkable();
    }

    private ArrayList<Node> expandNode(Node node, World world) {
        ArrayList<Node> neighborNodes = new ArrayList<>();
        int[][] newPositions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int[] newPosition : newPositions) {
            int[] nodePosition = {node.getX() + newPosition[0], node.getY() + newPosition[1]};

            if (nodePosition[0] > world.getGrid().getGrid().size() - 1 || nodePosition[0] < 0
                    || nodePosition[1] > Gdx.graphics.getHeight() / node.getHeight() - 1 || nodePosition[1] < 0) {
                continue;
            }
            Node newNode = null;
            for (Node n : world.getGrid().getGrid()) {
                if (n.getX() == nodePosition[0] && n.getY() == nodePosition[1]) {
                    newNode = n;
                }
            }
            if (!checkIsWalkable(world, newNode)) {
                continue;
            }
            newNode.setParentNode(node);
            neighborNodes.add(newNode);

        }
        return neighborNodes;
    }

    private Node getPlayerNode(World world) {
        Entity player;
        PositionPart playerPositionPart = null;

        for (Entity entity : world.getEntities()) {
            if (entity.getIsPlayer()) {
                player = entity;
                playerPositionPart = entity.getPart(PositionPart.class);
                break;
            }
        }
        if (playerPositionPart == null) {
            return null;
        }
        int playerNodeX = (int) Math.floor(playerPositionPart.getX() / world.getGrid().getGrid().get(0).getWidth());
        int playerNodeY = (int) Math.floor(playerPositionPart.getY() / world.getGrid().getGrid().get(0).getHeight());

        for (Node node : world.getGrid().getGrid()) {
            if (node.getX() == playerNodeX && node.getY() == playerNodeY) {
                return node;
            }
        }
        return null;
    }

    private LinkedList<Node> getPath(Node node) {
        LinkedList<Node> path = new LinkedList<>();
        while (node.getParentNode() != null) {
            node = node.getParentNode();
            path.add(node);
        };
        return path;

    }
}
