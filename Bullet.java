
public interface Bullet {
	public int getCost();
	public int getDamage();
	public float getX();
	public float getY();
	public void update();
	public int getSize();
	public boolean isOOB();
	public float getRotation();
	public void destroy();
	public boolean isDestroyed();
}
