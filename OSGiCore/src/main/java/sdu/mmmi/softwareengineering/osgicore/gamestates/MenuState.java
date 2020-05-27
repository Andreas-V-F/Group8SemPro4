package sdu.mmmi.softwareengineering.osgicore.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

    public MenuState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
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
    public void update(float dt) {

        handleInput();

    }

    @Override
    public void draw() {
        sb.setProjectionMatrix(Game.cam.combined);

        int y = 200;

        sb.begin();

        titleFont.draw(sb, title, Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 + 300);

        for (int i = 0; i < menuItems.length; i++) {
            if (currentItem == i) {
                font.setColor(Color.RED);
            } else {
                font.setColor(Color.WHITE);
            }
            font.draw(sb, menuItems[i], Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2 + y);
            y -= 50;
        }

        sb.end();

    }

    @Override
    public void handleInput() {
        if (gameData.getKeys().isPressed(GameKeys.UP)) {
            if (currentItem > 0) {
                currentItem--;
            }

        }
        if (gameData.getKeys().isPressed(GameKeys.DOWN)) {
            if (currentItem < menuItems.length - 1) {
                currentItem++;
            }
        }
        if (gameData.getKeys().isPressed(GameKeys.SPACE)) {
            select();
        }
    }

    private void select() {
        //play
        if (currentItem == 0) {
            gsm.setState(GameStateManager.PLAY);
        } //options
        else if (currentItem == 1) {
            gsm.setState(GameStateManager.OPTIONS);
        } //exit game
        else if (currentItem == 2) {
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {

    }
}
