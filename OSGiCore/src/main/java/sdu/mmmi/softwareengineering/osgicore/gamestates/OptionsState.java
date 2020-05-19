
package sdu.mmmi.softwareengineering.osgicore.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.openide.util.Exceptions;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.GameKeys;
import sdu.mmmi.softwareengineering.osgicore.Game;
import sdu.mmmi.softwareengineering.osgicore.managers.GameStateManager;


public class OptionsState extends GameState{
    
        private SpriteBatch sb;
    private GameData gameData = new GameData();

    
    private final String title = "OPTIONS";
    
    private int currentItem;
    private String[] menuItems;
    
    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/CaviarDreams.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameterTitle = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private BitmapFont titleFont;
    private BitmapFont font;
    private String[] able;
    private File enemyDest;
    private File enemySource;
    private File aiSource;
    private File aiDest;
    private File collisionSource;
    private File collisionDest;
    private File levelSource;
    private File levelDest;
    private File playerSource;
    private File playerDest;
    private File bulletSource;
    private File bulletDest;
    private String userPath = System.getProperty("user.dir");

    
     public OptionsState(GameStateManager gsm){
        super(gsm);
        init();
    }
    
    @Override
    public void init(){
        sb = new SpriteBatch();
        
        File filePath;
        int i = 3;
        for(filePath = new File(userPath); i > 0; filePath = filePath.getParentFile()){
            i--;
        }
        parameterTitle.size = 55;
        parameter.size = 35;
        titleFont = generator.generateFont(parameterTitle);
        font = generator.generateFont(parameter);
        
        enemyDest = new File("C:\\ProjectJar\\OSGiEnemy-1.0-SNAPSHOT.jar");
        aiDest = new File("C:\\ProjectJar\\OSGiAI-1.0-SNAPSHOT.jar");
        collisionDest = new File ("C:\\ProjectJar\\OSGiCollision-1.0-SNAPSHOT.jar");
        levelDest = new File ("C:\\ProjectJar\\OSGiLevel-1.0-SNAPSHOT.jar");
        playerDest = new File ("C:\\ProjectJar\\OSGiPlayer-1.0-SNAPSHOT.jar");
        bulletDest = new File ("C:\\ProjectJar\\OSGiCommonBullet-1.0-SNAPSHOT.jar");
        
        enemySource = new File (filePath + "\\OSGiEnemy\\target\\OSGiEnemy-1.0-SNAPSHOT.jar");
        aiSource = new File (filePath + "\\OSGiAI\\target\\OSGiAI-1.0-SNAPSHOT.jar");
        collisionSource = new File (filePath + "\\OSGiCollision\\target\\OSGiCollision-1.0-SNAPSHOT.jar");
        levelSource = new File (filePath + "\\OSGiLevel\\target\\OSGiLevel-1.0-SNAPSHOT.jar");
        playerSource = new File (filePath + "\\OSGiPlayer\\target\\OSGiPlayer-1.0-SNAPSHOT.jar");
        bulletSource = new File (filePath + "\\OSGiCommonBullet\\target\\OSGiCommonBullet-1.0-SNAPSHOT.jar");
        
        able = new String[]{
            "Disabled",
            "Enabled"
        };


        menuItems = new String[]{
            "Enemies: " + able[0],
            "AI: " + able[0],
            "Collision: " + able[0],
            "Level: " + able[0],
            "Player: " + able[0],
            "Bullet: " + able[0],
            "Back"
        };
    }
    
    @Override
    public void update(float dt){
        
        handleInput();
        
    }
    
    @Override
    public void draw(){
        sb.setProjectionMatrix(Game.cam.combined);

        int y = 200;
        
        sb.begin();
        
        titleFont.draw(sb, title, Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 + 300);
       
       for(int i = 0; i < menuItems.length; i++) {
           if(currentItem == i) font.setColor(Color.RED);
           else font.setColor(Color.WHITE);
           font.draw(sb, menuItems[i], Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2 + y);
           y -= 50;
       }
       
        if (enemyDest.exists()){
            menuItems[0] = "Enemies: " + able[1];
        }
        if (aiDest.exists()){
            menuItems[1] = "AI: " + able[1];
        }
        if (collisionDest.exists()){
            menuItems[2] = "Collision: " + able[1];
        }
        if (levelDest.exists()){
            menuItems[3] = "Level: " + able[1];
        }
        if (playerDest.exists()){
            menuItems[4] = "Player: " + able[1];
        }
        if (bulletDest.exists()){
            menuItems[5] = "Bullet: " + able[1];
        }
        
        sb.end();

    }
    
    @Override
    public void handleInput(){
        if(gameData.getKeys().isPressed(GameKeys.UP)){
            if(currentItem > 0){
                currentItem--;
            }
            
        }
        if(gameData.getKeys().isPressed(GameKeys.DOWN)) {
            if(currentItem < menuItems.length - 1) {
                currentItem++;
            }
        }
        if(gameData.getKeys().isPressed(GameKeys.SPACE)) {
            select();
        }
        if(gameData.getKeys().isPressed(GameKeys.ESCAPE)) {
            gsm.setState(GameStateManager.MENU);
        }
    }
    
    private void select() {
        
        if(currentItem == 0) {
            if (!enemyDest.exists()){
                try {
                    Files.copy(enemySource.toPath(), enemyDest.toPath());
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }else{
                enemyDest.delete();
                menuItems[0] = "Enemies: " + able[0];
            }
            
        } else if(currentItem == 1) {
            if (!aiDest.exists()){
                try {
                    Files.copy(aiSource.toPath(), aiDest.toPath());
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }else{
                aiDest.delete();
                menuItems[1] = "AI: " + able[0];
            }
            
        } else if(currentItem == 2) {
            if (!collisionDest.exists()){
                try {
                    Files.copy(collisionSource.toPath(), collisionDest.toPath());
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }else{
                collisionDest.delete();
                menuItems[2] = "Collision: " + able[0];
            }
            
        } else if(currentItem == 3) {
            if (!levelDest.exists()){
                try {
                    Files.copy(levelSource.toPath(), levelDest.toPath());
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }else{
                levelDest.delete();
                menuItems[3] = "Level: " + able[0];
            }
            
        } else if(currentItem == 4) {
            if (!playerDest.exists()){
                try {
                    Files.copy(playerSource.toPath(), playerDest.toPath());
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }else{
                playerDest.delete();
                menuItems[4] = "Player: " + able[0];
            }
            
        } else if(currentItem == 5) {
            if (!bulletDest.exists()){
                try {
                    Files.copy(bulletSource.toPath(), bulletDest.toPath());
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }else{
                bulletDest.delete();
                menuItems[5] = "Bullet: " + able[0];
            }
            
        }
        //back
        else if(currentItem == 6) {
            gsm.setState(GameStateManager.MENU);
        }
    }
    
    @Override
    public void dispose(){
        
    }
}
