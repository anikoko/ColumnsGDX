package columns;

public class Controller implements ModelListener, GameEventsListener{
	
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		model.addListener(this);
		this.view = view;
	}
	
	public void initController() {
		model.initModel();
		model.initMatrixes();
		model.initMembers();
		model.createNewFigure();
		view.showLevel(model.level);
		view.showScore(model.score);
	}
	
	
	//-----------------------------< ModelListener implementations >--------------------
	
		@Override
		public void foundNeighboursAt(int a, int b, int c, int d, int i, int j) {
			view.highlightNeighbours(a, b, c, d, i, j);
		}

		@Override
		public void figureMovedFrom(int oldX, int oldY) {
			view.moveAndDrawFigure(model.Fig, oldX, oldY);
		}

		@Override
		public void figureUpdated(Figure fig) {
			view.drawFigure(fig);
		}

		@Override
		public void fieldUpdated(int[][] newField) {
			view.drawField(newField);
		}

		@Override
		public void scoreHasChanged(int score) {
			view.showScore(score);
		}

		@Override
		public void levelHasChanged(int level) {
			view.showLevel(level);
		}
		
		@Override
		public boolean gameOver() {
			if (model.gameOver) {
				return true;
			}
			return false;

		}

		//-----------------------------< GameEventsListener implementations >--------------------


		@Override
		public void tryMoveLeft() {
			model.tryMoveLeft();
			
		}


		@Override
		public void tryMoveRight() {
			model.tryMoveRight();
			
		}


		@Override
		public void rotateUp() {
			model.rotateUp();
			
		}


		@Override
		public void rotateDown() {
			model.rotateDown();
			
		}


		@Override
		public void dropFigure(Figure fig) {
			model.dropFigure(fig);
			
		}


		@Override
		public void trySlideDown() {
			model.trySlideDown();
		}

		@Override
		public void decreaseLevel() {
			model.decreaseLevel();
			
		}

		@Override
		public void increaseLevel() {
			model.increaseLevel();
			
		}

		public void restart() {
			initController();
			
		}
		
		

}
