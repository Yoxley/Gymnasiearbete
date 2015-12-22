import java.awt.Graphics;

public class Object {
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	public void move(long delta) {
		x = 0;
		y += (delta * dy) / 1000;
	}
	
	public int getX(){
		return (int) x;
	}
	
	public int getY(){
		return (int) y;
	}
}
