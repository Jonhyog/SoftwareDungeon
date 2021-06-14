package mc322.game.util;

public class Clock {
	private int fps, ticks;
	private double timePerTick, delta;
	private long now, lastTime, timer;
	
	public Clock(int fps) {
		setFPS(fps);
	}
	
	private void setTimePerTick() {
		this.timePerTick = 1000000000 / fps;
	}
	
	private void setDelta() {
		this.delta = 0;
	}
	
	public void setFPS(int fps) {
		this.fps = fps;
		setTimePerTick();
		setDelta();
	}
	
	public void start() {
		this.now = System.nanoTime();
		this.lastTime = this.now;
	}
	
	public boolean canRun() {
		this.now = System.nanoTime();
		delta += (this.now - this.lastTime) / this.timePerTick;
		timer += this.now - this.lastTime;
		this.lastTime = this.now;
		
		if (delta >= 1) {
			ticks++;
			delta--;
			return true;
		}
		
		return false;
	}
	
	public void showCurrentFPS() {
		if (timer >= 1000000000) {
			System.out.println("FPS: " + ticks);
			ticks = 0;
			timer = 0;
		}
	}
}
