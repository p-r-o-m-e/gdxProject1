package com.gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Drop extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    private Screen Gscreen;
    private boolean gotscreen = false;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); // important!
    }
    public void disposeScreen(Screen Gscreen) {
        this.Gscreen = Gscreen;
        this.gotscreen = true;
    }
    public void dispose() {
        batch.dispose();
        font.dispose();
        if (gotscreen = true )
            screen.dispose();

    }

}