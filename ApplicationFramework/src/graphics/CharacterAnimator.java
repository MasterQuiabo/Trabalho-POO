package graphics;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import main.Panel;

public class CharacterAnimator {
	private BufferedImage[] frames;
	private BufferedImage sprite;
	private double posX;
	private double posY;
	private int curFrame;
	private int timing;

	public CharacterAnimator(String end,double x) {
		curFrame = 0;
		timing = 0;
		frames = new BufferedImage[2];
		posX = x;
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/"+end));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		for(int i=0;i<2;i++) {
			frames[i] = sprite.getSubimage(i*16, 0, 16, 21);
		}
		
	}
	
	public void draw(Graphics2D g) {
		timing++;
		g.drawImage(frames[curFrame], (int) posX, Panel.height-140,16*Panel.scale,21*Panel.scale, null);
		if(timing==8) {
			g.drawImage(frames[curFrame], (int) posX, Panel.height-140,16*Panel.scale,21*Panel.scale, null);
			timing = 0;
			curFrame++;
		}
		
		if(curFrame>=frames.length)
			curFrame=0;
	}

	
	
}
