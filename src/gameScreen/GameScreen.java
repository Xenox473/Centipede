package gameScreen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.lang.Math;

import com.Display.Display;
import com.State.StateMachineInterface;

import enemy_types.Centipede;
import enemy_types.SingleCentipede;
import enemy_types.Spider;
import sound.Sound;


public class GameScreen implements StateMachineInterface{
	
	private Player player;
	private Mushroom mushroom;
	private static Centipede centipede;
	private static Spider spider;
	private static ArrayList<Mushroom> mushrooms = new ArrayList<Mushroom>();
	private Font gameScreen = new Font("Arial", Font.PLAIN, 48);
	static Sound punch;
	private static double speed = 0.5;
	
	public static int score = 0;
	
	public GameScreen() {
		punch = new Sound("/sounds/Punch_sound_effect_2-6HDDjHEKfn0.wav");
		player = new Player(Display.WIDTH/2-50, Display.HEIGHT-75, 50, 50);
		int count = 0;
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 9; j++) {
				double flag1 =  Math.random() * 16;
				double flag2 =  Math.random() * 9;
//				double flag1 = i;
//				double flag2 = 2;
//				System.out.println((int)flag1);
//				mushrooms.add(new Mushroom(3 * 50, 0 * 50, 50, 50));
				if(mushroomCheck((int)flag1, (int)flag2)) {
					System.out.println(count++);
					mushrooms.add(new Mushroom((int)flag1 * 50, (int)flag2 * 50, 50, 50));	
				}
			}
		}
		centipede = new Centipede(speed);
		spider = new Spider(400, 300, 50, 50);
	}
	public void update(double delta) {
		if (player.getLives() <= 0) {
			Display.backgroundMusic.stop();
			return;
		}
		if (centipede.getSize() == 1) {
			GameScreen.score += 600;
			if (!spider.isInPlay()) {
				spider = new Spider(400, 300, 50, 50);
			}
			speed += 0.25;
			centipede = new Centipede(speed);
		}
		
		player.update(delta, centipede.getCentipede(), spider);
		for (int index = 0; index < mushrooms.size(); index++) {
			if(!mushrooms.get(index).isInPlay()) {
				mushrooms.remove(index);
				break;
			}
			mushrooms.get(index).update(delta, player.playerWeapons.weapons);
		}
		centipede.update(delta, player, mushrooms, player.playerWeapons.weapons);
		if(spider.bulletCollision(player.playerWeapons.weapons)) {
			spider.setInPlay(false);
		}
		spider.update(delta, player, mushrooms);
	}
	public void draw(Graphics2D g) {
		if (player.getLives() <= 0) {
			g.setColor(Color.red);
			g.setFont(gameScreen);
			String gameOver = "GAME OVER!";
			int gameOverWidth = g.getFontMetrics().stringWidth(gameOver);
			g.drawString(gameOver, (Display.WIDTH/2)-(gameOverWidth/2), Display.HEIGHT/2);
			g.drawString("Score: " + GameScreen.score, (Display.WIDTH/2)-(gameOverWidth/2), 585);
			return;
		}
		g.setColor(Color.GREEN);
		g.drawString("Score: " + GameScreen.score, 15, 585);
		g.drawString("Lives: " + player.getLives(), 100, 585);

		player.draw(g);;
		for(int i = 0; i < mushrooms.size(); i++) {
			mushrooms.get(i).draw(g);
		}	
		centipede.draw(g);
		spider.draw(g);
		
	}
	public void init(Canvas canvas) {
		canvas.addMouseMotionListener(player);
		canvas.addMouseListener(player);
	}
	
	public boolean mushroomCheck(int xPos, int yPos) {
		if(xPos == 0 || xPos == 15) {
			return false;
		}
		if (yPos == 0) {
			return false;
		}
		
		for (int i = 0; i < mushrooms.size(); i++) {
			Mushroom check = mushrooms.get(i);
			if(((int)check.getxPos() == (xPos) * 50) && (int)check.getyPos() == (yPos)*50){
				return false;
			} 
			if(((int)check.getxPos() == (xPos - 1) * 50) && (int)check.getyPos() == (yPos - 1)*50){
				return false;
			} 
			if(((int)check.getxPos() == (xPos + 1) * 50) && (int)check.getyPos() == (yPos - 1)*50){
				return false;
			}
			if(((int)check.getxPos() == (xPos - 1) * 50) && (int)check.getyPos() == (yPos + 1)*50){
				return false;
			}
			if(((int)check.getxPos() == (xPos + 1) * 50) && (int)check.getyPos() == (yPos + 1)*50){
				return false;
			}
			if(((int)check.getxPos() == (xPos) * 50) && (int)check.getyPos() == (yPos - 1)*50){
				return false;
			}
			if(((int)check.getxPos() == (xPos) * 50) && (int)check.getyPos() == (yPos + 1)*50){
				return false;
			}
		}
		return true;
	}
	
	public static void reset() {
		centipede = new Centipede(speed);
		spider = new Spider(400, 300, 50, 50);
		for (int index = 0; index < mushrooms.size(); index++) {
			mushrooms.get(index).setLives(2);
		}
	}
	
}
