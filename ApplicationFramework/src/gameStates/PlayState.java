package gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import character.*;

import graphics.Background;
import graphics.CharacterAnimator;
import graphics.Effect;
import main.Panel;
import java.util.ArrayList;

public class PlayState extends GameState{
	
	private Wizard playerOne;
	private Wizard playerTwo;
	private Effect effect;
	private BufferedImage scenary;
	private static int tileSize = 64;
	private BufferedImage[] playerOneIcons;
	private CharacterAnimator playerOneSprite;
	private CharacterAnimator playerTwoSprite;
	private String[] spritesNames = {"redWizard.png","blueWizard.png","greenWizard.png"};
	private String[] flippedSpritesNames = {"redWizardFlipped.png","blueWizardFlipped.png","greenWizardFlipped.png"};
	private String[] iconNames = {"fireQ","fireE","fireR","iceQ","iceE","iceR","earthQ","earthE","earthR","wisdomW"};
	private static String[] playerFields = {"HP","MP"};
	private static String[] playerNames = {"Player One","Player Two"};
	private static String[] playerTurn = {"Player One Turn!!","Player Two Turn!!"};
	private ArrayList<Wizard> playersArray;
	private int currentTurn = 0;

	public PlayState(StateManager sm, Wizard p1) {
		super(sm);
		
		// Jogadores de teste
		playerOne = p1;
		playerTwo = new EarthWizard();
		
		playersArray = new ArrayList<Wizard>();
		playersArray.add(playerOne);
		playersArray.add(playerTwo);
		
		playerOneIcons = new BufferedImage[4];
		
		init();
	}
	
