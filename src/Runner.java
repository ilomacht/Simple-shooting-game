import java.util.ArrayList;

public class Runner {
	private int bulletPoints;
	private int score;
	private Turret t;
	private ArrayList<Bullet> bullets;
	private ArrayList<Enemy> enemies;
	private boolean gameOver;
	public int ENEMY_FREQ=50, MIN_HP=10, MAX_HP=50;
	public int tempScore=0;
	public final int SCORE_PLUS=5;
	private int bullets_fired=0;
	public Runner(float x, float y) {
		t=new Turret(x,y);
		bullets=new ArrayList<Bullet>();
		enemies=new ArrayList<Enemy>();
		bulletPoints=5;
		score=0;
		enemies.add(new Enemy(MIN_HP,MAX_HP));
		gameOver=false;
	}

	public boolean isGO() {return gameOver;}
	public void gameOver() {gameOver=true;}
	public void update(float mouseX, float mouseY) {
		t.update(mouseX, mouseY);		float tx,ty;
		ty=(float) Math.sin(t.getAngle())*60;
		tx=(float) Math.cos(t.getAngle())*60;
		t.updateXY(tx, ty);
		updateBullets();
		updateEnemies();
		if(Math.random()*ENEMY_FREQ<1)enemies.add(new Enemy(MIN_HP,MAX_HP));
		colliding();
		removing();
		if(bulletPoints<0||t.getHealth()<=0)gameOver();
	}
	public int getHealth() {return t.getHealth();}
	private void updateEnemies() {
		for(int i=enemies.size()-1;i>=0;i--) {
			if
			(enemies.get(i).getY()>=900-enemies.get(i).getSize())
				enemies.get(i).bounce();
			if(enemies.get(i).getX()<114-enemies.get(i).getSize()){
				int n=(int)enemies.get(i).getHealth()/3;
				if(n<1)n=1;
				t.damage(n);
				enemies.get(i).mark();
			}
			enemies.get(i).update();
		}
		for(int i=enemies.size()-1;i>=0;i--) {
			if(enemies.get(i).isOOB()==true)enemies.remove(i);
		}
	}
	private void updateBullets() {
		for(int i=bullets.size()-1;i>=0;i--) {
			bullets.get(i).update();
		}
		for(int i=bullets.size()-1;i>=0;i--) {
			if(bullets.get(i).isOOB()==true)bullets.remove(i);
		}
	}
	public void createSmallBullet() {
		bullets_fired++;
		Bullet nsb=new SmallBullet(t);
		bullets.add(nsb);
		bulletPoints-=nsb.getCost();
	}
	public int getBFired() {return bullets_fired;}
	public void colliding() {
		for(int i=0;i<enemies.size();i++) {
			for(int j=bullets.size()-1;j>=0;j--) {
				if(enemies.get(i).touchBullet(bullets.get(j))) {
					score+=SCORE_PLUS;
					tempScore+=SCORE_PLUS;
					bulletPoints+=5;
					enemies.get(i).damage(bullets.get(j).getDamage());
					bullets.get(j).destroy();
				}
			}
		}
	}
	public void removing() {
		for(int i=enemies.size()-1;i>=0;i--) {
			if(enemies.get(i).getHealth()<=0) {
				enemies.remove(i);
				bulletPoints+=10;
			}
		}
		for(int i=bullets.size()-1;i>=0;i--) {
			if(bullets.get(i).isDestroyed()==true)bullets.remove(i);
		}
	}
	public int getBP() {return bulletPoints;}
	public int getDP() {return t.getDisplayPower();}
	public int getScore() {return score;}
	public int getDistance() {return t.getDistance();}
	public Turret getTurret() {return t;}
	public ArrayList<Bullet> getBullets(){return bullets;}
	public ArrayList<Enemy> getEnemies(){return enemies;}
	public void convert1() {
		if(bulletPoints>1000) {
			bulletPoints-=100;
			score+=SCORE_PLUS*110;
			tempScore+=SCORE_PLUS*110;
		}
		else if(bulletPoints>13) {
			bulletPoints-=10;
			score+=SCORE_PLUS*10;
			tempScore+=SCORE_PLUS*10;
		}
	}
	public void wipe() {
		if(bulletPoints>10000) {
			bulletPoints-=10000;
			while(!enemies.isEmpty()) {
				enemies.remove(0);
			}
		}
	}
	public void convert2() {
		if(bulletPoints>513&&t.getHealth()<=10) {
			bulletPoints-=100;
			t.damage(t.getHealth()-100);
		}
		if(bulletPoints>28&&t.getHealth()<=98) {
			bulletPoints-=25;
			t.damage(-2);
		}
		else if(bulletPoints>18&&t.getHealth()<=99) {
			bulletPoints-=15;
			t.damage(-1);
		}
	}
	public void feature1() {
		if(tempScore>600000) {
			for(int i=0;i<50;i++)enemies.add(new Enemy(1,4));
			tempScore-=500000;
		}
		else if(tempScore>100000) {
			enemies.add(new Enemy(1000,1005));
			tempScore-=50000;
		}
		else if(tempScore>5000) {
			enemies.add(new Enemy(750,950));
			tempScore-=5000;
		}
		
		else if(tempScore>1500) {
			enemies.add(new Enemy(200,250));
			tempScore-=1500;
		}
		else if(tempScore>500) {
			enemies.add(new Enemy(51,75));
			tempScore-=500;
		}
		else if(tempScore>150) {
			int z=(int)(Math.random()*5)+3;
			for(int i=0;i<z;i++) {
				enemies.add(new Enemy(1,4));
				
			}
			tempScore-=150;
		}
	}
	public void convert3() {
		if(tempScore>20) {
			tempScore-=20;
			bulletPoints++;
		}
	}
	public int getTS() {return tempScore;}
	public void burn() {
		if(bulletPoints>100) {
			int temp=bulletPoints-100;
			bulletPoints=100;
			score+=temp*SCORE_PLUS/3;
		}
	}
}
