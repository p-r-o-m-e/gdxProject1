package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import org.w3c.dom.Text;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Game extends ApplicationAdapter {

	public Texture dropIMG;
	public Texture bucketIMG;
	public Sound dropSound;
	public Music rainMusic;

	public SpriteBatch batch;
	public OrthographicCamera camera;

	private Rectangle bucket;
	private Array<Rectangle> raindrops;
	private long lastdropTime;

	//set positions for droplet and add it to list
	private void spawnRaindrop()
	{
		Rectangle Raindrop = new Rectangle();
		Raindrop.x = MathUtils.random(0, 800 - 64);
		Raindrop.y = 400;
		Raindrop.height = 64;
		Raindrop.width = 64;
		raindrops.add(Raindrop);
		// get time in nanoseconds.
		lastdropTime = TimeUtils.nanoTime();

	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		bucket = new Rectangle();
		raindrops = new Array<Rectangle>();
		bucket.x = 800/2 - 64/2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;
		spawnRaindrop();

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

		 //draw bucket and droplet
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bucketIMG, bucket.x, bucket.y);
		for (Rectangle raindrop: raindrops) {
			batch.draw(dropIMG, raindrop.x, raindrop.y);
		}
		batch.end();

		//mouse input for bucket
		if(Gdx.input.isTouched())
		{
			Vector3 touchpos = new Vector3();
			touchpos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchpos);
			bucket.x = (touchpos.x - 64/2);
		}
		//keyboard input for bucket
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
			if (bucket.x < 0)
				bucket.x = 0;
			else
				bucket.x -= 200 *  Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			if(bucket.x > 800-64)
				bucket.x  = 800-64;
			else
				bucket.x += 200 * Gdx.graphics.getDeltaTime();
		if(TimeUtils.nanoTime() - lastdropTime > Math.pow(10,10))
			spawnRaindrop();
		for(Iterator<Rectangle> iter = raindrops.iterator();iter.hasNext();)
		{
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if(raindrop.y + 64 < 0)
				iter.remove();
			if(raindrop.overlaps(bucket))
			{
				iter.remove();
				dropSound.play();
			}
		}

		camera.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		dropIMG.dispose();
		bucketIMG.dispose();
		dropSound.dispose();
		rainMusic.dispose();
	}
}
