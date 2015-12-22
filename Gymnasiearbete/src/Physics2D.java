import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Physics2D extends Canvas {
	//Bufferstrategi för att ladda nästa frame innan den visas
	private BufferStrategy strategy;
	//Anger hur många "frames per second" spelet körs i
	private int fps;
	//
	private long lastFpsTime;
	//Boolean som bestämmer om spelet skall köras eller ej
	private boolean gameRunning = true;
	private int x, y;
	
	public Physics2D(){
		//Skapa en JFrame som ska innehålla programmet
		JFrame container = new JFrame("2D Physics Java");
		
		//Hämta innehållet i JFrame och sätt en upplösning på programmet
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(null);
		
		//Bestäm canvas-storleken och lägg det i JFrame
		setBounds(0,0,800,600);
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		//Gör fönstret synligt
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
	}
	
	public void gameLoop()
	{
	   long lastLoopTime = System.nanoTime();
	   final int TARGET_FPS = 60;
	   final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   

	   // keep looping round til the game ends
	   while (gameRunning)
	   {
	      // beräkna hur länge sen det var sen senaste uppdateringen, detta
	      // kommer användas för att beräkna hur mycket objektet ska röra sig
	      // denna loopen
	      long now = System.nanoTime();
	      long updateLength = now - lastLoopTime;
	      lastLoopTime = now;
	      double delta = updateLength / ((double)OPTIMAL_TIME);
	      
	      	// Get hold of a graphics context for the accelerated 
			// surface and blank it out
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.clearRect(0, 0, 800, 600);
			
			if(y == 600){
				y = 600;
			}else{
				move(delta);
			}
			
			g.fillOval(x, y, 50, 50);

			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			g.dispose();
			
			strategy.show();

	      // update the frame counter
	      lastFpsTime += updateLength;
	      fps++;
	      
	      // update our FPS counter if a second has passed since
	      // we last recorded
	      if (lastFpsTime >= 1000000000)
	      {
//	         System.out.println("(FPS: "+fps+")");
	         lastFpsTime = 0;
	         fps = 0;
	      }
	      
	      // update the game logic

 
	      // we want each frame to take 10 milliseconds, to do this
	      // we've recorded when we started the frame. We add 10 milliseconds
	      // to this and then factor in the current time to give 
	      // us our final value to wait for
	      // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
	      try{
	    	  Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
	      }catch(Exception e){};
	   }
	   
	}
	
	public void move(double delta) {
		int t = 0;
		t += (int)delta;
		x = 350;
		y += delta * 9.82;

	}
	
	public static void main(String args[]){
		Physics2D p2d = new Physics2D();
		p2d.gameLoop();
	}
}
