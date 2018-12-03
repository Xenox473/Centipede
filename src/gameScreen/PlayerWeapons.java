package gameScreen;

import java.awt.Graphics2D;
import java.util.ArrayList;

import player_bullets.MachineGun;
import player_bullets.PlayerWeapon;
import sound.Sound;
import timer.Timer;

public class PlayerWeapons {
	
	public ArrayList<PlayerWeapon> weapons = new ArrayList<PlayerWeapon>();
	private Timer timer;
	private Sound shootSound;
	
	public PlayerWeapons() {
		timer = new Timer();
		shootSound = new Sound("/sounds/The amazing Spider man vs Rami Web twip sound effect.wav");
	}
	
	public void draw(Graphics2D g) {
		for (int i = 0; i < weapons.size(); i++) {
			weapons.get(i).draw(g);
		}
	}
	public void update(double delta) {
		for (int i = 0; i < weapons.size(); i++) {
			weapons.get(i).update(delta);
			if(weapons.get(i).isDestroyed()) {
				weapons.remove(i);
			}
		}
	}
	
	public void shootBullet(double xPos, double yPos, int width, int height) {
		if(timer.eventTimer(300)) {
			if (shootSound != null) {
				if (shootSound.isPlaying()) {
					shootSound.stop();
				}
				shootSound.play();
			}
			weapons.add(new MachineGun(xPos + 22, yPos + 15, width, height));
		}
	}
}
