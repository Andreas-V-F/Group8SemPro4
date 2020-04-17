
package sdu.mmmi.softwareengineering.osgicore.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.GameKeys;
import sdu.mmmi.softwareengineering.osgicore.Game;
import sdu.mmmi.softwareengineering.osgicore.managers.GameStateManager;


public class MenuState extends GameState {
   
    private SpriteBatch sb;
    private GameData gameData = new GameData();
    
    private final String title = "JØLP ETERNAL";
    
    private int currentItem;
    private String[] menuItems;
    
    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/CaviarDreams.ttf"));
    private FreeTypeFontParameter parameterTitle = new FreeTypeFontParameter();
    private FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    private BitmapFont titleFont;
    private BitmapFont font;

    
    public MenuState(GameStateManager gsm){
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

        int i = 0;
        int y = 700;
        
        sb.begin();
        
        titleFont.draw(sb, title, 500, 800);
        
       while ( i < menuItems.length) {
           font.draw(sb, menuItems[i], 525, y);
           i++;
           y -= 50;
       }
        
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
