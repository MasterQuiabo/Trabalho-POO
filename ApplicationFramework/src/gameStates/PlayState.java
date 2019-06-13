package gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import character.*;

import graphics.Background;
import main.Panel;
import java.util.ArrayList;

public class PlayState extends GameState{
	
	private BufferedImage scenary;
	private static int tileSize = 64;
	private static String[] playerFields = {"HP","MP"};
	private static String[] playerNames = {"Player One","Player Two"};
	private ArrayList<Wizard> playersArray;
	private int actualTurn = 0;

	public PlayState(StateManager sm) {
		super(sm);
		
		// Jogadores de teste
		
		Wizard playerOne = new FireWizard();
		Wizard playerTwo = new EarthWizard();
		
		playersArray = new ArrayList<Wizard>();
		playersArray.add(playerOne);
		playersArray.add(playerTwo);
		
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
			g.drawImage(scenary, Panel.width-i*tileSize, Panel.height-140,null);
		}
		//desenha o layout
		g.setColor(Color.white.darker());
		g.fillRect(0, Panel.height-tileSize, Panel.width, tileSize);
	
		g.fillRect(0, Panel.height-tileSize-356, Panel.width, tileSize);
		
		
		for(int i=0;i<5;i++) {
			g.setColor(Color.gray);
			g.drawRect(i*64,Panel.height-65, 64, 64);
			g.setColor(Color.gray.darker());
			g.drawRect(i*64+1,Panel.height-64, 62, 62);
		}
		
		for(int i=0;i<5;i++) {
			g.setColor(Color.gray);
			g.drawRect(i*64,Panel.height-420, 64, 64);
			g.setColor(Color.gray.darker());
			g.drawRect(i*64+1,Panel.height-420, 62, 62);
		}
		
		g.setColor(Color.white);
		g.drawString(playerNames[0],0,Panel.height-65);
		g.drawString(playerFields[0],400,Panel.height-50);
		g.drawString(playerFields[1],520,Panel.height-50);
		g.fillRect(400,Panel.height-380,100, 10);
		g.fillRect(520,Panel.height-380,100, 10);
		g.setColor(Color.red);
		g.fillRect(400,Panel.height-380,(int) this.playersArray.get(1).getHP(), 10);
		g.setColor(Color.blue);
		g.fillRect(520,Panel.height-380,(int) this.playersArray.get(1).getMP(), 10);
		
