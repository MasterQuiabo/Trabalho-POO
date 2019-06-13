package gameStates;

import java.awt.Graphics2D;

import graphics.Background;

public abstract class GameState {
	protected Background bg;
	protected StateManager stateManager;
	
	public GameState(StateManager sm) {
		this.stateManager = sm;
		
	}
	
	public abstract void draw(Graphics2D g);
	
	public abstract void update();
	
	public abstract void keyPressed(int key);
	
}
