package com.State;

import java.awt.Canvas;
import java.awt.Graphics2D;

public interface StateMachineInterface {
	public void update(double delta);
	public void draw(Graphics2D g);
	public void init(Canvas canvas);
}
