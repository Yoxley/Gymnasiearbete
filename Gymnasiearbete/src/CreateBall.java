import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class CreateBall implements MouseListener {
	ArrayList<Ball> balls;
	Random rand = new Random();

	public CreateBall(ArrayList<Ball> balls) {
		// Hämtar ArrayListen balls som för att den ska kunnas användas i
		// klassen
		this.balls = balls;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Skapar en slumpgenererad färg
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randColor = new Color(r, g, b);

		// Skapar en slumpad radie mellan 3-13mm
		double randDouble = rand.nextDouble();
		double randRadius = (randDouble / 100) + 0.003;

		// Slumpad hastighet
		double randDouble2 = rand.nextDouble();
		double randVel = (randDouble2 / 10) + 0.01;

		// Lägger till en ny boll i arraylisten
		balls.add(new Ball(ConvertPos.xpToxm(e.getX()), ConvertPos.ypToym(e.getY()), randRadius, randVel, randColor));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
