
package sdu.mmmi.softwareengineering.osgicore.managers;

import sdu.mmmi.softwareengineering.osgicore.gamestates.GameState;
import sdu.mmmi.softwareengineering.osgicore.gamestates.MenuState;
import sdu.mmmi.softwareengineering.osgicore.gamestates.PlayState;


public class GameStateManager {
    
    private GameState gameState;
    
    public static final int MENU = 0;
    public static final int PLAY = 1;
    public int gState;
    
    public GameStateManager(){
        setState(MENU);
    }
    
    public void setState(int state){
        if(gameState != null) gameState.dispose();
        if(state == MENU){
            gameState = new MenuState(this);
            gState = 0;
        }
        if(state == PLAY){
            gameState = new PlayState(this);
            gState = 1;
        }
    }
    
    public void update(float dt){
        gameState.update(dt);
    }
    
    public void draw(){
        gameState.draw();
    }
}
