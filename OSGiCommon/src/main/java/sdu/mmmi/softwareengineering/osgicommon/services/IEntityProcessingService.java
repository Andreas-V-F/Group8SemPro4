package sdu.mmmi.softwareengineering.osgicommon.services;

import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.World;

public interface IEntityProcessingService {

    void process(GameData gameData, World world);
}
