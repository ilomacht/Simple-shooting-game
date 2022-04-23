import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import processing.core.PApplet;

public class Main extends PApplet{
	public Runner game;
	public boolean paused=true;
	public boolean STORE=false;

	public int highScore=0;
	float x, y;
	public static void main(String[] args) {
		PApplet.main("Main");
	}
	public void settings() {
		size(1000,1000);
	}
	public void setup() {
		background(230);
		game=new Runner(165,880);
	}
	public void draw() {

		if(game.getScore()>highScore)highScore=game.getScore();
		if(game.isGO()==false) {

			if(paused==true) {pauseScreen();}
			else if(STORE==true) {storeScreen();}
			else {
				x=mouseX;
				y=mouseY;
				game.update(x,y);
				background(230);
				drawTurret();

				drawBullets();
				drawEnemies();
				updateScreen();
			}
		}
		if(game.isGO()==true)gameOver();
		fill(0);
		rect(x-5,y,12,2);
		rect(x,y-5,2,12);
		delay(8);
	}
	private void updateScreen() {

		fill(230);
		noStroke();
		rect(-5,-5,1100,105);
		rect(-5,901,1100,105);
		rect(-5,-5,119,1100);
		rect(916,-5,105,1100);
		stroke(0);
		drawScore();
		drawTS();
		drawBP();
		drawShF();
		displayEB();
		displayHealth();
		drawHS();
	}
	private void pauseScreen() {
		fill(0);textSize(48);
		text("PAUSED",200,950);
	}
	
	private void storeScreen() {
		//store here
	}
	
	private void displayHealth() {
		textSize(15);
		fill(0);
		int hp=game.getHealth();
		if(hp<0)hp=0;
		text("HP: "+hp,462.5f,37.5f);
		fill(230);
		float z=348;
		rect(z,15,304,7);
		float x=z+3;
		for(int i=0;i<100;i+=2) {
			if(hp>i)fill(30);
			else fill(230);
			rect(x+1,16,2,5);
			x+=6;
		}
	}
	public final int[] gameX= {7,8,9,10,14,15,16,20,24,28,29,30,31,6,13,17,20,21,23,24,27,6,9,10,13,14,15,16,17,20,22,24,27,28,29,30,6,10,13,17,20,24,27,7,8,9,10,13,17,20,24,28,29,30,31,7,8,9,13,17,21,22,23,24,27,28,29,30,31,6,10,13,17,20,27,31,6,8,10,13,17,20,21,22,23,27,28,29,30,6,10,14,16,20,27,31,7,8,9,15,21,22,23,24,27,31};
	public final int[] gameY= {13,13,13,13,13,13,13,13,13,13,13,13,13,14,14,14,14,14,14,14,14,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,16,16,16,16,16,16,16,17,17,17,17,17,17,17,17,17,17,17,17,20,20,20,20,20,20,20,20,20,20,20,20,20,21,21,21,21,21,21,21,22,22,22,22,22,22,22,22,22,22,22,22,22,23,23,23,23,23,23,23,24,24,24,24,24,24,24,24,24,24};
	public void gameOver() {
		rect(115,100,800,800);
		fill(90);
		stroke(70);
		for(int i=0;i<105;i++) {
			rect(gameX[i]*15+225, gameY[i]*15+200, 15,15);
		}
		textSize(25);
		text("Press \"R\" to restart",400,610);
	}
	public void displayEB() {
		fill(230);
		rect(25,300,50,300);
		float y=305f;
		for(int i=6;i>0;i--) {
			if(game.getDP()>=i)fill(30);
			else fill(230);
			rect(30f,y,40,44.16f);
			y+=44.16f+5f;
		}
	}
	public void drawEnemies() {
		for(Enemy e:game.getEnemies()) {
			int z=150-e.getHealth()*3;
			if(z<0)z=0;
			fill(z);
			rect(e.getX(), e.getY(),
					e.getSize(),e.getSize());
			fill(230);
			textSize(20);
			text(""+e.getHealth(),e.getX()+e.getSize()/5,e.getY()+20+e.getHealth()/2);
		}
	}
	public void drawTurret() {
		fill(230);
		rect(115,100,800,800);
		translate(165,880);
		rotate(-HALF_PI+game.getTurret().getAngle());
		rect(-7.5f,0,15,60);
		rotate(-game.getTurret().getAngle()+HALF_PI);
		translate(-165,-880);
		fill(230);
		arc(155,900,80,80,PI,2*PI,CHORD);
	}
	public void mouseClicked() {
		if(paused==false)
		game.createSmallBullet();
	}

