
public class Enemy {
	private int health;
	private float x,y;
	private float Yspeed, Xspeed;
	private final float ela=.95f;//elasti....
	private float set=-15;
	private boolean bound;
	public Enemy(int minh, int maxh) {
		health=(int)(((double)(Math.random()*(maxh-minh)))+minh+1);
		x=895-health/2;y=120-health/2;
		Yspeed=0;Xspeed=-1;
		bound=false;
	}
	public void damage(int pts) {
		health-=pts;
	}
	public int getHealth() {return health;}
	public void mark() {bound=true;}
	public boolean isOOB() {return bound;}
	public void update() {
		if(health<20)Xspeed=-1f;
		else if(health<35)Xspeed=-.6f;
		else if(health<51)Xspeed=-.2f;
		else Xspeed=0;
		y+=Yspeed;x+=Xspeed;
		Yspeed+=.2f;
		
	}
	public void bounce() {
		set*=ela;
		Yspeed=set;
	}
	public float getX() {return x;}
	public float getY() {return y;}
	public float getSize() {return 20+getHealth();}
	public boolean touchBullet(Bullet b) {
		float testX=b.getX(), testY=b.getY();
		if(b.getX()<x)testX=x;
		else if(b.getX()>x+getSize())testX=x+getSize();
		if(b.getY()<y)testY=y;
		else if(b.getY()>y+getSize())testY=y+getSize();
		float distX=b.getX()-testX, distY=b.getY()-testY;
		float dist=(float) Math.sqrt(distX*distX+distY*distY);
		if(dist<15)return true;
		return false;
	}
}
