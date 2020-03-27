package sdu.mmmi.softwareengineering.osgicommon.events;

import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import java.io.Serializable;

/**
 *
 * @author Mads
 */
public class Event implements Serializable {

    private final Entity source;

    public Event(Entity source) {
        this.source = source;
    }

    public Entity getSource() {
        return source;
    }
}
