/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgiai;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import sdu.mmmi.softwareengineering.osgicommon.bullet.Bullet;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.Node;
import sdu.mmmi.softwareengineering.osgicommon.data.UnplayableArea;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.MovingPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.managers.AssetMan;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

/**
 *
 *
 * @author Mikkel Høyberg
 */
public class AIProcessor implements IEntityProcessingService {

    int counter = 4;
    int delay = 1;
    boolean canMove = true;
    List l = null;

    @Override
    public void process(GameData gameData, World world) {

        ArrayList<Entity> arrayList = new ArrayList();

        for (Entity entity : world.getEntities()) {
            if (!entity.getIsPlayer() && !entity.getClass().equals(Bullet.class)) {
                arrayList.add(entity);
            }
        }
        Entity e = null;

        if (!arrayList.isEmpty()) {
            e = arrayList.get(0);
        }

        if (e != null) {

            if (counter > 3) {
                Node goalNode = getPlayerNode(world);
                l = findPath(getEnemyNode(e, world), goalNode, world);
                counter = 0;
            }
            System.out.println(counter);
            if (processEnemyMovement(e, (Node) l.get(counter), gameData)) {
                counter++;
            }

        }
    }

    public static class PriorityList extends LinkedList {

        public void add(Comparable object) {
            for (int i = 0; i < size(); i++) {
                if (object.compareTo(get(i)) <= 0) {
                    add(i, object);
                    return;
                }
            }
            addLast(object);
        }
    }

    protected List<Node> constructPath(Node node) {
        LinkedList path = new LinkedList();
        while (node.getParentNode() != null) {
            path.addFirst(node);
            node = node.getParentNode();
        }
        return path;
    }

    private List findPath(Node startNode, Node goalNode, World world) {
        PriorityList openList = new PriorityList();
        LinkedList closedList = new LinkedList();

        startNode.setCostFromStart(0);
        startNode.setEstimatedCostToGoal(startNode.getEstimatedCost(goalNode));
        startNode.setParentNode(null);
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node node = (Node) openList.removeFirst();
            if (node == goalNode) {
                return constructPath(goalNode);
            }

            List neighbours = getNeighbourNodes(node, world);
            for (Object o : neighbours) {
                Node neighbourNode = (Node) o;

                boolean isOpen = openList.contains(neighbourNode);
                boolean isClosed = closedList.contains(neighbourNode);

                int costFromStart = node.getCostFromStart() + node.getCost(neighbourNode);

                if ((!isOpen && !isClosed) || costFromStart < neighbourNode.getCostFromStart()) {
                    neighbourNode.setParentNode(node);
                    neighbourNode.setCostFromStart(costFromStart);
                    neighbourNode.setEstimatedCostToGoal(neighbourNode.getEstimatedCost(goalNode));
                    if (isClosed) {
                        closedList.remove(neighbourNode);
                    }
                    if (!isOpen) {
                        openList.add(neighbourNode);
                    }
                }
            }
            closedList.add(node);
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

    private List<Node> getNeighbourNodes(Node node, World world) {
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
            if (newNode == null) {
                continue;
            }
            if (!checkIsWalkable(world, newNode)) {
                continue;
            }
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
                playerPositionPart
                        = entity.getPart(PositionPart.class
                        );

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

    private Node getEnemyNode(Entity e, World world) {
        PositionPart enmemyPositionPart = e.getPart(PositionPart.class
        );
        int enemyNodeX = (int) Math.floor(enmemyPositionPart.getX() / world.getGrid().getGrid().get(0).getWidth());
        int enemyNodeY = (int) Math.floor(enmemyPositionPart.getY() / world.getGrid().getGrid().get(0).getHeight());

        for (Node node
                : world.getGrid()
                        .getGrid()) {
            if (node.getX() == enemyNodeX && node.getY() == enemyNodeY) {
                return node;
            }
        }

        return null;
    }

    private boolean processEnemyMovement(Entity e, Node nextNode, GameData gameData) {
        PositionPart enemyPositionPart = e.getPart(PositionPart.class);
        MovingPart enemyMovingPart = e.getPart(MovingPart.class);
        enemyMovingPart.setMaxSpeed(200);
        boolean inPosition = false;
        int buffer = 8;

        enemyMovingPart.setRight(nextNode.getX() * nextNode.getWidth() + buffer > enemyPositionPart.getX());
        
        enemyMovingPart.setLeft(nextNode.getX() * nextNode.getWidth() - buffer < enemyPositionPart.getX());
        
        enemyMovingPart.setUp(nextNode.getY() * nextNode.getHeight() + buffer > enemyPositionPart.getY());
        
        enemyMovingPart.setDown(nextNode.getY() * nextNode.getHeight() - buffer < enemyPositionPart.getY());

        enemyMovingPart.process(gameData, e);
        if (nextNode.getX() * nextNode.getWidth() + buffer >= enemyPositionPart.getX() && nextNode.getX() * nextNode.getWidth() - buffer <= enemyPositionPart.getX() && nextNode.getY() * nextNode.getHeight() + buffer >= enemyPositionPart.getY() && nextNode.getY() * nextNode.getHeight() - buffer <= enemyPositionPart.getY()) {
            inPosition = true;
        }
        return inPosition;
    }
}
