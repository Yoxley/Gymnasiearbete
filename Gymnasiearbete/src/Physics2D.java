import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Physics2D extends Canvas {
	public BufferStrategy strategy;
	public int fps;
	public long lastFpsTime;
	public boolean gameRunning = true;

	// Fönstrets storlek i pixlar och meter
	public static final int FWIDTH_PX = 800;
	public static final int FHEIGHT_PX = 600;
	public static final double FWIDTH_M = 0.18;
	public static final double FHEIGHT_M = 0.135;

	// ArrayList som innehåller alla bollar
	ArrayList<Ball> balls = new ArrayList<Ball>();
	CreateBall cb = new CreateBall(balls);

	public Physics2D() {
		this.addMouseListener(cb);

		// Skapar en boll med argumenten (x-pos, y-pos, x-hastighet radie, färg)
		balls.add(new Ball(FWIDTH_M / 2, 0.1, 0.007, 0.05, Color.RED));
		balls.add(new Ball(0.16, 0.07, 0.01, 0.05, Color.BLUE));

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

		// Skapar en bufferstrategi som renderar nästa frame i förväg
		createBufferStrategy(2);
		strategy = getBufferStrategy();
	}

	// Loopa programmet
	public void run() {
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 10;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

		while (gameRunning) {
			long now = System.nanoTime();
			double updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / (OPTIMAL_TIME);
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

			// Rensar fönstret
			g.clearRect(0, 0, FWIDTH_PX, FHEIGHT_PX);
			g.drawRect(0, 0, FWIDTH_PX - 1, FHEIGHT_PX - 1);

			// Uppdaterar bollarnas positioner och ritar dom
			for (int i = 0; i < balls.size(); i++) {
				balls.get(i).updatePos(updateLength / 1000000000);
				balls.get(i).draw(g);
			}
			g.dispose();
			strategy.show();

			// Uppdaterar FPS räknaren
			lastFpsTime += updateLength;
			fps++;

			// Uppdaterar FPS räknaren om en sekund har passerat
			if (lastFpsTime >= 1000000000) {
				lastFpsTime = 0;
				fps = 0;
			}

			// Gör så att varje frame tar 10 millisekunder
			try {
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
			} catch (Exception e) {
			}
			;
		}

	}

	public static void main(String args[]) {
		System.out.println(ConvertPos.xpToxm(100));
		Physics2D p2d = new Physics2D();
		p2d.run();
	}
}
