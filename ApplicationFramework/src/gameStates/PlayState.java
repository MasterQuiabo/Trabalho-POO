package gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import wizard.*;

import graphics.Background;
import main.Panel;

public class PlayState extends GameState{
	private BufferedImage scenary;
	private static int tileSize = 64;
	private static String[] playerFields = {"HP","MP"};
	private Wizard player;

	public PlayState(StateManager sm) {
		super(sm);
		player = new FireWizard();
		try {
			scenary = ImageIO.read(getClass().getResourceAsStream("/Tiles/grasstile.png"));
			bg = new Background("/Backgrounds/menuBg.png");
			bg.setVector(-4, 0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		//desenha background
		bg.draw(g);
		//desenha cenario
		for(int i=0;i<Panel.width;i++) {
			g.drawImage(scenary, Panel.width-i*tileSize, Panel.height-128,null);
		}
		//desenha o layout
		g.setColor(Color.white.darker());
		g.fillRect(0, Panel.height-tileSize, Panel.width, tileSize);
		
		
		for(int i=0;i<5;i++) {
			g.setColor(Color.gray);
			g.drawRect(i*64,Panel.height-65, 64, 64);
			g.setColor(Color.gray.darker());
			g.drawRect(i*64+1,Panel.height-64, 62, 62);
		}
		
		g.setColor(Color.white);
		g.drawString(playerFields[0],350,Panel.height-50);
		g.drawString(playerFields[1],350,Panel.height-20);
		g.fillRect(350,Panel.height-50,100, 10);
		g.fillRect(350,Panel.height-20,100, 10);
		g.setColor(Color.red);
		g.fillRect(350,Panel.height-50,(int) player.getHP(), 10);
		g.setColor(Color.blue);
		g.fillRect(350,Panel.height-20,(int) player.getMP(), 10);
		
		
	}

	@Override
	public void update() {
		bg.update();
		if(player.getMP()<100)player.passiveRegeneration();
		if(player.getHP()<= 0)System.exit(0);
	}
	
	public Wizard getPlayer() {
		return player;
	}

	@Override
	public void keyPressed(int key) {
		switch(key) {
			case KeyEvent.VK_SPACE:
				player.loseHP(10);
				break;
				
			case KeyEvent.VK_M:
				player.loseMP(10);
				break;
		}
	}
	
}
