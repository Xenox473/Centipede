package com.State;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;

import gameScreen.GameScreen;

public class StateMachine {
	
	private Canvas canvas;
	private ArrayList<StateMachineInterface> states = new ArrayList<StateMachineInterface>();
	private byte stateSelect = 0;
	
	public StateMachine(Canvas canvas) {
		StateMachineInterface game = new GameScreen();
		states.add(game);
		
		this.canvas = canvas;
	}
	
	public void draw(Graphics2D g) {
		states.get(stateSelect).draw(g);
	}
	
	public void update(double delta) {
		states.get(stateSelect).update(delta);
	}
	
	public void setState(byte b) {
		for(int i = 0; i < canvas.getKeyListeners().length; i++) {
			canvas.removeKeyListener(canvas.getKeyListeners()[i]);
		}
		stateSelect = b;
		states.get(stateSelect).init(canvas);
	}
	
	public byte getStates() {
		return stateSelect;
	}
}
