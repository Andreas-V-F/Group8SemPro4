package sdu.mmmi.softwareengineering.osgicore.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.List;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.GameKeys;
import sdu.mmmi.softwareengineering.osgicore.Game;
import sdu.mmmi.softwareengineering.osgicore.managers.GameStateManager;
import sdu.mmmi.softwareengineering.osgicommon.data.Level;

public class MapState extends GameState {

    private SpriteBatch sb;
    private ShapeRenderer sr;
    private GameData gameData = new GameData();
    private int BackgroundSize = 300;
    private int RoomSize = 25;

    public MapState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
        sr = new ShapeRenderer();
    }

    @Override
    public void update(float dt) {

        handleInput();

    }

    @Override
    public void draw() {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(Gdx.graphics.getWidth() / 2 - BackgroundSize / 2, Gdx.graphics.getHeight() / 2 - BackgroundSize / 2, BackgroundSize, BackgroundSize);
        List<Level> l = Game.getWorld().getVisitedLevels();
        for (Level level : l) {

            sr.setColor(Color.BLACK);
            if (level.getX() == 0 && level.getY() == 0) {
                sr.setColor(Color.GOLD);
            }
            if (level.getID().equals(Game.getWorld().getCurrentLevel().getID())) {
                sr.setColor(Color.RED);
            }

            sr.rect(Gdx.graphics.getWidth() / 2 - RoomSize / 2 + level.getX() * RoomSize, Gdx.graphics.getHeight() / 2 - RoomSize / 2 + level.getY() * RoomSize, RoomSize - 5, RoomSize - 5);

        }
        sr.end();

    }

    @Override
    public void handleInput() {
        if (gameData.getKeys().isDown(GameKeys.M)) {
            gsm.setState(GameStateManager.PLAY);
        }
    }

    private void select() {

    }

    @Override
    public void dispose() {

    }

}