	public void drawBullets() {
		fill(230);
		for(Bullet bc:game.getBullets()) {
			translate(bc.getX(),bc.getY());
			rotate(bc.getRotation());
			rect(-5,-5,10,10);
			rotate(-bc.getRotation());
			translate(-bc.getX(),-bc.getY());
		}
	}
	public void drawScore() {
		double score=(double)game.getScore();
		fill(0);
		textSize(15);
		String sc="";
		if(score>1000000) {
			int sss=(int)score/10000;
			double scr=(double)sss/100;
			sc+=scr;
			sc+="M";
		}
		else if(score>1000) {
			int sss=(int)score/10;
			double scr=(double)sss/100;
			sc+=scr;
			sc+="k";
		}
		else sc+=(int)score;
		text("Score: "+sc, 885, 20);
	}
	public void drawTS() {
		double score=(double)game.getTS();
		fill(0);
		textSize(15);
		String sc="";
		if(score>1000000) {
			int sss=(int)score/10000;
			double scr=(double)sss/100;
			sc+=scr;
			sc+="M";
		}
		else if(score>1000) {
			int sss=(int)score/10;
			double scr=(double)sss/100;
			sc+=scr;
			sc+="k";
		}
		else sc+=(int)score;
		text("SP: "+sc, 885, 40);
	}
	public void drawHS() {
		double score=(double)highScore;
		fill(0);
		textSize(15);
		String sc="";
		if(score>1000000) {
			int sss=(int)score/10000;
			double scr=(double)sss/100;
			sc+=scr;
			sc+="M";
		}
		else if(score>1000) {
			int sss=(int)score/10;
			double scr=(double)sss/100;
			sc+=scr;
			sc+="k";
		}
		else sc+=(int)score;
		text("High Score: "+sc, 865, 980);
	}
	public void drawBP() {
		double score=(double)game.getBP();
		fill(0);
		textSize(15);
		String sc="";
		if(score>1000000) {
			int sss=(int)score/10000;
			double scr=(double)sss/100;
			sc+=scr;
			sc+="M";
		}
		else if(score>1000) {
			int sss=(int)score/10;
			double scr=(double)sss/100;
			sc+=scr;
			sc+="k";
		}
		else sc+=(int)score;
		text("BP: "+sc, 25, 25);
	}
	public void drawShF() {
		double score=(double)game.getBFired();
		fill(0);
		textSize(15);
		String sc="";
		if(score>1000000) {
			int sss=(int)score/10000;
			double scr=(double)sss/100;
			sc+=scr;
			sc+="M";
		}
		else if(score>1000) {
			int sss=(int)score/10;
			double scr=(double)sss/100;
			sc+=scr;
			sc+="k";
		}
		else sc+=(int)score;
		text("High Score: "+sc, 865, 980);
		text("Total Bullets Fired: "+sc, 25, 45);
	}

	public void keyPressed() {
		if(game.isGO()==true&&(key=='R'||key=='r'))setup();
		if(paused==true&&(key=='C'||key=='c')) {game.convert1();updateScreen();}
		if(paused==true&&(key=='D'||key=='d')) {game.convert2();updateScreen();}
		if(paused==true&&(key=='V'||key=='v')) {game.convert3();updateScreen();}
		if(key=='P'||key=='p')paused=!paused;
		if(paused==false&&(key=='f'||key=='F'))game.feature1();
		if(paused==false&&game.getBP()>5&&(key=='z'||key=='Z'))game.createSmallBullet();
		if(game.isGO()==false&&(key=='t'||key=='T'))game.gameOver();
	}
}
