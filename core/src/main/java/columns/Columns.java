package columns;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Columns {

	static final int TimeShift = 250;
	static final int MinTimeShift = 200;

	
//	private ScheduledExecutorService timer;
	
	//-----------------------------< Applet methods implementations >--------------------

//	
//	@Override
//	public void init() {
//		model.initModel();
//		view.initView(getGraphics());
//		model.initMatrixes();
//		model.initMembers();
//		model.createNewFigure();
//		requestFocus();
//	}
//
//	@Override
//	public void start() {
//		view.graphics.setColor(Color.black);
//
//		// setBackground (new Color(180,180,180));
//
//		timer = Executors.newSingleThreadScheduledExecutor();
//		timer.scheduleAtFixedRate(model::trySlideDown, 1, 1, TimeUnit.SECONDS);
//	}
//
//	@Override
//	public void paint(Graphics g) {
//		// ShowHelp(g);
//
//		graphics.setColor(Color.black);
//
//		view.showLevel(model.level);
//		view.showScore(model.Score);
//		view.drawField(model.newField);
//		view.drawFigure(model.Fig);
//		requestFocus();
//	}
//
//	@Override
//	public void stop() {
//		timer.shutdown();
//	}
//
//	@Override
//	public boolean keyDown(Event e, int k) {
//		processUserActions(e.key);
//		return true;
//	}
//
//
//	public boolean lostFocus(Event e, Object w) {
//		processUserActions('P');
//		return true;
//	}

//
//
//	private void processUserActions(int keyPressedCode) {
//			switch (keyPressedCode) {
//			case Event.LEFT:
//				model.tryMoveLeft();
//				break;
//			case Event.RIGHT:
//				model.tryMoveRight();
//				break;
//			case Event.UP:
//				model.rotateUp();
//				break;
//			case Event.DOWN:
//				model.rotateDown();
//				break;
//			case ' ':
//				model.dropFigure(model.Fig);
//				break;

				// TODO:  HomeWork:  move to a separate thread
//			case 'P':
//			case 'p':
//				while (!keyPressed) {
//					int oldX3 = model.Figraphics.x;
//					view.hideFigure(oldX3, oldY);
//					Delay(500);
//					view.drawFigure(model.Fig);
//					Delay(500);
//				}
//				lastTimeFigureMoved = System.currentTimeMillis();
//				break;
				
				// TODO:  HomeWork
//			case '-':
//				if (model.level > 0)
//					model.descreaseLevel();
//				model.removedCellsCounter = 0;
//				view.showLevel(model.level);
//				break;
//			case '+':
//				if (model.level < Model.MaxLevel)
//					model.increaseLevel();
//				model.removedCellsCounter = 0;
//				view.showLevel(model.level);
//				break;
//		}
//	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Columns");

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 500));

		frame.add(panel);

		frame.pack();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setVisible(true);
		
		Graphics2D graphics = (Graphics2D) panel.getGraphics();
		
		Model model = new Model();
		View view = new View(new Graphics() {

			@Override
			public void setColourBlack() {
				graphics.setColor(Color.black);
				
			}

			@Override
			public void drawBox(int x, int y, int c) {
				   if (c==0) {
				        graphics.setColor(Color.black);
				        graphics.fillRect(View.LeftBorder+x*View.BOX_SIZE-View.BOX_SIZE, View.TopBorder+y*View.BOX_SIZE-View.BOX_SIZE, View.BOX_SIZE, View.BOX_SIZE);
				        graphics.drawRect(View.LeftBorder+x*View.BOX_SIZE-View.BOX_SIZE, View.TopBorder+y*View.BOX_SIZE-View.BOX_SIZE, View.BOX_SIZE, View.BOX_SIZE);
				    }
				    else if (c==8) {
				        graphics.setColor(Color.white);
				        graphics.drawRect(View.LeftBorder+x*View.BOX_SIZE-View.BOX_SIZE+1, View.TopBorder+y*View.BOX_SIZE-View.BOX_SIZE+1, View.BOX_SIZE-2, View.BOX_SIZE-2);
				        graphics.drawRect(View.LeftBorder+x*View.BOX_SIZE-View.BOX_SIZE+2, View.TopBorder+y*View.BOX_SIZE-View.BOX_SIZE+2, View.BOX_SIZE-4, View.BOX_SIZE-4);
				        graphics.setColor(Color.black);
				        graphics.fillRect(View.LeftBorder+x*View.BOX_SIZE-View.BOX_SIZE+3, View.TopBorder+y*View.BOX_SIZE-View.BOX_SIZE+3, View.BOX_SIZE-6, View.BOX_SIZE-6);
				    }
				    else {
				        graphics.setColor(View.COLORS[c]);
				        graphics.fillRect(View.LeftBorder+x*View.BOX_SIZE-View.BOX_SIZE, View.TopBorder+y*View.BOX_SIZE-View.BOX_SIZE, View.BOX_SIZE, View.BOX_SIZE);
				        graphics.setColor(Color.black);
				        graphics.drawRect(View.LeftBorder+x*View.BOX_SIZE-View.BOX_SIZE, View.TopBorder+y*View.BOX_SIZE-View.BOX_SIZE, View.BOX_SIZE, View.BOX_SIZE);
				    }
				    //		graphics.setColor (Color.black);
				
			}

			@Override
			public void showHelp() {
				graphics.setColor(Color.black);
				
				graphics.drawString(" Keys available:", 200 - View.LeftBorder, 102);
				graphics.drawString("Roll Box Up:     ", 200 - View.LeftBorder, 118);
				graphics.drawString("Roll Box Down:   ", 200 - View.LeftBorder, 128);
				graphics.drawString("Figure Left:     ", 200 - View.LeftBorder, 138);
				graphics.drawString("Figure Right:    ", 200 - View.LeftBorder, 148);
				graphics.drawString("Level High/Low: +/-", 200 - View.LeftBorder, 158);
				graphics.drawString("Drop Figure:   space", 200 - View.LeftBorder, 168);
				graphics.drawString("Pause:           P", 200 - View.LeftBorder, 180);
				graphics.drawString("Quit:     Esc or Q", 200 - View.LeftBorder, 190);
				
			}

			@Override
			public void showLevel(int level) {
				graphics.setColor(Color.black);
				graphics.clearRect(View.LeftBorder + 100, 390, 100, 20);
				graphics.drawString("Level: " + level, View.LeftBorder + 100, 400);
				
			}

			@Override
			public void showScore(int score) {
				graphics.setColor(Color.black);
				graphics.clearRect(View.LeftBorder, 390, 100, 20);
				graphics.drawString("Score: " + score, View.LeftBorder, 400);
				
			}
				
			
			
		});
		Controller controller = new Controller(model, view);
		
		controller.initController();
		
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT: {
					controller.tryMoveLeft();
					break;
				}
				case KeyEvent.VK_RIGHT: {
					controller.tryMoveRight();
					break;
				}
				case KeyEvent.VK_UP:
					controller.rotateUp();
					break;
				case KeyEvent.VK_DOWN:
					controller.rotateDown();
					break;
				case KeyEvent.VK_SPACE:
					controller.dropFigure(model.Fig);
					break;
				case KeyEvent.VK_MINUS:
					controller.decreaseLevel();
					break;
				case KeyEvent.VK_EQUALS:
					controller.increaseLevel();
					break;
				
				}
				
			}

		});
		
		ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
		timer.scheduleAtFixedRate(controller::trySlideDown, 1, 1, TimeUnit.SECONDS);
		
	}

}