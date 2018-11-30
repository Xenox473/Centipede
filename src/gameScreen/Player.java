package gameScreen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.Display.Display;

public class Player implements KeyListener{
	
	private double xPos, yPos;
	private int width, height;
	private BufferedImage playerSprite;
	private Rectangle rectangle;
	private final double speed = 5.0d;
	
	private boolean left = false, right = false;

	public Player(double xPos,double yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
				
		rectangle = new Rectangle((int)xPos, (int) yPos, width, height);
		
		try {
			URL url = this.getClass().getResource("/images/Player.png");
			playerSprite = ImageIO.read(url);
		}catch(IOException e) {};
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(playerSprite, (int)xPos, (int)yPos, width, height, null);
	}
	
	public void update(double delta) {
		if(right && !left && xPos < Display.WIDTH-width){
			xPos += speed * delta;
			rectangle.x = (int) xPos;
		}if(!right && left && xPos > 10){
			xPos -= speed * delta;
			rectangle.x = (int) xPos;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			right = true;
		}else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
			left = true;
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			right = false;
		}else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
			left = false;
		}
	}

}
