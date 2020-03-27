/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.common.data.entityparts;

import sdu.mmmi.softwareengineering.common.data.Entity;
import sdu.mmmi.softwareengineering.common.data.GameData;

/**
 *
 * @author Alexander
 */
public interface EntityPart {
    
    int life_new = 10;

    void process(GameData gameData, Entity entity);
    
}
