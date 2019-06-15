package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Effect {
	
	private BufferedImage[] effects;
	private BufferedImage img;
	private double timing;
	private int curFrame;
	private double posX;
	private double posY;
	private double vectorX;
	private double vectorY;
	
	public Effect(String end) {
		curFrame = 0;
		timing = 0;
		effects = new BufferedImage[49];
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream(end));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		for(int i=0,k=0;i<7;i++) {
			for(int j=0;j<7;j++,k++) {
				effects[k] = img.getSubimage(i*100, j*100, 100, 100);
			}
		}
	}
	
	public void setPosition(double x, double y) {
		posX = x;
		posY = y;
	}
	
	public void setVector(double x, double y) {
		vectorX = x;
		vectorY = y;
	}
	
	private void update() {
		posX += vectorX;
		posY += vectorY;
	}
	
	public void draw(java.awt.Graphics2D g) {
		timing++;
		if(timing == 2) {
			g.drawImage(effects[curFrame], (int)posX, (int)posY, null);
			timing = 0;
			curFrame++;
			if(curFrame>=effects.length)
				curFrame=0;
			update();
		}
		else 
			g.drawImage(effects[curFrame], (int)posX, (int)posY, null);
	}
	
}
