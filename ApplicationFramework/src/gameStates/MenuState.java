package gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import graphics.Background;

import java.awt.Graphics2D;
import main.Panel;

public class MenuState extends GameState{
	private int curChoice;
	private String[] options = {"START","EXIT"};
	private Font titleFont;
	private Font altFont;
	//posicoes de referencia para o texto
	private static final int xPosText = Panel.width/2;
	private static final int yPosText = Panel.height/2;
	
	public MenuState(StateManager sm) {
		super(sm);
		this.curChoice = 0;
		this.titleFont = new Font("Arial",Font.CENTER_BASELINE, 24);
		this.altFont = new Font("Serif", Font.PLAIN, 15);
		try {
			bg = new Background("/Backgrounds/menuBg.png");
			bg.setVector(-5, 0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		bg.update();
	}
	
	public void draw(Graphics2D g) {
		
		// desenha o background
		bg.draw(g);
		// desenha o titulo
		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("TITULO", xPosText -44, yPosText-60);
		// desenha as opcoes
		g.setFont(altFont);
		if(curChoice == 0) {
			g.setColor(Color.YELLOW);
			g.drawString(options[0], xPosText-35, yPosText-20);
			g.setColor(Color.WHITE);
			g.drawString(options[1], xPosText -30, yPosText);
		}
		else if(curChoice == 1) {
			g.setColor(Color.WHITE);
			g.drawString(options[0], xPosText-35, yPosText-20);
			g.setColor(Color.YELLOW);
			g.drawString(options[1], xPosText-30, yPosText);
		}
	}
	
	private void select() {
		switch(curChoice) {
			case 0:
				stateManager.push(new CharacterSelectionState(stateManager));
				break;
			
			case 1:
				System.exit(0);
			
			default: break;
		}
	}
	
	public void keyPressed(int key) {
		switch(key) {
			case KeyEvent.VK_ENTER:
				select();
				break;
			
			case KeyEvent.VK_UP:
				curChoice--;
				
				if(curChoice < 0) {
					curChoice = options.length - 1;
				}
				break;
			
			case KeyEvent.VK_DOWN: 
				curChoice++;
				
				if(curChoice >= options.length) {
					curChoice = 0;
				}
				break;
		}
		
	}
	

	
}
