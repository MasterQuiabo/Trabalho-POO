package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Panel;

public class Background {
	private BufferedImage bg;
	private double x;
	private double y;
	private double vx;
	private double vy;
	
	public Background(String end) {
		x=0;
		y=0;
		try {
			bg = ImageIO.read(getClass().getResourceAsStream(end));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setVector(double x,double y) {
		vx = x;
		vy = y;
	}
	
	public void update() {
		x += vx;
		y += vy;
		if(x<-Panel.width || x>Panel.width)x=0;
	}
	
	public void draw(Graphics g) {
		g.drawImage(bg, (int)x, (int)y, null);
		if(x<0) {
			g.drawImage(bg,(int)x+Panel.width,(int)y,null); 
		}
		else{
			g.drawImage(bg,(int)x-Panel.width,(int)y,null);
		}
		
	}
}
