package sdu.mmmi.softwareengineering.osgicore;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import sdu.mmmi.softwareengineering.osgicommon.data.Entity;
import sdu.mmmi.softwareengineering.osgicommon.data.GameData;
import sdu.mmmi.softwareengineering.osgicommon.data.World;
import sdu.mmmi.softwareengineering.osgicommon.services.IEntityProcessingService;
import sdu.mmmi.softwareengineering.osgicommon.services.IGamePluginService;
import sdu.mmmi.softwareengineering.osgicommon.services.IPostEntityProcessingService;
import sdu.mmmi.softwareengineering.osgicore.managers.GameInputProcessor;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import sdu.mmmi.softwareengineering.osgicommon.data.GameKeys;
import sdu.mmmi.softwareengineering.osgicommon.data.UnplayableArea;
import sdu.mmmi.softwareengineering.osgicommon.data.entityParts.PositionPart;
import sdu.mmmi.softwareengineering.osgicommon.managers.AssetMan;
import sdu.mmmi.softwareengineering.osgicore.managers.GameStateManager;

public class Game implements ApplicationListener {

    public static OrthographicCamera cam;
    private ShapeRenderer sr;
    private final GameData gameData = new GameData();
    private static World world = new World();
    private static final List<IEntityProcessingService> entityProcessorList = new CopyOnWriteArrayList<>();
    private static final List<IGamePluginService> gamePluginList = new CopyOnWriteArrayList<>();
    private static final List<IGamePluginService> tempGamePluginList = new CopyOnWriteArrayList<>();
    private static List<IPostEntityProcessingService> postEntityProcessorList = new CopyOnWriteArrayList<>();

    private SpriteBatch spriteBatch;
    private GameStateManager gsm;

    private boolean drawHitboxes = false;

    public Game() {
        init();
    }

    private void init() {

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Jølp Eternal";
        cfg.width = 800;
        cfg.height = 800;
        cfg.useGL30 = false;
        cfg.resizable = false;

        new LwjglApplication(this, cfg);
    }

    @Override
    public void create() {

        spriteBatch = new SpriteBatch();

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

        world.getGridTemplate().fillGrid();

        gsm = new GameStateManager();

        // Loading Assets
        AssetMan.loadAssets();

        // Shows the process on the loading in the console
        float k = 0;
        while (!AssetMan.manager.update()) {
            float j = AssetMan.manager.getProgress();
            if (j != k) {
                System.out.println(Math.round(AssetMan.manager.getProgress() * 100) + "%");
                k = j;
            }
        }
        if (AssetMan.manager.getProgress() == 1) {
            System.out.println("100%");
            System.out.println("All Assets have been loaded!");
        }
    }

    @Override
    public void render() {

        // clear screen to black
//        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1); // Sets the background to a lightblue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw();

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        gameData.getKeys().update();

        if (gameData.getKeys().isDown(GameKeys.ALT) || drawHitboxes) {
            draw();
            drawHitboxes = true;
        }
        if (gameData.getKeys().isDown(GameKeys.CTRL)) {
            drawHitboxes = false;
        }
        if (gsm.gState == 1) {
            update();

            // Sets a texture for every entity in the "world"
            for (Entity entity : world.getEntities()) {
                PositionPart positionPart = entity.getPart(PositionPart.class);

                if (entity.getTexture() != null) {
                    spriteBatch.begin();
                    // Don't know if the math is right!
                    spriteBatch.draw(entity.getTexture(), positionPart.getX() - (entity.getTexture().getHeight() / 2), positionPart.getY() - (entity.getTexture().getWidth() / 2));
                    spriteBatch.end();
                }
            }
            for (UnplayableArea un : world.getCurrentLevel().getUnplayableAreas()) {
                //sets all unplayableareas to the same texture
                if (un.getTextureRegion() == null) {
                    un.setTextureRegion(AssetMan.manager.get(AssetMan.wall));
                }

                if (un.getTexture().equals(AssetMan.manager.get(AssetMan.wall))) {
                    spriteBatch.begin();
                    spriteBatch.draw(un.getTextureRegion(), un.getShapeX()[0], un.getShapeY()[0], un.getShapeX()[3] - un.getShapeX()[0], un.getShapeY()[2] - un.getShapeY()[3]);
                    spriteBatch.end();
                }
            }

            for (UnplayableArea un : world.getCurrentLevel().getUnplayableAreas()) {
                if (!un.getTexture().equals(AssetMan.manager.get(AssetMan.wall))) {
                    spriteBatch.begin();
                    spriteBatch.draw(un.getTextureRegion(), un.getShapeX()[0], un.getShapeY()[0], un.getShapeX()[3] - un.getShapeX()[0], un.getShapeY()[2] - un.getShapeY()[3]);
                    spriteBatch.end();
                }
            }
        }

    }

    private void update() {

        for (IGamePluginService p : gamePluginList) {
            if (p.getClass().toString().equals("class sdu.mmmi.softwareengineering.osgilevel.LevelPlugin") && !tempGamePluginList.contains(p)) {
                p.start(gameData, world);
                tempGamePluginList.add(p);
            }
        }
        for (IGamePluginService p : gamePluginList) {
            if (!p.getClass().toString().equals("class sdu.mmmi.softwareengineering.osgilevel.LevelPlugin") && !tempGamePluginList.contains(p)) {
                p.start(gameData, world);
                tempGamePluginList.add(p);
            }
        }

        // Update
        for (IEntityProcessingService entityProcessorService : entityProcessorList) {
            entityProcessorService.process(gameData, world);
        }

        // Post Update
        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessorList) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {
            sr.setColor(1, 1, 1, 1);

            sr.begin(ShapeRenderer.ShapeType.Line);

            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();

        }

        for (UnplayableArea un : world.getCurrentLevel().getUnplayableAreas()) {
            sr.setColor(1, 1, 1, 1);

            sr.begin(ShapeRenderer.ShapeType.Line);

            float[] shapex = un.getShapeX();
            float[] shapey = un.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    public void addEntityProcessingService(IEntityProcessingService eps) {
        this.entityProcessorList.add(eps);
    }

    public void removeEntityProcessingService(IEntityProcessingService eps) {
        this.entityProcessorList.remove(eps);
    }

    public void addPostEntityProcessingService(IPostEntityProcessingService eps) {
        postEntityProcessorList.add(eps);
    }

    public void removePostEntityProcessingService(IPostEntityProcessingService eps) {
        postEntityProcessorList.remove(eps);
    }

    public void addGamePluginService(IGamePluginService plugin) {
        this.gamePluginList.add(plugin);
//        plugin.start(gameData, world); 

    }

    public void removeGamePluginService(IGamePluginService plugin) {
        this.gamePluginList.remove(plugin);
        this.tempGamePluginList.remove(plugin);
        plugin.stop(gameData, world);
    }

    public static World getWorld() {
        return world;
    }

}
