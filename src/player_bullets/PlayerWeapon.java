package player_bullets;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public abstract class PlayerWeapon {
	
	protected double xPos, yPos;
	protected int width, height;
	
	public abstract void draw(Graphics2D g);
	public abstract void update(double delta);
	public abstract boolean collisionRectangle(Rectangle rectangle);
	public abstract boolean collisionPoly(Polygon poly);
	public abstract boolean isDestroyed();
	protected abstract void wallCollision();
	protected abstract void isOutBounds();
	
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
