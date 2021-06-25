package mc322.game.util.observer;

public interface ISubject {
	public void addListener(IObject listener);
	public void removeListener(IObject listener);
	public void notifyListeners();
}
