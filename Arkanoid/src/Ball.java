import java.awt.*;

public class Ball {
	public static int standardBallRadius = 10;
	private Game instance;
	private Dimension vector = new Dimension(0,0); 
	private Point pos = new Point(0,0);
	private int radius;
	
	public Ball(Game inst, int x, int y, int radius) {
		instance = inst;
		pos = new Point(x,y);
		this.radius = radius;
	}
	
	public void setVector(int XMovement, int YMovement) {
		vector = new Dimension(XMovement, YMovement);
	}
	
	public Point getPosition() {
		return pos;
	}
	public void setPosition(int x, int y) {
		pos = new Point(x, y);

	}
	
	public void tick() {
		if (pos.x-radius <= 0 && vector.width<0) vector.width = -vector.width;
		if (pos.x+radius >= instance.getGameDimension().width && vector.width>0) vector.width = -vector.width;
		if (pos.y-radius <= 0 && vector.height<0) vector.height = -vector.height;
		if (pos.y+radius >= instance.getGameDimension().height && vector.height>0) instance.loseBall();
		
		if (instance.getPlayer() != null) {
			if (instance.getPlayer().collidesWith(new Rectangle(pos.x-radius, pos.y-radius, radius*2, radius*2))) {
				vector.height = -vector.height;
			}
		}
		
		pos.move(pos.x+vector.width, pos.y+vector.height);
		
		
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(new Color(0,0,0));
		g.fillOval(pos.x-radius, pos.y-radius, radius*2, radius*2);
	
	}

}

