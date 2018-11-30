package com.Display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.State.StateMachine;

public class Display extends Canvas implements  Runnable{
	
	private static final long serialVersionUID = 1L;
	//Defining width and height of display.
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	
	//Defining FPS
	public int FPS;
	
	//Defining State Machine
	
	public static StateMachine state;
	
	//Initializing Threads
	private boolean isRunning = false;
	private Thread thread;
	
	//Creating thread start and stop methods
	public synchronized void start() {
		if(isRunning) {
			return;
		}else {
			isRunning = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop() {
		if (!isRunning) {
			return;
		}else {
			isRunning = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		//Initializing display and adding to frame
		Display display = new Display();
		JFrame frame = new JFrame();
		frame.add(display);
		frame.pack();
		frame.setTitle("Centipede");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		display.start();
	}
	
	public Display() {
		//Setting width and height of the display
		this.setSize(WIDTH,HEIGHT);
		this.setFocusable(true);
		state = new StateMachine(this);
		state.setState((byte) 0);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.createBufferStrategy(3);
		BufferStrategy buffStrat = this.getBufferStrategy();
		
		long timer = System.currentTimeMillis();
		long loopTimer = System.nanoTime();
		final int targetFPS = 60;
		final long optimalTime = 1000000000/targetFPS;
		int frames = 0;

		while(isRunning) {
			long now = System.currentTimeMillis();
			long update = now - loopTimer;
			loopTimer = now;
			double delta = update / ((double) optimalTime);
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				FPS = frames;
				frames = 0;
			}
			
			draw(buffStrat);
			update(delta);
			
			try {
				Thread.sleep((loopTimer - System.nanoTime())+optimalTime / 1000000);
			} catch(Exception e) {};
//			System.out.println("Hello World");
		}
		
	}
	
	public void draw(BufferStrategy buffStrat) {
		do {
			do {
				Graphics2D g = (Graphics2D) buffStrat.getDrawGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, WIDTH, HEIGHT);
				state.draw(g);
				g.dispose();
			}while(buffStrat.contentsRestored());
			buffStrat.show();
		}while (buffStrat.contentsLost());
	}
	
	public void update(double delta) {
		state.update(delta);
	}
	
}
