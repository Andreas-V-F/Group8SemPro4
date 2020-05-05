
package sdu.mmmi.softwareengineering.osgicore.gamestates;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.GameKeys;
import sdu.mmmi.softwareengineering.osgicore.managers.GameStateManager;


public class PlayState extends GameState{
    
    private Game game;
    private GameData gameData = new GameData();
    
    public PlayState(GameStateManager gsm){
        super(gsm);
        init();
    }
    
    @Override
    public void init(){
    }
    
    @Override
    public void update(float dt){
        handleInput();
        System.out.println("PLAY UPDATE");
    }
    
    @Override
    public void draw(){
        System.out.println("PLAY DRAW");
    }
    
    @Override
    public void handleInput(){
        if(gameData.getKeys().isPressed(GameKeys.ESCAPE)){
            gsm.setState(GameStateManager.MENU);
        }
    }
    
    @Override
    public void dispose(){
        
    }

}
