import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {
	
	private Dimension gameField = new Dimension(400,300); 
	private Player player;
	
	public Game(Frame container) {
		container.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.moveOnXAxis(10);
				if (e.getKeyCode() == KeyEvent.VK_LEFT) player.moveOnXAxis(-10);
				repaint();
				
			}
			
		});
		player = new Player(this,(int)(gameField.getWidth()-Player.standardPlayerWidth)/2, gameField.height-Player.standardPlayerHeight, Player.standardPlayerWidth, Player.standardPlayerHeight); 
		
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
		gameField = new Dimension(size.width-200, size.height-200);
		player.setY(gameField.height-Player.standardPlayerHeight);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.translate((getWidth()-gameField.width)/2, (getHeight()-gameField.height)/2 );
		
		g.setColor(new Color(255,255,255,100));
		g.fillRect(0, 0, gameField.width, gameField.height);
		
		player.render(g);
		
		g.setColor(new Color(0,0,0));
		g.drawRect(0, 0, gameField.width, gameField.height);
		
	}

}