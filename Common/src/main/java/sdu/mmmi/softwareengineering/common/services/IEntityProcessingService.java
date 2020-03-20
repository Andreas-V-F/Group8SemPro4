package sdu.mmmi.softwareengineering.common.services;

import sdu.mmmi.softwareengineering.common.data.GameData;
import sdu.mmmi.softwareengineering.common.data.World;

public interface IEntityProcessingService {

    void process(GameData gameData, World world);
}
