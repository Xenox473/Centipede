package gameScreen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import player_bullets.PlayerWeapon;

public class Mushroom {
	
	private double xPos, yPos;
	private int width, height;
	private BufferedImage mushroomSprite;
	private Rectangle rectangle;
	private boolean inPlay = true;
	private int lives = 2;
	
	public boolean isInPlay() {
		return inPlay;
	}

	public void setInPlay(boolean inPlay) {
		this.inPlay = inPlay;
	}

	public Mushroom(double xPos,double yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
				
		setRectangle(new Rectangle((int)xPos, (int) yPos, width, height));
		
		try {
			URL url = this.getClass().getResource("/images/Mushroom.png");
			mushroomSprite = ImageIO.read(url);
		}catch(IOException e) {};
	}
	
	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public void draw(Graphics2D g) {
		g.drawImage(mushroomSprite, (int)xPos, (int)yPos, width, height, null);
	}
	
	public void update(double delta, ArrayList<PlayerWeapon> machineGun) {
		for (int j = 0; j < machineGun.size(); j++) {
			if(machineGun.get(j).collisionRectangle(getRectangle())) {
//				if(centipede.get(index).getLives() == 0) {
//					centipede.get(index).setInPlay(false);
//					centipede.remove(index);
//					size--;
//					System.out.println(size);
//					break;
//				}
				if(lives == 0) {
					inPlay = false;
					GameScreen.score += 5;
				}else {
					lives--;
					GameScreen.score += 1;
				}
			}
		}		
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

}
