
public class SmallBullet implements Bullet{
	private float x,y;
	private double Xpower, Ypower;//Currpower will be negative for conveinence of coding
	private float xyRatio;
	private float Iangle;
	private float Rangle;
	private boolean exist;
	private int bulletDamage=1;
	public SmallBullet(Turret t) {
		x=t.getX();y=t.getY();
		Iangle=t.getAngle();
		Xpower=Math.cos(Iangle)*t.getPower();
		Ypower=Math.sin(Iangle)*t.getPower();
		Rangle=0;
		this.exist=true;
	}
	@Override
	public int getCost() {
		return 1;
	}
	
	@Override
	public void update() {
		x+=Xpower;
		y+=Ypower;
		Ypower++;
		Rangle+=.1f;
	}
	
	@Override
	public int getDamage() {
		return bulletDamage;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}
	@Override
	public int getSize() {
		return 7;
	}
	/**returns true of out of bounds*/
	@Override
	public boolean isOOB() {
		return x>915||y>900;
	}
	@Override
	public float getRotation() {
		return Rangle;
	}
	@Override
	public void destroy() {this.exist=false;}
	@Override
	public boolean isDestroyed() {return !exist;}
}
