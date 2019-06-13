package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import gameStates.StateManager;

public class Panel extends JPanel implements ActionListener{
	//dimensoes
	public static final int width = 600;
	public static final int height = 400;
	//escala
	public static final int scale = 2;
	//graficos
	private Graphics2D g;
	private BufferedImage img;
	//gerenciador de estados
	private StateManager stateManager;
	
	private Timer timer;
	
	
	public Panel() {
		super();
		setPreferredSize(new Dimension(width*scale,height*scale));
		setFocusable(true);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		g = (Graphics2D) img.getGraphics();
		stateManager = new StateManager();
		addKeyListener(new Keyboard(stateManager));
		timer = new Timer(50,this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		draw();
		drawToScreen();
		update();
	}
	
	public void update() {
		stateManager.update();
	}
	public void draw() {
		stateManager.draw(g);;
	}
	//passamos o desenho off-screen(buffer) para a tela
	public void drawToScreen() {
		Graphics2D g2 = (Graphics2D)getGraphics();
		g2.drawImage(img, 0, 0, width*scale, height*scale,null);
	}
	
}
