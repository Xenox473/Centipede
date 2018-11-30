package gameScreen;

import java.awt.Canvas;
import java.awt.Graphics2D;

import com.State.StateMachineInterface;

public class GameScreen implements StateMachineInterface{
	
	private Player player;
	
	public GameScreen() {
		player = new Player(150,400,50,50);
	}
	public void update(double delta) {
		player.update(delta);
	}
	public void draw(Graphics2D g) {
		player.draw(g);;
	}
	public void init(Canvas canvas) {
		canvas.addKeyListener(player);
	}
}
