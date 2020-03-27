package sdu.mmmi.softwareengineering.common.services;

import sdu.mmmi.softwareengineering.common.data.GameData;
import sdu.mmmi.softwareengineering.common.data.World;

public interface IGamePluginService {

    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
