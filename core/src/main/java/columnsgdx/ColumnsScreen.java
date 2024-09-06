package columnsgdx;

import com.badlogic.gdx.ScreenAdapter;

public class ColumnsScreen extends ScreenAdapter {
	
	ColumnsStage _stage;

	@Override
	public void show() {
		_stage = new ColumnsStage();
		_stage.init();
	}

	@Override
	public void render(float delta) {
		if (!_stage.paused) {
			_stage.act(delta);
		}
		_stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		_stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void pause() {
		super.pause();
	}


}
