package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import org.w3c.dom.Text;
import com.badlogic.gdx.math.Rectangle;

public class Game extends ApplicationAdapter {

	public Texture dropIMG;
	public Texture bucketIMG;
	public Sound dropSound;
	public Music rainMusic;

	public SpriteBatch batch;
	public OrthographicCamera camera;

	private Rectangle bucket;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bucket = new Rectangle();
		bucket.x = 800/2 - 64/2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		//LOAD IMAGES FOR DROPLET & BUCKET.
		dropIMG = new Texture(Gdx.files.internal("drop.png"));
		bucketIMG = new Texture(Gdx.files.internal("bucket.png"));
		//LOAD SOUND
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		//set the playback of the BGM immediately.
		rainMusic.setLooping(true);
		rainMusic.play();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bucketIMG, bucket.x, bucket.y);
		batch.end();

		camera.update();
	}
	
	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
	}
}
