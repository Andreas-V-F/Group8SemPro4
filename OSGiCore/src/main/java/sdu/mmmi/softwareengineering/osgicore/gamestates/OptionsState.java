
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
    private File fileEnemy1;
    private File fileEnemy2;

    
     public OptionsState(GameStateManager gsm){
        super(gsm);
        init();
    }
    
    @Override
    public void init(){
        sb = new SpriteBatch();
        
        parameterTitle.size = 55;
        parameter.size = 35;
        titleFont = generator.generateFont(parameterTitle);
        font = generator.generateFont(parameter);
        
        fileEnemy1 = new File("C:\\ProjectJar\\OSGiEnemy-1.0-SNAPSHOT.jar");
        fileEnemy2 = new File("C:\\Users\\menta\\Documents\\GitHub\\Group8SemPro4\\OSGiEnemy\\target\\OSGiEnemy-1.0-SNAPSHOT.jar");
        
        able = new String[]{
            "Disabled",
            "Enabled"
        };


        menuItems = new String[]{
            "Enemies: " + able[0],
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
       
        if (fileEnemy1.exists()){
            menuItems[0] = "Enemies: " + able[1];
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
    }
    
    private void select() {
        //play
        if(currentItem == 0) {
            if (!fileEnemy1.exists()){
                try {
                    Files.copy(fileEnemy2.toPath(), fileEnemy1.toPath());
                    menuItems[0] = "Enemies: " + able[1];
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }else{
                fileEnemy1.delete();
                menuItems[0] = "Enemies: " + able[0];
            }

        }
        //back
        else if(currentItem == 1) {
            gsm.setState(GameStateManager.MENU);
        }
    }
    
    @Override
    public void dispose(){
        
    }
}
