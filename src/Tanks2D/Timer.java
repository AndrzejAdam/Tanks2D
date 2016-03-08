package Tanks2D;

	
public class Timer {
	private long timeInNano = System.nanoTime();
	private long timeInMili = System.currentTimeMillis();
	private final double nsPerUpdate = 1000000000 / 60.0;
	private double delta = 0;
	public int frames = 0;
	private int ticks = 0;
	private long now = 0;
	
	public double getDelta(){
		delta += (now - timeInNano) / nsPerUpdate;
		timeInNano = now;
		return delta;
	}

	public void updateTickCounter() {
		ticks++;
		delta--;
		
	}

	public void displayFrames() {
		if(System.currentTimeMillis() - timeInMili > 1000){
			timeInMili += 1000;
			System.out.println(ticks + " ups, " + frames + " fps");
			ticks = 0;
			frames = 0;
		}
	}
	public void updateTime(){
		now = System.nanoTime();
	}
}
