package mc322.game.gfx;

public interface IAnimation {
	public void addFrame(Sprite frame, int time);
	public Sprite getCurrentFrame();
	public void tick();
}
