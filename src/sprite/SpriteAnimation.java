package sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import timer.Timer;

public class SpriteAnimation {
	
	private double xPos, yPos;
	private int animationSpeed;
	
	private boolean loop = false;
	private boolean play = false;
	private boolean destroyAfterAnimation = false;
	
	private Timer timer;
	private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	private byte currentSprite;
	
	public SpriteAnimation(double xPos, double yPos, int animationSpeed) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.animationSpeed = animationSpeed;
		timer = new Timer();
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(sprites.get(currentSprite), (int)getxPos(), (int)getyPos(), null);
		if (isDestroyed()) {
			return;
		}
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
	
	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public byte getCurrentSprite() {
		return currentSprite;
	}

	public void setCurrentSprite(byte currentSprite) {
		this.currentSprite = currentSprite;
	}

	public void update(double delta) {
		if (isDestroyed()) {
			return;
		}
		
		if (loop && !play) {
			loopAnimation();
		}
		
		if (!loop && play) {
			playAnimation();
		}
	}
	
	public void setPlayerAnimation(boolean play, boolean destroyAfterAnimation) {
		this.play = play;
		this.setDestroyAfterAnimation(destroyAfterAnimation);
	}
	
	public void stopAnimation() {
		loop = false;
		play = false;
	}
	
	public void resetSprite() {
		loop = false;
		play = false;
		currentSprite = 0;
	}
	
	private void playAnimation() {
		if (timer.eventTimer(animationSpeed) && currentSprite != sprites.size()-1 && !isDestroyAfterAnimation()){
			play = false;
			currentSprite = 0;
		}
		else if(timer.eventTimer(animationSpeed) && currentSprite == sprites.size() - 1 && isDestroyAfterAnimation()) {
			sprites = null;
		}
		else if(timer.eventTimer(animationSpeed) && currentSprite != sprites.size() - 1) {
			currentSprite++;
		}
	}
	
	private void loopAnimation() {
		if (timer.isReady(animationSpeed) && currentSprite == sprites.size() - 1) {
			currentSprite = 0;
			timer.reset();
		}
		else if(timer.eventTimer(animationSpeed) && currentSprite != sprites.size()-1) {
			currentSprite++;
		}
	}
	
	public boolean isDestroyed() {
		if (sprites == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void addSpriteAnimation(BufferedImage spriteMap, int xPos, int Ypos, int width, int height) {
		sprites.add(spriteMap.getSubimage(xPos, Ypos, width, height));
	}

	public boolean isDestroyAfterAnimation() {
		return destroyAfterAnimation;
	}

	public void setDestroyAfterAnimation(boolean destroyAfterAnimation) {
		this.destroyAfterAnimation = destroyAfterAnimation;
	}
}
