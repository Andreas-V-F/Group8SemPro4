package sdu.mmmi.softwareengineering.osgicommon.data.entityParts;

import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;

public class ShootingPart implements EntityPart {

    private boolean isShooting;
    private String ID;
    private String direction;

    public ShootingPart(String id) {
        this.ID = id;
    }

    public ShootingPart() {
    }

    public boolean isShooting() {
        return this.isShooting;
    }

    public void setIsShooting(boolean b) {
        this.isShooting = b;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDirection() {
        return direction;
    }

    public boolean getIsShooting() {
        return isShooting;
    }

}
