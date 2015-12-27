import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {
	double x, y, xVel, yVel, r;

	// Konstruktor
	public Ball(double x, double y, double r) {
		// Position (m)
		this.x = x;
		this.y = y;

		// Hastighet (m/s)
		this.xVel = 0;
		this.yVel = 0;

		// Radie (m)
		this.r = r;
	}

	// Uppdaterar bollens position
	public void updatePos(double updateLength) {
		if (y - r <= 0 && yVel < 0 || y + r >= Physics2D.FHEIGHT_M && yVel > 0) {
			yVel = -yVel;
		} else if (x - r <= 0 && xVel < 0 || x + r >= Physics2D.FWIDTH_M && xVel > 0) {
			xVel = -xVel;
		}

		yVel = -0.2 * updateLength + yVel;

		x += xVel * updateLength;
		y += yVel * updateLength;
	}

	// Konverterar en x-koordinat från meter till pixlar
	public static int convertX(double xm) {
		double xp = (Physics2D.FWIDTH_PX / Physics2D.FWIDTH_M) * xm;
		return (int) xp;
	}

	// Konverterar en y-koordinat från meter till pixlar
	public static int convertY(double ym) {
		double yp = -((ym - Physics2D.FHEIGHT_M) / Physics2D.FHEIGHT_M) * Physics2D.FHEIGHT_PX;
		return (int) yp;
	}

	// Ritar bollen
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillOval(convertX(x - r), convertY(y + r), convertX(2 * r), convertX(2 * r));
		g.setColor(Color.BLACK);
		g.drawString("x-pos (m): " + x, 20, 500);
		g.drawString("y-pos (m): " + y, 20, 525);
		g.drawString("xVel (m/s): " + xVel, 20, 550);
		g.drawString("yVel (m/s): " + yVel, 20, 575);
	}
}
