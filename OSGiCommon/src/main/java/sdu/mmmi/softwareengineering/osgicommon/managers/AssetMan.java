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


    // Shrek assets
    public static final AssetDescriptor<Texture> enemy_up = new AssetDescriptor<Texture>("assets/enemy/up.png", Texture.class);
    public static final AssetDescriptor<Texture> enemy_down = new AssetDescriptor<Texture>("assets/enemy/down.png", Texture.class);
    public static final AssetDescriptor<Texture> enemy_left = new AssetDescriptor<Texture>("assets/enemy/left.png", Texture.class);
    public static final AssetDescriptor<Texture> enemy_right = new AssetDescriptor<Texture>("assets/enemy/right.png", Texture.class);

    //HappyBoiii assets
    public static final AssetDescriptor<Texture> happy_boiii_up = new AssetDescriptor<Texture>("assets/character/HappyBoiii_up.png", Texture.class);
    public static final AssetDescriptor<Texture> happy_boiii_down = new AssetDescriptor<Texture>("assets/character/HappyBoiii_down.png", Texture.class);
    public static final AssetDescriptor<Texture> happy_boiii_left = new AssetDescriptor<Texture>("assets/character/HappyBoiii_left.png", Texture.class);
    public static final AssetDescriptor<Texture> happy_boiii_right = new AssetDescriptor<Texture>("assets/character/HappyBoiii_right.png", Texture.class);

	//Bullet asset
    public static final AssetDescriptor<Texture> bullet = new AssetDescriptor<Texture>("assets/bullet/bullet.png", Texture.class);
    

    public static void loadAssets() {
        // Loads default assets
        manager.load(defaultAsset);

        //Loading main character
        manager.load(characterUp);
        manager.load(characterDown);
        manager.load(characterLeft);
        manager.load(characterRight);

        //Loading shrek assets
        manager.load(enemy_up);
        manager.load(enemy_down);
        manager.load(enemy_left);
        manager.load(enemy_right);

        //Loading HappyBoiii assets
        manager.load(happy_boiii_up);
        manager.load(happy_boiii_down);
        manager.load(happy_boiii_left);
        manager.load(happy_boiii_right);

        //Loading bullet
        manager.load(bullet);
    }

    public void dispose() {
        System.out.println("Dispose is called!");
        manager.dispose();
    }

}
