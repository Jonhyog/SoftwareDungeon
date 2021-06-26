package mc322.game.gfx;

import java.util.ArrayList;

public class Animation implements IAnimation {
	private ArrayList<Sprite> frames;
	private ArrayList<Integer> times;
	private int current, n, ticks;
	
	public Animation(){
		frames = new ArrayList<Sprite>();
		times = new ArrayList<Integer>();
		ticks = 0;
		current = 0;
		n = 0;
	}
	
	private void nextFrame() {
		ticks = 0;
		current++;
		if (current == n)
			current = 0;
	}
	
	public void addFrame(Sprite frame, int time) {
		frames.add(frame);
		times.add(time);
		n++;
	}
	
	public Sprite getCurrentFrame() {
		return frames.get(current);
	}
	
	public void tick() {
		ticks++;
		if (ticks == times.get(current))
			nextFrame();
	}
}
