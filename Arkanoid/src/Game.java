import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {
	
	private Dimension gameField = new Dimension(400,300);
	private boolean isRunning = false;
	private boolean isPaused = false;
	
	private Player player;
	private Ball ball;
	
	public Game(Frame container) {
		container.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				
				if (!isRunning || isPaused) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) start();
				} else {
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.moveOnXAxis(20);
					if (e.getKeyCode() == KeyEvent.VK_LEFT) player.moveOnXAxis(-20);
				}
				
				
			}
			
		});
		player = new Player(this,(int)((gameField.getWidth()-Player.standardPlayerWidth)/2), gameField.height-Player.standardPlayerHeight, Player.standardPlayerWidth, Player.standardPlayerHeight); 
		ball = new Ball(this, gameField.width/2, gameField.height/2, Ball.standardBallRadius);
	}
	
	public void loseBall() {
		pause();
		ball.setPosition(gameField.width/2, gameField.height/2);
		player.setX((int)((gameField.getWidth()-Player.standardPlayerWidth)/2));
		player.setY(gameField.height-Player.standardPlayerHeight);
		repaint();
		
		
	}
	
	
	public void start() {
		isPaused = false;
		if (!isRunning) gameThread.start();
	}
	
	public void pause() {
		isPaused = true;
		

	}
	public void stop() {
		isRunning = false;
	}
	
	public Dimension getGameDimension(){
		return gameField;
	}
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public void setSize(Dimension size) {
		super.setSize(size);
		if (!isRunning) {
			gameField = new Dimension(size.width-200, size.height-200);
			ball.setPosition(gameField.width/2, gameField.height/2);
			player.setX((int)((gameField.getWidth()-Player.standardPlayerWidth)/2));
			player.setY(gameField.height-Player.standardPlayerHeight);
		}
	}
	
	private Thread gameThread = new Thread(new Runnable() {
		public void run() {
			isRunning = true;
			ball.setVector(10, 10);
			while (isRunning) {
				if (!isPaused) {
					ball.tick();
					
					repaint();
					try {
						Thread.sleep(30);
					} catch (Exception e) {}
				}
			}
		}
	});
	

	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.translate((getWidth()-gameField.width)/2, (getHeight()-gameField.height)/2 );
		
		g.setColor(new Color(255,255,255,100));
		g.fillRect(0, 0, gameField.width, gameField.height);
		
		ball.render(g);
		player.render(g);
		
		g.setColor(new Color(0,0,0));
		g.drawRect(0, 0, gameField.width, gameField.height);
		
	}

}
//problem z restartem gry!!!