package gameStates;

import java.util.Stack;

//aqui podemos gerenciar os estados do game usando uma pilha
public class StateManager {
	private Stack<GameState> states;
	
	public StateManager() {
		states = new Stack<GameState>();
		states.push(new MenuState(this));
	}
	
	public GameState getCurrentState() {
		return states.peek();
	}
	
	public void update() {
		states.peek().update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		states.peek().draw(g);
	}
	
	public void keyPressed(int key) {
		states.peek().keyPressed(key);
	}

	public void push(GameState state) {
		states.push(state);
	}
	
	public void pop() {
		states.pop();
	}
}
