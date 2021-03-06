/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicommon.data.entityParts;

import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;

public class MovingPart implements EntityPart {

    private float dx, dy;
    private float maxSpeed;
    private boolean left, right, up, down;

    public MovingPart(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setSpeed(float speed) {
        this.maxSpeed = speed;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        float dt = gameData.getDelta();

        if (left) {
            x -= maxSpeed * dt;
        }
        if (right) {
            x += maxSpeed * dt;
        }
        if (up) {
            y += maxSpeed * dt;
        }
        if (down) {
            y -= maxSpeed * dt;
        }

        // set position
        x += dx * dt;

        if (x > gameData.getDisplayWidth()) {
            x = 0;
        } else if (x < 0) {
            x = gameData.getDisplayWidth();
        }

        y += dy * dt;

        if (y > gameData.getDisplayHeight()) {
            y = 0;
        } else if (y < 0) {
            y = gameData.getDisplayHeight();
        }

        positionPart.setX(x);
        positionPart.setY(y);
        positionPart.setRadians(radians);
    }

}
