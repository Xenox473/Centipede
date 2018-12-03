package enemy_types;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.Display.Display;

import gameScreen.Mushroom;
import gameScreen.Player;

public class SingleCentipede implements EnemyType{
	
	private double xPos, yPos;
	private int width, height, direction = -1;
	private double speed;
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	private BufferedImage centipedeSprite;
	private Rectangle rectangle;
	private int lives = 2;
	private boolean move = false, inPlay = true;
	
	
	public SingleCentipede(double xPos, double yPos, int width, int height, String pathName) {
//		singleCentipede = new SpriteAnimation(xPos, yPos, 300);
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
				
		rectangle = new Rectangle((int)xPos, (int) yPos, width, height);
		
		try {
			URL url = this.getClass().getResource(pathName);
			centipedeSprite = ImageIO.read(url);
		}catch(IOException e) {};
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if(inPlay) {
			g.drawImage(centipedeSprite, (int)xPos, (int)yPos, width, height, null);
		}
	}

	@Override
	public void update(double delta, Player player, ArrayList<Mushroom> mushrooms) {
		// TODO Auto-generated method stub
		if (centipedeSprite != null && inPlay) {
			if(move) {
				move = false;
				if(getyPos() != Display.HEIGHT - 3*50) {
					this.setyPos(getyPos()+50);
					this.getRectangle().y = (int) getyPos();
				}
			}
			if((getxPos() + speed * direction > 0) && (getxPos() + speed * direction < Display.WIDTH - this.width)) {
				this.setxPos(getxPos() + speed * direction);
				this.getRectangle().x = (int) getxPos();
				if(collisionMushroom(mushrooms)) {
//					this.setyPos(getyPos()+50);
//					this.getRectangle().y = (int) getyPos();
					move = true;
//					direction *= -1;
				}
			}
			else{
//				this.setyPos(getyPos()+50);
//				this.getRectangle().y = (int) getyPos();
				move = true;
				direction *= -1;
			}
		}
		
	}

	public boolean isInPlay() {
		return inPlay;
	}
	public void setInPlay(boolean inPlay) {
		this.inPlay = inPlay;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
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
	@Override
	public void changeDirection(double delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOutBounds() {
		if(rectangle.x > 0 && rectangle.x < Display.WIDTH - rectangle.width)
			return false;
		return true;
	}
	
	public boolean collisionMushroom(ArrayList<Mushroom> mushrooms) {
		// TODO Auto-generated method stub
		if(centipedeSprite != null) {
			for(int index = 0; index < mushrooms.size(); index++) {
				if(rectangle.intersects(mushrooms.get(index).getRectangle())) {
					return true;
				}
			}
		}
		return false;
	}
}


