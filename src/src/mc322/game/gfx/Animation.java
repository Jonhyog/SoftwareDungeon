package mc322.game.gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation implements IAnimation {
	private ArrayList<Sprite> frames;
	private ArrayList<Integer> times;
	private int current, n, ticks;
	private boolean flip = false, finished = false;
	
	public Animation(){
		frames = new ArrayList<Sprite>();
		times = new ArrayList<Integer>();
		ticks = 0;
		current = 0;
		n = 0;
	}
	
	private BufferedImage transform(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage newImg = new BufferedImage(width, height, img.getColorModel().getTransparency());
        Graphics2D g = newImg.createGraphics();
        g.drawImage(img, 0, 0, width, height, width, 0, 0, height, null);
        g.dispose();
        
		return newImg;
	}
	
	private Sprite flipSprite(Sprite sprite) {
		Sprite flipped = new Sprite();
		
		flipped.setSizeX(sprite.getSizeX());
		flipped.setSizeY(sprite.getSizeY());
		flipped.setName(sprite.getName());
		flipped.setSolid(sprite.isSolid());
        flipped.setTexture(transform(sprite.getTexture()));
        
        return flipped;
	}
	
	private void nextFrame() {
		ticks = 0;
		current++;
		if (current == n) {
			finished = true;
			current = 0;
		}
	}
	
	public void addFrame(Sprite frame, int time) {
		frames.add(frame);
		times.add(time);
		n++;
	}
	
	public Sprite getCurrentFrame() {
		if (flip)
			return flipSprite(frames.get(current));
		return frames.get(current);
	}
	
	public void flipSprites(boolean flip) {
		this.flip = flip;
	}
	
	public boolean finishedLoop() {
		return finished;
	}
	
	public void tick() {
		ticks++;
		finished = false;
		if (ticks == times.get(current))
			nextFrame();
		if (current == n) {
			finished = true;
		}
	}
}
