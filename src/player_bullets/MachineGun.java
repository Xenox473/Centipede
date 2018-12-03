package player_bullets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import com.Display.Display;

public class MachineGun extends PlayerWeapon{
	
	private Rectangle bullet;
	private final double speed = 1.5;
	
	public MachineGun(double xPos, double yPos, int width, int height) {
		this.setxPos(xPos);
		this.setyPos(yPos);
		this.setWidth(width);
		this.setHeight(height);
		this.bullet = new Rectangle((int)getxPos(), (int)getyPos(), getWidth(), getHeight());
	}
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if(bullet != null) {
			g.setColor(Color.WHITE);
			g.fill(bullet);
		}
	}

	@Override
	public void update(double delta) {
		// TODO Auto-generated method stub
		if(bullet != null) {
			this.setyPos(getyPos() - (speed));
			bullet.y = (int) this.getyPos();
			wallCollision();
			isOutBounds();
		}
	}

	@Override
	public boolean collisionRectangle(Rectangle rectangle) {
		// TODO Auto-generated method stub
		if(this.bullet != null) {
			if(bullet.intersects(rectangle)) {
				this.bullet = null;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean collisionPoly(Polygon poly) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		if (bullet == null) {
			return true;
		}
		return false;
	}

	@Override
	protected void wallCollision() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isOutBounds() {
		// TODO Auto-generated method stub
		if(!isDestroyed()) {
			if(bullet.y < 0 || bullet.y > Display.HEIGHT || bullet.x<0 || bullet.x > Display.WIDTH) {
				bullet = null;
			}
		}
	}

}
