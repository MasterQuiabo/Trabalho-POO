package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Panel;

public class Background {
	private BufferedImage bg;
	private double posX;
	private double posY;
	private double vectorX;
	private double vectorY;
	
	public Background(String end) {
		posX=0;
		posY=0;
		try {
			bg = ImageIO.read(getClass().getResourceAsStream(end));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setVector(double x,double y) {
		vectorX = x;
		vectorY = y;
	}
	
	public void update() {
		posX += vectorX;
		posY += vectorY;
		if(posX<-Panel.width || posX>Panel.width)posX=0;
	}
	
	public void draw(Graphics g) {
		g.drawImage(bg, (int)posX, (int)posY, null);
		if(posX<0) {
			g.drawImage(bg,(int)posX+Panel.width,(int)posY,null);
		}
		else{
			g.drawImage(bg,(int)posX-Panel.width,(int)posY,null);
		}
		
	}
}