		g.setColor(Color.white);
		g.drawString(playerNames[1],0,Panel.height-345);
		g.drawString(playerFields[0],400,Panel.height-380);
		g.drawString(playerFields[1],520,Panel.height-380);
		g.fillRect(400,Panel.height-50,100, 10);
		g.fillRect(520,Panel.height-50,100, 10);
		g.setColor(Color.red);
		g.fillRect(400,Panel.height-50,(int) this.playersArray.get(0).getHP(), 10);
		g.setColor(Color.blue);
		g.fillRect(520,Panel.height-50,(int) this.playersArray.get(0).getMP(), 10);
		
		
	}

	@Override
	public void update() {
		bg.update();
		
	}
	
	// Retorna dados sobre o tipo do mago
	
	public int validateType(Wizard wiz)
	{
		if(wiz instanceof FireWizard)
		{
			return 1;
		}
		else if(wiz instanceof IceWizard)
		{
			return 2;
		}
		else if(wiz instanceof EarthWizard)
		{
			return 3;
		}
		
		return 0;
	}
	
	// Verifica o MP máximo daquela instancia 
	
	public double maxMP(Wizard wiz)
	{
		if(wiz instanceof FireWizard)
		{
			return 100;
		}
		else if(wiz instanceof IceWizard)
		{
			return 80;
		}
		else if(wiz instanceof EarthWizard)
		{
			return 120;
		}
		
		return 0;
	}
	
	// Verifica o tipo de mago do oponente
	
	public int otherPlayerType()
	{
		int lastTurn;
		if(actualTurn == 1)
		{
			lastTurn = 0;
		}
		else
			lastTurn = 1;
		
		int wizType = validateType(this.playersArray.get(lastTurn));
		return wizType;
	}
	
	// Aplica resistências e fraquezas do oponente ao dano iminente
	
	public double mitigateDMG(double DMG)
	{
		int wizType = otherPlayerType();
		
		if(wizType == 1)
		{
			DMG = DMG*this.playersArray.get(actualTurn).getFireResistance();
			return DMG;
		}
		else if(wizType == 2)
		{
			DMG = DMG*this.playersArray.get(actualTurn).getIceResistance();
			return DMG;
		}
		else if(wizType == 3)
		{
			DMG = DMG*this.playersArray.get(actualTurn).getEarthResistance();
			return DMG;
		}
		return 0;
	}
	
	public void updateGame()
	{
		Wizard upWizOne = this.playersArray.get(0);
		Wizard upWizTwo = this.playersArray.get(1);
		
		if(upWizOne.getHP()<= 0)System.exit(0);
		if(upWizTwo.getHP()<= 0)System.exit(0);
		
		// Ativa regeneração passiva de MP, caso o valor passivo exceda o máximo de MP ele não extrapola
		
		if(upWizOne.getMP() < maxMP(upWizOne))
		{
			if((upWizOne.getMP() + 5.0) > maxMP(upWizOne))
			{
				upWizOne.passiveRegeneration(validateType(upWizOne));
			}
			else
				upWizOne.passiveRegeneration();
		}
		if(upWizTwo.getMP() < maxMP(upWizTwo))
		{
			if((upWizTwo.getMP() + 5.0) > maxMP(upWizTwo))
			{
				upWizTwo.passiveRegeneration(validateType(upWizTwo));
			}
			else
				upWizTwo.passiveRegeneration();
		}
		
		// Atualiza os dados no array
		
		this.playersArray.set(0, upWizOne);
		this.playersArray.set(1, upWizTwo);
		
		// Atualiza o turno e verifica se o oponente está atordoado
		
		if(this.actualTurn == 0)
		{
			if(this.playersArray.get(1).getTurnsStunned() > 0)
			{
				this.playersArray.get(1).updateStun();
			}
			else
				this.actualTurn = 1;
		}
		else if(this.actualTurn == 1)
		{
			if(this.playersArray.get(0).getTurnsStunned() > 0)
			{
				this.playersArray.get(0).updateStun();
			}
			else
				this.actualTurn = 0;
		}
	}

	@Override
	public void keyPressed(int key) {
		double DMG;	
		switch(key) {
			case KeyEvent.VK_Q:
				DMG = this.playersArray.get(actualTurn).elementalStrike();
				updateGame();
				mitigateDMG(DMG);
				this.playersArray.get(actualTurn).loseHP(DMG);
				System.out.println(actualTurn);
				break;
				
			case KeyEvent.VK_W:
				this.playersArray.get(actualTurn).elementalWisdom();
				updateGame();
				break;
				
			case KeyEvent.VK_E:
				DMG = this.playersArray.get(actualTurn).ultimateStrike();
				updateGame();
				mitigateDMG(DMG);
				this.playersArray.get(actualTurn).loseHP(DMG);
				break;
				
		// Habilidades únicas
				
			case KeyEvent.VK_R:
				int playerType = validateType(this.playersArray.get(actualTurn));
				if(playerType == 1)
				{
					DMG = ((FireWizard) this.playersArray.get(actualTurn)).trueFlames();
					updateGame();
					this.playersArray.get(actualTurn).loseHP(DMG);
				}
				else if(playerType == 2)
				{
					int turnsStunned = ((IceWizard) this.playersArray.get(actualTurn)).stunningBlow();
					updateGame();
					this.playersArray.get(actualTurn).stun(turnsStunned);
				}
				else if(playerType == 3)
				{
					((EarthWizard) this.playersArray.get(actualTurn)).intenseHealing();;
					updateGame();
				}
				break;
		}
	}
	
}
