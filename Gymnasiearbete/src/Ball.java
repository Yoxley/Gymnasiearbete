import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {
	double x, y, xVel, yVel, r;
	// Tyngdacceleration (m/s^2)
	double yAcc = -0.2;
	// Färg
	Color color = new Color(0);

	// Konstruktor
	public Ball(double x, double y, double r, double xVel, Color color) {
		// Position (m)
		this.x = x;
		this.y = y;

		// Hastighet (m/s)
		this.xVel = xVel;
		this.yVel = 0;

		// Radie (m)
		this.r = r;

		this.color = color;
	}

	// Uppdaterar bollens position
	public void updatePos(double updateLength) {
		if (y - r <= 0 && yVel < 0 || y + r >= Physics2D.FHEIGHT_M && yVel > 0) {
			yVel = -yVel;
		} else if (x - r <= 0 && xVel < 0 || x + r >= Physics2D.FWIDTH_M && xVel > 0) {
			xVel = -xVel;
		}

		yVel = yAcc * updateLength + yVel;
		x += xVel * updateLength;
		y += yVel * updateLength;
	}

	// Ritar bollen
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(ConvertPos.xmToxp(x - r), ConvertPos.ymToyp(y + r), ConvertPos.xmToxp(2 * r),
				ConvertPos.xmToxp(2 * r));
	}
}
