/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicommon.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author andre
 */
public class SpriteManager {
    public final AssetManager manager = new AssetManager();
	
	// Textures
	public final String playerImage = "C:\\Users\\andre\\Documents\\GitHub\\Group8SemPro4\\OSGiCore\\src\\main\\java\\assets\\char.png";
	
        public void queueAddImages(){
	manager.load(playerImage, Texture.class);
}
}
