package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    final Drop Game;
    OrthographicCamera camera;

    public MainMenuScreen(final Drop Game) {

        this.Game = Game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

    }

    public void show () {

    }
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        Game.batch.setProjectionMatrix(camera.combined);

        Game.batch.begin();
        Game.font.draw(Game.batch, "Welcome to Drop!!! ", 100, 150);
        Game.font.draw(Game.batch, "Tap anywhere to begin!", 100, 100);
        Game.batch.end();

        if(Gdx.input.isTouched()) {
            dispose();
            Game.setScreen(new gdxGame(Game));
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
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
