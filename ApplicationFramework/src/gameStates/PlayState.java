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
import main.Panel;
import java.util.ArrayList;

public class PlayState extends GameState{
	
	private BufferedImage scenary;
	private static int tileSize = 64;
	private BufferedImage[] fireIcons;
	private BufferedImage[] iceIcons;
	private BufferedImage[] earthIcons;
	private BufferedImage wIcon;
	private String[] iconNames = {"fireQ","fireE","fireR","iceQ","iceE","iceR","earthQ","earthE","earthR","wisdomW"};
	private static String[] playerFields = {"HP","MP"};
	private static String[] playerNames = {"Player One","Player Two"};
	private static String[] playerTurn = {"Player One Turn!!","Player Two Turn!!"};
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
		
		fireIcons = new BufferedImage[3];
		iceIcons = new BufferedImage[3];
		earthIcons = new BufferedImage[3];
		
		for(int i=0;i<fireIcons.length;i++) {
			try {
				fireIcons[i] = ImageIO.read(getClass().getResourceAsStream("/Icons/"+iconNames[i]+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=0;i<iceIcons.length;i++) {
			try {
				iceIcons[i] = ImageIO.read(getClass().getResourceAsStream("/Icons/"+iconNames[i+fireIcons.length]+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(int i=0;i<earthIcons.length;i++) {
			try {
				earthIcons[i] = ImageIO.read(getClass().getResourceAsStream("/Icons/"+iconNames[i+iceIcons.length]+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			wIcon = ImageIO.read(getClass().getResourceAsStream("/Icons/"+iconNames[9]+".png"));
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
		
		g.drawImage(fireIcons[0],10,Panel.height-64,48,48,null );
		for(int i=1;i<3;i++) {
			g.drawImage(fireIcons[i], (i+1)*48+10, Panel.height-64,48,48,null );
		}
		g.drawImage(wIcon, 48+10, Panel.height-64,48,48,null);
		
		for(int i=0;i<4;i++) {
			g.setColor(Color.gray);
			g.drawRect(i*64,0, 64, 64);
			g.setColor(Color.gray.darker());
			g.drawRect(i*64+1,1, 62, 62);
		}
		
		g.setColor(Color.RED);
		if(actualTurn == 0)
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
	
	public int getOtherPlayer()
	{
		int lastTurn;
		if(actualTurn == 1)
		{
			lastTurn = 0;
		}
		else
			lastTurn = 1;
		
		return lastTurn;
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
				if(DMG != -1)
				{
					DMG = mitigateDMG(DMG);
					this.playersArray.get(getOtherPlayer()).loseHP(DMG);
					updateGame();
			
					break;
				}
				else
					break;
			case KeyEvent.VK_W:
				this.playersArray.get(actualTurn).elementalWisdom();
				updateGame();
				break;
				
			case KeyEvent.VK_E:
				DMG = this.playersArray.get(actualTurn).ultimateStrike();
				if(DMG != -1)
				{
					DMG = mitigateDMG(DMG);
					this.playersArray.get(getOtherPlayer()).loseHP(DMG);
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
				this.playersArray.get(actualTurn).loseMP(this.playersArray.get(actualTurn).getMaxMP());
				break;
				
		// Habilidades únicas
				
			case KeyEvent.VK_R:
				int playerType = validateType(this.playersArray.get(actualTurn));
				if(playerType == 1)
				{
					DMG = ((FireWizard) this.playersArray.get(actualTurn)).trueFlames();
					this.playersArray.get(getOtherPlayer()).loseHP(DMG);
					updateGame();
				}
				else if(playerType == 2)
				{
					int turnsStunned = ((IceWizard) this.playersArray.get(actualTurn)).stunningBlow();
					this.playersArray.get(getOtherPlayer()).stun(turnsStunned);
					
					updateGame();
				}
				else if(playerType == 3)
				{
					((EarthWizard) this.playersArray.get(actualTurn)).intenseHealing();
					updateGame();
				}
				break;
		}
	}
	
}
