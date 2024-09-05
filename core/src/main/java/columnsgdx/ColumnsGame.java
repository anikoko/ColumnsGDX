package columnsgdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ColumnsGame extends Game implements ScreenListener {
	
	public SpriteBatch batch;
	public ColumnsScreen columnsScreen;

	@Override
	public void create() {
		batch = new SpriteBatch();
		columnsScreen = new ColumnsScreen();
		setScreen(columnsScreen);
		columnsScreen._stage.addScreenListener(this);
	}

	@Override
	public void gameOver(int score, int level) {
		setScreen(new GameOverScreen(this, score, level));
		
	}

	@Override
	public void restart() {
		columnsScreen.dispose(); 
	    columnsScreen = new ColumnsScreen(); 
	    setScreen(columnsScreen);
	    columnsScreen._stage.addScreenListener(this);
		
	}


}