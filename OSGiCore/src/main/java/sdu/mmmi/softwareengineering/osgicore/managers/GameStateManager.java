
package sdu.mmmi.softwareengineering.osgicore.managers;

import sdu.mmmi.softwareengineering.osgicore.gamestates.GameState;
import sdu.mmmi.softwareengineering.osgicore.gamestates.MapState;
import sdu.mmmi.softwareengineering.osgicore.gamestates.MenuState;
import sdu.mmmi.softwareengineering.osgicore.gamestates.PlayState;


public class GameStateManager {
    
    private GameState gameState;
    
    public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int MAP = 2;
    
    public GameStateManager(){
        setState(MENU);
    }
    
    public void setState(int state){
        if(gameState != null) gameState.dispose();
        if(state == MENU){
            gameState = new MenuState(this);
        }
        if(state == PLAY){
            gameState = new PlayState(this);
        }
        if(state == MAP){
            gameState = new MapState(this);
        }
    }
    
    public void update(float dt){
        gameState.update(dt);
    }
    
    public void draw(){
        gameState.draw();
    }
}
