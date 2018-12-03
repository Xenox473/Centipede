package enemy_types;

import java.awt.Graphics2D;
import java.lang.Math;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.Display.Display;

import gameScreen.GameScreen;
import gameScreen.Mushroom;
import gameScreen.Player;
import gameScreen.PlayerWeapons;
import player_bullets.PlayerWeapon;
import timer.Timer;

public class Spider implements EnemyType{
	
	private double xPos, yPos, tempX, tempY;
	private int width, height, directionx = 1, directiony = 1;
	private final double speed = 0.5;
	private BufferedImage centipedeSprite;
	private Rectangle rectangle;
	private int lives = 1;
	private boolean inPlay = true;
	public Timer timer;
	
	public Spider(double xPos, double yPos, int width, int height) {
//		singleCentipede = new SpriteAnimation(xPos, yPos, 300);
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		
		timer = new Timer();
				
		rectangle = new Rectangle((int)xPos, (int) yPos, width, height);
		
		try {
			URL url = this.getClass().getResource("/images/Doc Ock.png");
			centipedeSprite = ImageIO.read(url);
		}catch(IOException e) {};
	}
	
	@Override
	
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if(inPlay) {
			g.drawImage(centipedeSprite, (int)xPos, (int)yPos, width, height, null);
		}
	}

	@Override
	public void changeDirection(double delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deathScene() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collide(int i, Player player, ArrayList<EnemyType> enemy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOutBounds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(double delta, Player player, ArrayList<Mushroom> mushrooms) {
		// TODO Auto-generated method stub
//		if ((xPos + speed * directionx > 0) && (xPos + speed * directionx < Display.WIDTH - width)) {
//			xPos += speed * directionx;
//			rectangle.x = (int) xPos;
//		}
//		else {
//			directionx *= -1;
//		}
//		if ((yPos + speed * directiony > 0) && (xPos + speed * directiony < Display.HEIGHT - height)) {
//			yPos += speed * directiony;
//			rectangle.y = (int) xPos;
//		}
//		else {
//			directiony *= -1;
//		}
		if (timer.eventTimer(300) && inPlay){
			tempX = 16 * Math.random() * 50;
			tempY = 15 * Math.random() * 50;
			xPos += (tempX - xPos) * 0.25;
			yPos += (tempY - yPos) * 0.25;
			rectangle.x = (int) xPos;
			rectangle.y = (int) yPos;
		}
	}
	
	public Boolean bulletCollision(ArrayList<PlayerWeapon> weapons) {
		if (rectangle != null) {
			for(int index = 0; index < weapons.size(); index++) {
				if(weapons.get(index).collisionRectangle(rectangle)) {
					System.out.println("HIT");
					if(lives == 0) {
						GameScreen.score += 600;
						rectangle = null;
						return true;
					}
					lives --;
					GameScreen.score += 100;
				}
			}
		}
		return false;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public boolean isInPlay() {
		return inPlay;
	}

	public void setInPlay(boolean inPlay) {
		this.inPlay = inPlay;
	}

}
