package Tanks2D;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static int width = 800;
	private static int height = width * 9 / 16;
	public static String title = "Tanks2D";
	
//	private Screen screen;
	private JFrame frame;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private boolean running = false;
	
	public Game(){
		
	}

	public static void main(String[] args) {
		Game game = new Game();
		
		game.frame = new JFrame();
		game.frame.setMinimumSize(new Dimension(width, height));
		game.frame.setTitle(title);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLayout(new BorderLayout());
		
		game.frame.add(game);
		game.frame.setResizable(false);
		game.frame.setVisible(true);
		game.frame.pack();
		
		game.start();
	}

	private synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	private synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		Timer timer = new Timer();
		while(running){
			
			timer.updateTime();
			while(timer.getDelta() >= 1){
				updateGame();
				timer.updateTickCounter();
			}
			timer.frames++;
			renderGame();
			timer.displayFrames();
			
		}
	}
	
	public void updateGame(){
		
	}
	public void renderGame(){
		BufferStrategy bs = getBufferStrategy();
		if(bs ==null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, width, height);
		g.dispose();
		bs.show();
	}

}
