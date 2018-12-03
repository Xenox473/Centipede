package enemy_types;

import java.awt.Graphics2D;
import java.util.ArrayList;

import gameScreen.Mushroom;
import gameScreen.Player;

public interface EnemyType {
	void draw(Graphics2D g);
//	void update(double delta, Player player);
	void changeDirection(double delta);
	
	boolean deathScene();
	boolean collide(int i, Player player, ArrayList<EnemyType> enemy);
	boolean isOutBounds();
	void update(double delta, Player player, ArrayList<Mushroom> mushrooms);
}
