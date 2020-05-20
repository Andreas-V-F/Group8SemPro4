/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgibullet;

import sdu.mmmi.softwareengineering.osgicommon.data.Bullet;
import sdu.mmmi.softwareengineering.osgicommon.data.*;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.*;
import sdu.mmmi.softwareengineering.osgicommon.managers.AssetMan;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

/**
 *
 * @author andre
 */
public class BulletControlSystem implements IEntityProcessingService {

    private Entity bullet;

    @Override
    public void process(GameData gameData, World world) {
//        System.out.println("hejsa");
        for (Entity entity : world.getEntities()) {
            if (entity.getPart(ShootingPart.class) != null) {

                ShootingPart shootingPart = entity.getPart(ShootingPart.class);
                //Shoot if isShooting is true, ie. space is pressed.

                if (shootingPart.isShooting()) {

                    PositionPart positionPart = entity.getPart(PositionPart.class);
                    //Add entity radius to initial position to avoid immideate collision.
                    bullet = createBullet(entity, gameData);
                    shootingPart.setIsShooting(false);
                    world.addEntity(bullet);
                    
                }
            }
        }

        for (Entity b : world.getEntities(Bullet.class)) {
            
            PositionPart ppb = b.getPart(PositionPart.class);
            MovingPart mpb = b.getPart(MovingPart.class);
            TimerPart btp = b.getPart(TimerPart.class);
            if (ppb.getRadians() == (float) Math.PI / 2) {
                mpb.setUp(true);
            } else if (ppb.getRadians() == (float) (Math.PI * 10)) {
                 mpb.setDown(true);
            } else if (ppb.getRadians() == (float) Math.PI) {
                mpb.setLeft(true);
            } else {
                mpb.setRight(true);
            }
            btp.reduceExpiration(gameData.getDelta());

            //If duration is exceeded, remove the bullet.
            if (btp.getExpiration() < 0) {

                world.removeEntity(b);
            }

            ppb.process(gameData, b);
            mpb.process(gameData, b);
            btp.process(gameData, b);

            updateShape(b);
        }
    }

    //Could potentially do some shenanigans with differing colours for differing sources.
    private Entity createBullet(Entity shooter, GameData gameData) {

        PositionPart shooterPos = shooter.getPart(PositionPart.class);
        MovingPart shooterMovingPart = shooter.getPart(MovingPart.class);
        ShootingPart shooterShootingPart = shooter.getPart(ShootingPart.class);

        float x = shooterPos.getX();
        float y = shooterPos.getY();
        float speed = 350;

        Entity bullet = new Bullet(shooter.getID());

        if (shooterShootingPart.getDirection().equals("UP")) {
            bullet.add(new PositionPart(x, y + 50, (float) Math.PI / 2));
        } else if (shooterShootingPart.getDirection().equals("DOWN")) {
            bullet.add(new PositionPart(x, y + -50, (float) (Math.PI * 10)));
        } else if (shooterShootingPart.getDirection().equals("LEFT")) {
            bullet.add(new PositionPart(x - 50, y, (float) Math.PI));
        } else if (shooterShootingPart.getDirection().equals("RIGHT")) {
            bullet.add(new PositionPart(x + 50, y, 0));
        }

        bullet.add(new MovingPart(speed));
        bullet.add(new TimerPart(5));

        return bullet;
    }

    private void updateShape(Entity entity) {
        entity.setTexture(AssetMan.manager.get(AssetMan.bullet));
        
        float[] shapex = new float[4];
        float[] shapey = new float[4];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        
        final int bullet_width = entity.getTexture().getWidth();
        final int bullet_height = entity.getTexture().getHeight();

        //Lower left corner
        shapex[0] = (float) (x - bullet_width / 2);
        shapey[0] = (float) (y - (bullet_height / 2));

        //Upper left corner
        shapex[1] = (float) (x - bullet_width / 2);
        shapey[1] = (float) (y + (bullet_height / 2));

        //Upper right corner 
        shapex[2] = (float) (x + bullet_width / 2);
        shapey[2] = (float) (y + (bullet_height / 2));

        //Lower right corner
        shapex[3] = (float) (x + bullet_width / 2);
        shapey[3] = (float) (y - (bullet_height / 2));

        

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
