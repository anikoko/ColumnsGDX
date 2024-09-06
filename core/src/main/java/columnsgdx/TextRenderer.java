package columnsgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextRenderer extends ApplicationAdapter  {
    private SpriteBatch batch;
    private BitmapFont font;
    private String score;
    private String level;
    private String pauseText;
   
    public SpriteBatch batchForPause;
    public BitmapFont fontForPause;
    
    public void setScore(String score) {
		this.score = score;
	}
    
    public void setLevel(String level) {
    	this.level = level;
    }

    public void setPauseText(String pausedText) {
    	this.pauseText = pausedText;
    }
    
    public void renderPauseMessage() {
    	batchForPause = new SpriteBatch();
    	fontForPause = new BitmapFont();
    	fontForPause.setColor(Color.RED);
    	fontForPause.getData().setScale(2f);    	
    	if (pauseText != null) {
    		batchForPause.begin();
    		fontForPause.draw(batchForPause, pauseText, 190, 300);
    		batchForPause.end();
    	}
    }
    
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);
		font.getData().setScale(1.5f);	
	}


	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (score != null) {
		    batch.begin();
		    font.draw(batch, score, 50, 300);
		    batch.end();
        }
		if (level != null) {
	        batch.begin();
	        font.draw(batch, "\n" + level, 50, 300);
	        batch.end();
		}
	}

	@Override
	public void dispose() {
		pauseText = null;
		batchForPause.dispose();
		fontForPause.dispose();
	}

}
