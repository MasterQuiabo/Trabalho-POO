package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


import main.Panel;

public class CharacterAnimator {
	private int numFrames;
	private BufferedImage[] idleFrames;
	private BufferedImage sprite;
	private double posX;
	private double posY;
	private int curFrame;
	private int timing;
	private boolean casting;

	public CharacterAnimator(String end) {
		curFrame = 0;
		timing = 0;
		
		numFrames = 2;
		
		idleFrames = new BufferedImage[numFrames];
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/"+end));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		for(int i=0;i<numFrames;i++) {
			idleFrames[i] = sprite.getSubimage(i*16, 0, 16, 21);
		}
		
	}
	
	public void setPosition(double x, double y) {
		posX = x;
		posY = y;
	}
	
	public void draw(Graphics2D g) {
		timing++;
		
		if(timing==9) {
			g.drawImage(idleFrames[curFrame], (int) posX, (int) posY,16*Panel.scale,21*Panel.scale, null);
			timing = 0;
			curFrame++;
			if(curFrame>=idleFrames.length)
				curFrame=0;
		}
		else 
			g.drawImage(idleFrames[curFrame], (int) posX, (int) posY,16*Panel.scale,21*Panel.scale, null);
	}

	
	
}
