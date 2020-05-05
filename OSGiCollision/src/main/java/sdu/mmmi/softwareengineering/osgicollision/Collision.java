/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicollision;

import sdu.mmmi.softwareengineering.osgicommon.bullet.Bullet;
import sdu.mmmi.softwareengineering.osgicommon.data.Door;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.Level;
import sdu.mmmi.softwareengineering.osgicommon.data.UnplayableArea;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.LifePart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.MovingPart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.services.IPostEntityProcessingService;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.ShootingPart;

/**
 *
 * @author andre
 */
public class Collision implements IPostEntityProcessingService {

    public boolean detectCollision(float[] eX, float[] eY, float[] fX, float[] fY) {

        if (eX[1] > fX[3] || fX[1] > eX[3]) {
            return false;
        }
        if (eY[1] < fY[3] || fY[1] < eY[3]) {
            return false;
        }

        return true;
    }

    public void pushBack(Entity entity, UnplayableArea unplayableArea) {

        PositionPart positionPart = entity.getPart(PositionPart.class);

        float[] unplayableAreaShapeX = unplayableArea.getShapeX();
        float[] unplayableAreaShapeY = unplayableArea.getShapeY();

        //left wall
        float[] shapeX = new float[]{unplayableAreaShapeX[0], unplayableAreaShapeX[0], unplayableAreaShapeX[1], unplayableAreaShapeX[1]};
        float[] shapeY = new float[]{unplayableAreaShapeY[0], unplayableAreaShapeY[1], unplayableAreaShapeY[1], unplayableAreaShapeY[0]};

        if (detectCollision(shapeX, shapeY, entity.getShapeX(), entity.getShapeY())) {
            positionPart.setX(positionPart.getX() - 10);
        }

        //right wall
        shapeX = new float[]{unplayableAreaShapeX[3], unplayableAreaShapeX[3], unplayableAreaShapeX[2], unplayableAreaShapeX[2]};
        shapeY = new float[]{unplayableAreaShapeY[3], unplayableAreaShapeY[2], unplayableAreaShapeY[2], unplayableAreaShapeY[3]};

        if (detectCollision(shapeX, shapeY, entity.getShapeX(), entity.getShapeY())) {
            positionPart.setX(positionPart.getX() + 10);
        }

        //upper wall
        shapeX = new float[]{unplayableAreaShapeX[1], unplayableAreaShapeX[1], unplayableAreaShapeX[2], unplayableAreaShapeX[2]};
        shapeY = new float[]{unplayableAreaShapeY[1], unplayableAreaShapeY[2], unplayableAreaShapeY[2], unplayableAreaShapeY[1]};

        if (detectCollision(shapeX, shapeY, entity.getShapeX(), entity.getShapeY())) {
            positionPart.setY(positionPart.getY() + 10);
        }

        //lower wall
        shapeX = new float[]{unplayableAreaShapeX[0], unplayableAreaShapeX[0], unplayableAreaShapeX[3], unplayableAreaShapeX[3]};
        shapeY = new float[]{unplayableAreaShapeY[0], unplayableAreaShapeY[3], unplayableAreaShapeY[3], unplayableAreaShapeY[0]};

        if (detectCollision(shapeX, shapeY, entity.getShapeX(), entity.getShapeY())) {
            positionPart.setY(positionPart.getY() - 10);
        }

    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            for (UnplayableArea un : world.getCurrentLevel().getUnplayableAreas()) {
                if (detectCollision(e.getShapeX(), e.getShapeY(), un.getShapeX(), un.getShapeY())) {
                    if (e.getClass().equals(Bullet.class)) {
                        world.removeEntity(e);
                        continue;
                    }
                    if (world.getCurrentLevel().getEntities().size() - world.getCurrentLevel().getEntities(Bullet.class).size() - 1 == 0) {
                        if (un.getClass().equals(Door.class) && e.getIsPlayer()) {
                            Door door = (Door) un;
                            int distance = door.getSize() + 20;
                            door.setIsActivated(true);
                            PositionPart positionPart = e.getPart(PositionPart.class);
                            switch (door.getRotation()) {
                                case "LEFT":
                                    positionPart.setX(gameData.getDisplayWidth() - door.getSize() - distance);
                                    positionPart.setY(gameData.getDisplayHeight() / 2);
                                    break;
                                case "RIGHT":
                                    positionPart.setX(0 + door.getSize() + distance);
                                    positionPart.setY(gameData.getDisplayHeight() / 2);
                                    break;
                                case "UP":
                                    positionPart.setX(gameData.getDisplayWidth() / 2);
                                    positionPart.setY(0 + door.getSize() + distance);
                                    break;
                                case "DOWN":
                                    positionPart.setX(gameData.getDisplayWidth() / 2);
                                    positionPart.setY(gameData.getDisplayHeight() - door.getSize() - distance);
                            }
                        }
                    }
                    pushBack(e, un);
                }
            }

            for (Entity f : world.getEntities()) {
                if (e.getID().equals(f.getID()) || e.getClass().equals(f.getClass())) {
                    continue;
                }

                if (e.getClass().equals(Bullet.class)) {
                    Bullet bullet = (Bullet) e;
                    if (bullet.getShooterID().equals(f.getID())) {
                        continue;
                    }
                }
                if (f.getClass().equals(Bullet.class)) {
                    Bullet bullet = (Bullet) f;
                    if (bullet.getShooterID().equals(e.getID())) {
                        continue;
                    }
                }

                if (detectCollision(e.getShapeX(), e.getShapeY(), f.getShapeX(), f.getShapeY())) {
                    if (e.getPart(LifePart.class) != null) {
                        LifePart lp = e.getPart(LifePart.class);
                        lp.setIsHit(true);
                        lp.process(gameData, e);

                        if (lp.isDead()) {

                            world.removeEntity(e);
                        }
                    } else {
                        world.removeEntity(e);
                    }
                    if (f.getPart(LifePart.class) != null) {
                        LifePart lp = f.getPart(LifePart.class);
                        lp.setIsHit(true);
                        lp.process(gameData, f);

                        if (lp.isDead()) {

                            world.removeEntity(f);
                        }
                    } else {
                        world.removeEntity(f);
                    }

                }
            }
        }
    }

}
