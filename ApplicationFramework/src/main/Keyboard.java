package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gameStates.StateManager;

// Classe para pegar inputs do teclado 

public class Keyboard extends KeyAdapter{
	private StateManager stateManager;
	
	public Keyboard(StateManager sm) {
		stateManager = sm;
	}
	
	@Override
	public void keyPressed(KeyEvent a) {
		int key = a.getKeyCode();
		stateManager.keyPressed(key);
	}
}
