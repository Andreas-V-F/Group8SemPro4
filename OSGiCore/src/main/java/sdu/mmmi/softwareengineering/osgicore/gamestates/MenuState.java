
package sdu.mmmi.softwareengineering.osgicore.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.GameKeys;
import sdu.mmmi.softwareengineering.osgicore.Game;
import sdu.mmmi.softwareengineering.osgicore.managers.GameStateManager;


public class MenuState extends GameState {
   
    private SpriteBatch sb;
    private GameData gameData = new GameData();
    
    private BitmapFont titleFont;
    private BitmapFont font;
    private final String title = "JØLP ETERNAL";
    
    private int currentItem;
    private String[] menuItems;
    
    
    public MenuState(GameStateManager gsm){
        super(gsm);
        init();
    }
    
    @Override
    public void init(){
        sb = new SpriteBatch();
        
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
//            Gdx.files.internal("C:\\Users\\menta\\Documents\\GitHub\\Group8SemPro4\\OSGiCore\\src\\main\\java\\fonts\\mnml fnt.ttf")
        );
        
        menuItems = new String[]{
            "Play",
            "Options",
            "Exit"
        };
    }
    
    @Override
    public void update(float dt){
        
        handleInput();
        
    }
    
    @Override
    public void draw(){
        sb.setProjectionMatrix(Game.cam.combined);
        
        sb.begin();
        
        sb.end();

    }
    
    @Override
    public void handleInput(){
        if(gameData.getKeys().isPressed(GameKeys.SPACE)){
            gsm.setState(GameStateManager.PLAY);
        }
    }
    
    @Override
    public void dispose(){
        
    }
}
