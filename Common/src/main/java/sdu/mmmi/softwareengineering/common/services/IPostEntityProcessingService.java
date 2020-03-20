package sdu.mmmi.softwareengineering.common.services;

import sdu.mmmi.softwareengineering.common.data.GameData;
import sdu.mmmi.softwareengineering.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {

    void process(GameData gameData, World world);
}
