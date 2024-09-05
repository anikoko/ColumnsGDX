package columns;

import java.awt.Color;
import java.awt.Font;

public class View {
	
	final static public Color[] COLORS = new Color[] {Color.black,Color.cyan,Color.blue,Color.red,Color.green,
		    Color.yellow,Color.pink,Color.magenta,Color.white};
	
	public static final int TopBorder=2;
	public static final int LeftBorder=2;
	public static final int BOX_SIZE=25;
	public static final int ORIGIN=50;
	

	public Font font;
	public Graphics gr;
	
	public View(Graphics gr) {
		this.gr = gr;
        gr.setColourBlack();
	}
	
	
	public void drawModel(Model model) {
		drawField(model.newField);
		drawFigure(model.Fig);
	}

	
	void drawField(int[][] field) {
		for (int i=1; i<=Model.Depth; i++) {
	        for (int j=1; j<=Model.Width; j++) {
	            drawBox(j, i, field[j][i]);
	        }
	    }
	}

	void drawBox(int x, int y, int c) {
		gr.drawBox(x, y, c);
	}

	void drawFigure(Figure f) {
		if (f == null)
			return;
		int[] c = f.getCells();
	    drawBox(f.x, f.y,c[0]);
	    drawBox(f.x, f.y+1,c[1]);
	    drawBox(f.x, f.y+2,c[2]);
	}

	void hideFigure(int x, int y) {
		drawBox(x, y, 0);
		drawBox(x, y+1, 0);
		drawBox(x, y+2, 0);
	}


	void showHelp() {
		gr.showHelp();
	}


	void showLevel(int level) {
		gr.showLevel(level);
	}


	void showScore(int score) {
		gr.showScore(score);
	}


	void moveAndDrawFigure(Figure fig, int oldX, int oldY) {
		hideFigure(oldX, oldY);
		drawFigure(fig);
	}


	void highlightNeighbours(int a, int b, int c, int d, int i, int j) {
		drawBox(a, b, 8);
		drawBox(j, i, 8);
		drawBox(c, d, 8);
	}

}