/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgilevel;

import java.awt.Shape;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.Level;
import sdu.mmmi.softwareengineering.osgicommon.data.UnplayableArea;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;

/**
 *
 * @author Andreas Ibsen Cor
 */
public class LevelPlugin implements IGamePluginService{
    
    public LevelPlugin(){
    }

    @Override
    public void start(GameData gameData, World world) {
        Level level = createLevel();
        world.addLevel(level);
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Level createLevel(){
        return new Level();
    }
    
}
