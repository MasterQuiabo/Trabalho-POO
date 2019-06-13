package gameStates;

import java.util.ArrayList;

//aqui podemos gerenciar os estados do game usando arraylist
public class StateManager {
	private ArrayList<GameState> states;
	private int curState;
	
	public StateManager() {
		curState = State.valueOf(State.MENUSTATE);
		states = new ArrayList<GameState>();
		states.add(new MenuState(this));
		states.add(new PlayState(this));
	}
	
	public void setState(int state) {
		curState = state;
	}
	
	public void setState(State state) {
		curState = State.valueOf(state);
	}
	
	public GameState getState() {
		return states.get(curState);
	}
	
	public void update() {
		states.get(curState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		states.get(curState).draw(g);
	}
	
	public void keyPressed(int key) {
		states.get(curState).keyPressed(key);
	}
}
