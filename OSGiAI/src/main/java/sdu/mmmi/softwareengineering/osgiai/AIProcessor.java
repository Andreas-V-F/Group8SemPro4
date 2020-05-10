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
import java.util.Map;
import sdu.mmmi.softwareengineering.osgicommon.bullet.Bullet;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.Index;
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
                //System.out.println("not walkable: " + listNode(world));
                l = findPath(getEnemyNode(e, world), goalNode, world);
                if (l != null) {
                    counter = 0;
                }
            }

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

    private List<Node> getNeighbourNodes(Node node, World world) {
        ArrayList<Node> neighborNodes = new ArrayList<>();
        int[][] newPositions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int[] newPosition : newPositions) {
            int[] nodePosition = {node.getX() + newPosition[0], node.getY() + newPosition[1]};

            if (nodePosition[0] > world.getGrid().size() - 1 || nodePosition[0] < 0
                    || nodePosition[1] > Gdx.graphics.getHeight() / node.getHeight() - 1 || nodePosition[1] < 0) {
                continue;
            }
            Node newNode = null;
            for (Map.Entry<Index, Node> entry : world.getGrid().entrySet()) {
                if (entry.getValue().getX() == nodePosition[0] && entry.getValue().getY() == nodePosition[1]) {
                    newNode = entry.getValue();
                }
            }
            if (newNode == null) {
                continue;
            }
            if (!newNode.isWalkable()) {
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
        int playerNodeX = (int) Math.floor(playerPositionPart.getX() / new Node().getWidth());
        int playerNodeY = (int) Math.floor(playerPositionPart.getY() / new Node().getHeight());

        for (Map.Entry<Index, Node> entry : world.getGrid().entrySet()) {
            if (entry.getValue().getX() == playerNodeX && entry.getValue().getY() == playerNodeY) {
                return entry.getValue();
            }
        }
        return null;
    }

    private Node getEnemyNode(Entity e, World world) {
        PositionPart enmemyPositionPart = e.getPart(PositionPart.class);
        int enemyNodeX = (int) Math.floor(enmemyPositionPart.getX() / new Node().getWidth());
        int enemyNodeY = (int) Math.floor(enmemyPositionPart.getY() / new Node().getHeight());

        for (Map.Entry<Index, Node> entry : world.getGrid().entrySet()) {
            if (entry.getValue().getX() == enemyNodeX && entry.getValue().getY() == enemyNodeY) {
                return entry.getValue();
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

    public ArrayList<Node> listNode(World world) {
        ArrayList<Node> list = new ArrayList();
        for (Map.Entry<Index, Node> entry : world.getGrid().entrySet()) {
            if (!entry.getValue().isWalkable()) {
                list.add(entry.getValue());
            }
        }
        return list;
    }
}
