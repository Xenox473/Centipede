package gameScreen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.Display.Display;

import enemy_types.Centipede;
import enemy_types.SingleCentipede;
import enemy_types.Spider;

public class Player implements MouseMotionListener, MouseListener{
	
	private double xPos, yPos, xTemp, yTemp;
	private int width, height, lives = 3;
	private BufferedImage playerSprite;
	private Rectangle player;
	private boolean shoot = false;
	
	public PlayerWeapons playerWeapons;
	
	public Player(double xPos,double yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
				
		player = new Rectangle((int)xPos, (int) yPos, width, height);
		
		try {
			URL url = this.getClass().getResource("/images/Spider-Man.png");
			playerSprite = ImageIO.read(url);
		}catch(IOException e) {};
		
		playerWeapons = new PlayerWeapons();
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(playerSprite, (int)xPos, (int)yPos, width, height, null);
		playerWeapons.draw(g);
	}
	
	public void update(double delta, ArrayList<SingleCentipede> centipede, Spider spider) {
		
		for (int index = 0; index < centipede.size(); index++) {
			if(collisionRectangle(centipede.get(index).getRectangle())) {
				lives --;
				GameScreen.punch.play();
				GameScreen.reset();
				System.out.println("DEAD");
			}
		}
		if(collisionRectangle(spider.getRectangle())) {
			lives --;
			GameScreen.reset();
			System.out.println("DEAD");
		}
		if (xTemp < Display.WIDTH - width) {
			xPos = xTemp;
			if (player != null) {
				player.x = (int) xPos;
			}
		}
		if (yTemp < Display.HEIGHT - height) {
			yPos = yTemp;
			if (player != null) {
				player.y = (int) yPos;
			}
		}
		playerWeapons.update(delta);
		if(shoot) {
//			shoot = false;
			playerWeapons.shootBullet(xPos, yPos, 5, 5);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		xTemp = e.getX();
		yTemp = e.getY();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		xTemp = e.getX();
		yTemp = e.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		shoot = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		shoot = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		shoot = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean collisionRectangle(Rectangle rectangle) {
		// TODO Auto-generated method stub
		if(player != null && rectangle != null) {
			if(player.intersects(rectangle)) {
				return true;
			}
		}
		return false;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

}
