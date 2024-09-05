package columnsgdx;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import columns.Controller;
import columns.Figure;
import columns.Model;
import columns.View;
import columns.Graphics;

public class ColumnsStage extends Stage implements Graphics {
	
	static Color[] COLORS = new Color[] {Color.BLACK,Color.CYAN,Color.BLUE,Color.RED,Color.GREEN,
		    Color.YELLOW,Color.PINK,Color.MAGENTA,Color.WHITE};

	private ShapeRenderer shape;
	private View view;
	private Model model;
	private Controller controller;
	private TextRenderer textRenderer;
	
	final List<ScreenListener> screenListeners = new ArrayList<>();


	public ColumnsStage() {
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(true);
		setViewport(new ScreenViewport(camera));
		shape = new ShapeRenderer();
		Gdx.graphics.setWindowedMode(400, 700);
	}

	public void addScreenListener(ScreenListener screenListener) {
		screenListeners.add(screenListener);
	}
	
	public void init() {
		model = new Model();
		view = new View(this);

		controller = new Controller(model, view);
		textRenderer = new TextRenderer();
		textRenderer.create();
		
		controller.initController();

		model.addListener(controller);
		

		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				controller.trySlideDown();
			}
		}, 1.0f, 1.0f);

		Gdx.input.setInputProcessor(this);

		addListener(new InputListener() {

			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				switch (keycode) {
				case Input.Keys.LEFT:
					controller.tryMoveLeft();
					break;
				case Input.Keys.RIGHT:
					controller.tryMoveRight();
					break;
				case Input.Keys.UP:
					controller.rotateUp();
					break;
				case Input.Keys.DOWN:
					controller.rotateDown();
					break;
				case Input.Keys.SPACE:
					controller.dropFigure(model.Fig);
					break;
				case Input.Keys.MINUS:
					controller.decreaseLevel();
					break;
				case Input.Keys.EQUALS:
					controller.increaseLevel();
					break;
				case Input.Keys.R: {
					controller.restart();
					for (ScreenListener screenListener : screenListeners) {
						screenListener.restart();
					}
					break;
				}
				case Input.Keys.Q:
					System.exit(0);
					break;
				}
				return true;
			}

		});

	}
	
	@Override
	public void draw() {
		textRenderer.render();
		view.drawModel(model);
		if (controller.gameOver()) {
			for (ScreenListener screenListener : screenListeners) {
				screenListener.gameOver(model.score, model.level);
			}
			return;
		}
	}

	@Override
	public void setColourBlack() {
		shape.setColor(Color.BLACK);		
	}

	@Override
	public void drawBox(int x, int y, int c) {
		  Camera camera = getViewport().getCamera();
		    camera.update();

		    shape.setProjectionMatrix(camera.combined);
		    
		    shape.begin(ShapeType.Filled);
		    
		    if (c == 0) {
		        shape.setColor(Color.DARK_GRAY);
		    } else if (c == 8) {
		        shape.setColor(Color.WHITE);
		    } else {
		        shape.setColor(COLORS[c]);
		    }

		    shape.rect(View.LeftBorder + x * View.BOX_SIZE - View.BOX_SIZE,
		               View.TopBorder + y * View.BOX_SIZE - View.BOX_SIZE,
		               View.BOX_SIZE, View.BOX_SIZE);
		    if (c == 8) {
		        shape.setColor(Color.WHITE);
		        shape.rect(View.LeftBorder + x * View.BOX_SIZE - View.BOX_SIZE + 1,
		                   View.TopBorder + y * View.BOX_SIZE - View.BOX_SIZE + 1,
		                   View.BOX_SIZE - 2, View.BOX_SIZE - 2);

		        shape.rect(View.LeftBorder + x * View.BOX_SIZE - View.BOX_SIZE + 2,
		                   View.TopBorder + y * View.BOX_SIZE - View.BOX_SIZE + 2,
		                   View.BOX_SIZE - 4, View.BOX_SIZE - 4);
		    }
		    shape.end();
		    if (c != 0 && c != 8) {
		        shape.begin(ShapeType.Line);
		        shape.setColor(Color.BLACK);
		        shape.rect(View.LeftBorder + x * View.BOX_SIZE - View.BOX_SIZE,
		                   View.TopBorder + y * View.BOX_SIZE - View.BOX_SIZE,
		                   View.BOX_SIZE, View.BOX_SIZE);
		        shape.end();
		    }
	}

	@Override
	public void showHelp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showLevel(int level) {
		textRenderer.setLevel("Level: " + level);
	}

	@Override
	public void showScore(int score) {
		textRenderer.setScore("Score: " + score);	
	}
	
	
}