	public void init() {
		int aux = 0; // Os icones s�o definidos como os do fireWizard por default
		
		if(playerOne instanceof IceWizard) {
			aux = 3;
		}
		else if(playerOne instanceof EarthWizard) {
			aux = 6;
		}
			
		playerOneSprite = new CharacterAnimator(spritesNames[aux/3]);
		playerOneSprite.setPosition(200,Panel.height-145);
		playerTwoSprite = new CharacterAnimator(flippedSpritesNames[2]);
		playerTwoSprite.setPosition(400,Panel.height-145);
		for(int i=0;i<playerOneIcons.length;i++) {
			try {
				playerOneIcons[i] = ImageIO.read(getClass().getResourceAsStream("/Icons/"+iconNames[i+aux]+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			playerOneIcons[3] = ImageIO.read(getClass().getResourceAsStream("/Icons/"+iconNames[9]+".png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
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
		g.fillRect(0, Panel.height-tileSize-12, Panel.width, tileSize+12);
	
		g.fillRect(0, 0, Panel.width, tileSize);
		
		g.drawImage(playerOneIcons[0],10,Panel.height-64,48,48,null );
		for(int i=1;i<3;i++) {
			g.drawImage(playerOneIcons[i], (i+1)*48+10, Panel.height-64,48,48,null );
		}
		g.drawImage(playerOneIcons[3], 48+10, Panel.height-64,48,48,null);
		
		for(int i=0;i<4;i++) {
			g.setColor(Color.gray);
			g.drawRect(i*64,0, 64, 64);
			g.setColor(Color.gray.darker());
			g.drawRect(i*64+1,1, 62, 62);
		}
		
		g.setColor(Color.RED);
		if(currentTurn == 0)
		{
			g.drawString(playerTurn[0],350,Panel.height-300);
		}
		else
		{
			g.drawString(playerTurn[1],350,Panel.height-300);
		}
		
		
		g.setColor(Color.white);
		g.drawString(playerNames[0],0,Panel.height-65);
		g.drawString(playerFields[0],350,Panel.height-50);
		g.drawString(String.valueOf(this.playersArray.get(0).getHP()),410,Panel.height-50);
		g.drawString(playerFields[1],470,Panel.height-50);
		g.drawString(String.valueOf(this.playersArray.get(0).getMP()),530,Panel.height-50);
		g.fillRect(350,Panel.height-50,(int) this.playersArray.get(0).getMaxHP(), 10);
		g.fillRect(470,Panel.height-50,(int) this.playersArray.get(0).getMaxMP(), 10);
		g.setColor(Color.red);
		g.fillRect(350,Panel.height-50,(int) this.playersArray.get(0).getHP(), 10);
		g.setColor(Color.blue);
		g.fillRect(470,Panel.height-50,(int) this.playersArray.get(0).getMP(), 10);
		
		
		g.setColor(Color.white);
		g.drawString(playerNames[1],0,Panel.height-325);
		g.drawString(playerFields[0],350,Panel.height-380);
		g.drawString(String.valueOf(this.playersArray.get(1).getHP()),400,Panel.height-380);
		g.drawString(playerFields[1],350,Panel.height-350);
		g.drawString(String.valueOf(this.playersArray.get(1).getMP()),400,Panel.height-350);
		g.fillRect(350,Panel.height-380,(int) this.playersArray.get(1).getMaxHP(), 10);
		g.fillRect(350,Panel.height-350,(int) this.playersArray.get(1).getMaxMP(), 10);
		g.setColor(Color.red);
		g.fillRect(350,Panel.height-380,(int) this.playersArray.get(1).getHP(), 10);
		g.setColor(Color.blue);
		g.fillRect(350,Panel.height-350,(int) this.playersArray.get(1).getMP(), 10);
		
		playerOneSprite.draw(g);
		playerTwoSprite.draw(g);
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
	
	// Verifica o tipo de mago do oponente
	
	public int otherPlayerType()
	{
		int lastTurn;
		if(currentTurn == 1)
		{
			lastTurn = 0;
		}
		else
			lastTurn = 1;
		
		int wizType = validateType(this.playersArray.get(lastTurn));
		return wizType;
	}
	
	
	public Wizard getCurrentPlayer() {
		return playersArray.get(currentTurn);
	}
	
	public Wizard getOtherPlayer()
	{
		int lastTurn;
		if(currentTurn == 1)
		{
			lastTurn = 0;
		}
		else
			lastTurn = 1;
		
		return playersArray.get(lastTurn);
	}
	
	public void updateGame()
	{
		
		Wizard upWizOne = this.playersArray.get(0);
		Wizard upWizTwo = this.playersArray.get(1);
		
		if(upWizOne.getHP()<= 0)System.exit(0);
		if(upWizTwo.getHP()<= 0)System.exit(0);
		
		// Ativa regeneração passiva de MP, caso o valor passivo exceda o máximo de MP ele não extrapola
		
		// *Mudei pra simplificar o codigo. Agora o metodo passive regeneration testa se ultrapassou ou n�o o maxMP.
		
		upWizOne.passiveRegeneration();
		
		upWizTwo.passiveRegeneration();
		
		// Atualiza o turno e verifica se o oponente está atordoado
		
		if(this.currentTurn == 0)
		{
			if(this.playersArray.get(1).getTurnsStunned() > 0)
			{
				this.playersArray.get(1).updateStun();
			}
			else
				this.currentTurn = 1;
		}
		else if(this.currentTurn == 1)
		{
			if(this.playersArray.get(0).getTurnsStunned() > 0)
			{
				this.playersArray.get(0).updateStun();
			}
			else
				this.currentTurn = 0;
		}
	}
	// polimorfismo
	public void takeDamage(Wizard wiz, double DMG) {
		wiz.takeDamage(DMG, getCurrentPlayer().getType());
	}
	
	public void stun(Wizard wiz, int turnsStunned) {
		wiz.stun(turnsStunned);
	}

	@Override
	public void keyPressed(int key) {
		double DMG;
		
		switch(key) {
			case KeyEvent.VK_Q:
				DMG = this.playersArray.get(currentTurn).elementalStrike();
				if(DMG != -1)
				{
					takeDamage(getOtherPlayer(),DMG);
					updateGame();
			
					break;
				}
				else
					break;
			case KeyEvent.VK_W:
				this.playersArray.get(currentTurn).elementalWisdom();
				updateGame();
				break;
				
			case KeyEvent.VK_E:
				DMG = this.playersArray.get(currentTurn).ultimateStrike();
				if(DMG != -1)
				{
					takeDamage(getOtherPlayer(),DMG);
					updateGame();
					
					break;
				}
				else
					break;
			case KeyEvent.VK_T:
				
				// Pula turno
				
				updateGame();
				break;
			case KeyEvent.VK_L:
				this.playersArray.get(currentTurn).loseMP(this.playersArray.get(currentTurn).getMaxMP());
				break;
				
		// Habilidades únicas
				
			case KeyEvent.VK_R:
				int playerType = validateType(this.playersArray.get(currentTurn));
				if(playerType == 1)
				{
					DMG = ((FireWizard) this.playersArray.get(currentTurn)).trueFlames();
					if(DMG != -1) {
						takeDamage(getOtherPlayer(),DMG);
						updateGame();
					}
				}
				else if(playerType == 2)
				{
					int turnsStunned = ((IceWizard) this.playersArray.get(currentTurn)).stunningBlow();
					stun(getOtherPlayer(),turnsStunned);
					if(turnsStunned != -1) {
						updateGame();
					}
				}
				else if(playerType == 3)
				{
					double heal = ((EarthWizard) this.playersArray.get(currentTurn)).intenseHealing();
					if(heal != -1) {
						updateGame();
					}
				}
				break;
		}
	}

	public Wizard getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(Wizard playerOne) {
		this.playerOne = playerOne;
	}
	
}
