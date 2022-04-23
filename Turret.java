
public class Turret {
	private final float PI = 3.141592653589793238462643f;
	private float Ix, Iy;
	private float x,y;
	private static double distance=0;
	private float xyRatio;
	private float angle;
	private int health;
	public Turret(float x, float y) {
		this.Ix=x;this.Iy=y;
		health=100;
	}
	public void update(float mouseX, float mouseY) {
		distance=Math.sqrt((mouseX-x)*(mouseX-x)+(mouseY-y)*(mouseY-y));
		xyRatio=Math.abs((mouseX-x)/(mouseY-y));
		if(xyRatio<1/10)xyRatio=1/10f;
		if(xyRatio>10)xyRatio=10;
	//	modifyRatio();
		angle=(float)Math.atan2(mouseY-Iy, mouseX-Ix);
		if(angle<-PI/1.95f)angle=-PI/1.95f;
		if(angle>0)angle=0;
	}
	public void damage(int n) {
		health-=n;
	}
	public int getHealth() {return health;}
	public void updateXY(float x, float y) {
		this.x=Ix+x;this.y=Iy+y;
	}

	public int getDisplayPower() {
		if(distance<400)return (int)(distance/80)+1;
		else return 6;
	}
	public int getPower() {
		if(distance<480)return (int)(distance/12)+1;
		else return 41;
	}
	public int getDistance() {
		if(distance>0&&distance<480)return (int)distance;
		else return 480;
	}
	public float getxyRatio() {
		return xyRatio;
	}
	public float getX() {return x;}
	public float getY() {return y;}
	public float getAngle() {return angle;}
}
