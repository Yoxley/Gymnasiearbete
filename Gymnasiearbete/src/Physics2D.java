import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Physics2D extends Canvas {
	public BufferStrategy strategy;
	public int fps;
	public long lastFpsTime;
	public boolean gameRunning = true;
	public static final int FWIDTH_PX = 800;
	public static final int FHEIGHT_PX = 600;
	public static final double FWIDTH_M = 0.2;
	public static final double FHEIGHT_M = 0.15;

	// Skapar en boll enhet: (m)
	public Ball b = new Ball(FWIDTH_M / 2, 0.10, 0.007);

	public Physics2D() {
		// Skapa en JFrame som ska innehålla programmet
		JFrame container = new JFrame("2D Physics Java");

		// Hämta innehållet i JFrame och sätt en upplösning på programmet
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(FWIDTH_PX, FHEIGHT_PX));
		panel.setLayout(null);

		// Bestäm canvas-storleken och lägg det i JFrame
		setBounds(0, 0, FWIDTH_PX, FHEIGHT_PX);
		panel.add(this);
		setIgnoreRepaint(true);

		// Gör fönstret synligt
		container.pack();
		container.setResizable(false);
		container.setVisible(true);

		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
	}

	// Program-loop
	public void run() {
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

		// keep looping round til the game ends
		while (gameRunning) {
			long now = System.nanoTime();
			double updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / (OPTIMAL_TIME);

			// Get hold of a graphics context for the accelerated
			// surface and blank it out
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

			// Rensar fönstret
			g.clearRect(0, 0, FWIDTH_PX, FHEIGHT_PX);
			g.drawRect(0, 0, FWIDTH_PX - 1, FHEIGHT_PX - 1);

			// Uppdaterar bollens position och ritar den
			b.updatePos(updateLength / 1000000000);

			b.draw(g);

			g.dispose();
			strategy.show();

			// update the frame counter
			lastFpsTime += updateLength;
			fps++;

			// update our FPS counter if a second has passed since
			// we last recorded
			if (lastFpsTime >= 1000000000) {
				lastFpsTime = 0;
				fps = 0;
			}

			// we want each frame to take 10 milliseconds, to do this
			// we've recorded when we started the frame. We add 10 milliseconds
			// to this and then factor in the current time to give
			// us our final value to wait for
			// remember this is in ms, whereas our lastLoopTime etc. vars are in
			// ns.
			try {
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
			} catch (Exception e) {
			}
			;
		}

	}

	public static void main(String args[]) {
		Physics2D p2d = new Physics2D();
		p2d.run();
	}
}
