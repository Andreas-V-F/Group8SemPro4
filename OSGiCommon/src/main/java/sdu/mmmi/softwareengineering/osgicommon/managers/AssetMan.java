package sdu.mmmi.softwareengineering.osgicommon.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import java.io.File;

/**
 *
 * @author Mikkel Høyberg
 */
public class AssetMan {

    public static final AssetManager manager = new AssetManager();

    // Character assets
    public static final AssetDescriptor<Texture> characterUp = new AssetDescriptor<Texture>("assets/character/CharacterUp.png", Texture.class);
    public static final AssetDescriptor<Texture> characterDown = new AssetDescriptor<Texture>("assets/character/CharacterDown.png", Texture.class);
    public static final AssetDescriptor<Texture> characterLeft = new AssetDescriptor<Texture>("assets/character/CharacterLeft.png", Texture.class);
    public static final AssetDescriptor<Texture> characterRight = new AssetDescriptor<Texture>("assets/character/CharacterRight.png", Texture.class);
    
    // Default assets
    public static final AssetDescriptor<Texture> defaultAsset = new AssetDescriptor<Texture>("assets/default/BossMonster.png", Texture.class);
    
    // Enemy assets
    public static final AssetDescriptor<Texture> enemyUp = new AssetDescriptor<Texture>("assets/enemy/enemy_up.png", Texture.class);
    public static final AssetDescriptor<Texture> enemyDown = new AssetDescriptor<Texture>("assets/enemy/enemy_down.png", Texture.class);
    public static final AssetDescriptor<Texture> enemyLeft = new AssetDescriptor<Texture>("assets/enemy/enemy_left.png", Texture.class);
    public static final AssetDescriptor<Texture> enemyRight = new AssetDescriptor<Texture>("assets/enemy/enemy_right.png", Texture.class);

    public static void load() {
        // Loads default assets
        manager.load(defaultAsset);

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
    }

    public void dispose() {
        System.out.println("Dispose is called!");
        manager.dispose();
    }

}
