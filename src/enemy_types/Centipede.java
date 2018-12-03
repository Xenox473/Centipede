package enemy_types;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.Display.Display;

import gameScreen.GameScreen;
import gameScreen.Mushroom;
import gameScreen.Player;
import gameScreen.PlayerWeapons;
import player_bullets.MachineGun;
import player_bullets.PlayerWeapon;

public class Centipede {
	
	private ArrayList<SingleCentipede> centipede = new ArrayList<SingleCentipede>();
	private int count = 0;
	private int size = 12;
//	private SingleCentipede centipede1, centipede2;
	private ArrayList<String> pathNames = new ArrayList<String>();
	
	public Centipede(double speed) {
		pathNames.add("/images/Shocker.png");
		pathNames.add("/images/Rhino.png");
		pathNames.add("/images/Lizard.png");
		pathNames.add("/images/Fisk.png");
		pathNames.add("/images/GG.png");
		pathNames.add("/images/Electro.png");
		pathNames.add("/images/Mysterio.png");
		pathNames.add("/images/Sandman.png");
		pathNames.add("/images/Vulture.png");
		pathNames.add("/images/Venom.png");
		pathNames.add("/images/Carnage.png");
		pathNames.add("/images/Kraven.png");
		
		for(int index = 0; index < 12; index ++) {
			centipede.add(new SingleCentipede(Display.WIDTH - 50 * index, 0, 50, 50, pathNames.get(index)));
			centipede.get(index).setSpeed(speed);
		}
//		centipede1 = new SingleCentipede(Display.WIDTH - 50, 0, 50, 50);
//		centipede2 = new SingleCentipede(Display.WIDTH - 100, 0, 50, 50);

	}
	
	public void update(double delta, Player player, ArrayList<Mushroom> mushrooms, ArrayList<PlayerWeapon> machineGun) {
		for(int index = 0; index < size; index ++) {
			centipede.get(index).update(delta, player, mushrooms);
			for (int j = 0; j < machineGun.size(); j++) {
				if(machineGun.get(j).collisionRectangle(centipede.get(index).getRectangle())) {
					GameScreen.score += 2;
					centipede.get(index).setLives(centipede.get(index).getLives() - 1);
					if(centipede.get(index).getLives() == 0) {
						GameScreen.score += 5;
						centipede.get(index).setInPlay(false);
						centipede.remove(index);
						size--;
						System.out.println(size);
						break;
					}
				}
			}			
		}
//		if (count < 1) {
//			count++;
//		}
//		if (count == 1) {
//			centipede2.setInPlay(true);
//		}
//		centipede1.setInPlay(true);
//		centipede1.update(delta, player, mushrooms);
//		centipede2.update(delta, player, mushrooms);
//		count++;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public ArrayList<SingleCentipede> getCentipede(){
		return centipede;
	}

	public void draw(Graphics2D g) {
		for(int index = 0; index < size; index ++) {
			centipede.get(index).draw(g);
		}
//		centipede1.draw(g);
//		centipede2.draw(g);
//		
	}
}
