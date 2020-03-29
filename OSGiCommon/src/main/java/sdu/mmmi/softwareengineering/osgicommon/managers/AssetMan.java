/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.mmmi.softwareengineering.osgicommon.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Mikkel Høyberg
 */
public class AssetMan {

    public static final AssetManager manager = new AssetManager();

//    public static final String playerUp = "C:/Users/krute/Documents/NetBeansProjects/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/CharacterUp.png";
//    public static final String playerDown = "C:/Users/krute/Documents/NetBeansProjects/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/CharacterDown.png";
//    public static final String playerLeft = "C:/Users/krute/Documents/NetBeansProjects/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/CharacterLeft.png";
//    public static final String playerRight = "C:/Users/krute/Documents/NetBeansProjects/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/CharacterRight.png";
    public static final AssetDescriptor<Texture> characterUp = new AssetDescriptor<Texture>("C:/Users/madsm/Desktop/Coding/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/CharacterUp.png", Texture.class);
    public static final AssetDescriptor<Texture> characterDown = new AssetDescriptor<Texture>("C:/Users/madsm/Desktop/Coding/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/CharacterDown.png", Texture.class);
    public static final AssetDescriptor<Texture> characterLeft = new AssetDescriptor<Texture>("C:/Users/madsm/Desktop/Coding/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/CharacterLeft.png", Texture.class);
    public static final AssetDescriptor<Texture> characterRight = new AssetDescriptor<Texture>("C:/Users/madsm/Desktop/Coding/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/CharacterRight.png", Texture.class);
    public static final AssetDescriptor<Texture> enemyUp = new AssetDescriptor<Texture>("C:/Users/madsm/Desktop/Coding/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/enemy_up.png", Texture.class);
    public static final AssetDescriptor<Texture> enemyDown = new AssetDescriptor<Texture>("C:/Users/madsm/Desktop/Coding/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/enemy_down.png", Texture.class);
    public static final AssetDescriptor<Texture> enemyLeft = new AssetDescriptor<Texture>("C:/Users/madsm/Desktop/Coding/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/enemy_left.png", Texture.class);
    public static final AssetDescriptor<Texture> enemyRight = new AssetDescriptor<Texture>("C:/Users/madsm/Desktop/Coding/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/enemy_right.png", Texture.class);

    public static void load() {
        //Loading main character
        manager.load(characterUp);
        manager.load(characterDown);
        manager.load(characterLeft);
        manager.load(characterRight);
        
        //Loading enemy
        manager.load(enemyUp);
        manager.load(enemyDown);
        manager.load(enemyLeft);
        manager.load(enemyRight);
        
//        manager.load("C:/Users/krute/Documents/NetBeansProjects/Group8SemPro4/OSGiCommon/src/main/java/sdu/mmmi/softwareengineering/osgicommon/assets/char.png", Texture.class);
//        manager.load(charTexture);
//        manager.finishLoading();
    }

    public void dispose() {
        System.out.println("Dispose is called!");
        manager.dispose();
    }

}
