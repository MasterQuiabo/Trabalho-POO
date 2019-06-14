package gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;

import character.*;
import graphics.Background;
import graphics.CharacterAnimator;
import main.Panel;

public class CharacterSelectionState extends GameState{
	
	private CharacterAnimator[] chars;
	private String[] spritesNames = {"redWizard.png","blueWizard.png","greenWizard.png"};
	private int curChoice;
	private String[] options = {"FIRE", "ICE", "EARTH"};
	private Font font;
	
	public CharacterSelectionState(StateManager sm) {
		super(sm);
		curChoice = 0;
		bg = new Background("/Backgrounds/menuBg.png");
		bg.setVector(-4, 0);
		font = new Font("Times New Roman",Font.CENTER_BASELINE, 35);
		chars = new CharacterAnimator[3];
		
		for(int i=0;i<3;i++) {
			chars[i] = new CharacterAnimator(spritesNames[i]);
			chars[i].setPosition(130+150*i,Panel.height-200);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		//desenha o background
		bg.draw(g);
		//desenha os frames
		g.setColor(Color.white);
		for(int i=0;i<options.length;i++) {
			g.drawRect(95+150*i, 130, 100, 130);
		}
		switch(curChoice) {
			case 0:
				g.setColor(Color.yellow);
				g.drawRect(95, 130, 100, 130);
				break;
			case 1:
				g.setColor(Color.yellow);
				g.drawRect(245, 130, 100, 130);
				break;
			case 2:
				g.setColor(Color.yellow);
				g.drawRect(395, 130, 100, 130);
				break;
		}
		g.setColor(Color.red);
		g.drawString(options[0], 125, 150);
		g.setColor(Color.blue);
		g.drawString(options[1], 280, 150);
		g.setColor(Color.green);
		g.drawString(options[2], 120+300, 150);
		//desenha os personagens
		for(int i=0;i<3;i++) {
			chars[i].draw(g);
		}
	}

	@Override
	public void update() {
		bg.update();
	}
	

	public void select(){
		Wizard playerOne;
		switch(curChoice) {
		
			case 0:
				playerOne = new FireWizard();
				break;
				
			case 1:
				playerOne = new IceWizard();
				break;
				
			case 2:
				playerOne = new EarthWizard();
				break;
				
			default:
				playerOne = new FireWizard();
				break;
				
		}
		stateManager.push(new PlayState(stateManager, playerOne));
	}

	@Override
	public void keyPressed(int key) {
		switch(key) {
			case KeyEvent.VK_RIGHT:
				curChoice++;
				if(curChoice>=options.length)
					curChoice = 0;
				break;
			case KeyEvent.VK_LEFT:
				curChoice--;
				if(curChoice<0)
					curChoice = options.length-1;
				break;
			case KeyEvent.VK_ENTER:
				select();
				break;
		}
	}

}
