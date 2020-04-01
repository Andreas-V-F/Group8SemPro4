/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicollision;

import sdu.mmmi.softwareengineering.osgicommon.bullet.Bullet;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.LifePart;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.services.IPostEntityProcessingService;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.ShootingPart;

/**
 *
 * @author andre
 */
public class Collision implements IPostEntityProcessingService {

    public boolean detectCollision(Entity e, Entity f) {

        float[] eX = e.getShapeX();
        float[] eY = e.getShapeY();
        float[] fX = f.getShapeX();
        float[] fY = f.getShapeY();

        if (eX[1] > fX[3] || fX[1] > eX[3]) {
            return false;
        }
        if (eY[1] < fY[3] || fY[1] < eY[3]) {
            return false;
        }

        return true;
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
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

                if (detectCollision(e, f)) {
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
