package columns;

public interface GameEventsListener {
	
	void tryMoveLeft();
	void tryMoveRight();
	void rotateUp();
	void rotateDown();
	void dropFigure(Figure fig);
	void trySlideDown();
	void decreaseLevel();
	void increaseLevel();
	

}
