package sdu.mmmi.softwareengineering.osgienemy;

import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.Level;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.managers.AssetMan;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;

public class EnemyProcessor implements IEntityProcessingService {

    private boolean first = true;

    int K = 0;
    boolean hitbox_on = false;

    @Override
    public void process(GameData gameData, World world) {

        if (first) {
            for (Level l : world.getLevels()) {
                for (Entity entity : l.getEntities(Enemy.class)) {
                    entity.setTexture(AssetMan.manager.get(AssetMan.enemy_down));
                }
            }
            first = false;
        }

        for (Entity entity : world.getEntities(Enemy.class)) {

            updateHitbox(entity);
        }
    }

    private void updateHitbox(Entity entity) {

        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();

        PositionPart positionPart = entity.getPart(PositionPart.class);

        float x;
        float y;
        x = positionPart.getX();
        y = positionPart.getY();

        final int enemy_width = entity.getTexture().getWidth();
        final int enemy_height = entity.getTexture().getHeight();

        //Lower left corner
        shapex[0] = (float) (x - enemy_width / 2);
        shapey[0] = (float) (y - (enemy_height / 2));

        //Upper left corner
        shapex[1] = (float) (x - enemy_width / 2);
        shapey[1] = (float) (y + (enemy_height / 2));

        //Upper right corner 
        shapex[2] = (float) (x + enemy_width / 2);
        shapey[2] = (float) (y + (enemy_height / 2));

        //Lower right corner
        shapex[3] = (float) (x + enemy_width / 2);
        shapey[3] = (float) (y - (enemy_height / 2));

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);

    }
}
