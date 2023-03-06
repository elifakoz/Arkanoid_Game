import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;


public class Ball extends JFrame{
	
	public int x,y,width,height,tempx,tempy,tempw,temph;
	public Color color;
	public int xSpeed,ySpeed,speed;
	public boolean destroyed= false;
	public Dir direction;
	public Ball(int x,int y,int width,int height,int speed) {
		super();
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		tempx = x;
		tempy = y;
		tempw = width;
		temph = height;
		this.speed=speed;
		this.direction = Dir.DOWNLEFT;
		setBounds(x,y,width,height);
	}
	
	void Move(int xBorder,int yBorder) {
		if(x<0 && (direction == Dir.DOWNLEFT  || direction == Dir.UPLEFT)) {
			if(direction == Dir.DOWNLEFT) {
				direction = Dir.DOWNRIGHT;
			}
			else if(direction == Dir.UPLEFT) {
				direction = Dir.UPRIGHT;
			}
		}
		if(x+width>xBorder && (direction == Dir.DOWNRIGHT||direction == Dir.UPRIGHT)) {
			if(direction == Dir.DOWNRIGHT)
				direction = Dir.DOWNLEFT;
			if(direction == Dir.UPRIGHT)
				direction = Dir.UPLEFT;
		}
		if(y<0 && (direction == Dir.UPLEFT||direction == Dir.UPRIGHT)) {
			if(direction == Dir.UPLEFT) {
				direction = Dir.DOWNLEFT;
			}
			else if(direction == Dir.UPRIGHT) {
				direction = Dir.DOWNRIGHT;
			}
		}
		if(y>yBorder) {
			GameWindow.lives--;
			x= tempx;
			y=tempy;
			width=tempw;
			height = temph;
		}
		SetSpeed();
		if(!destroyed) {
			x+=xSpeed;
			y+=ySpeed;
		}
	}
	void SetSpeed() {
		if(direction == Dir.DOWNLEFT) {
			xSpeed = -speed;
			ySpeed = speed;
		}
		if(direction == Dir.UPLEFT) {
			xSpeed = -speed;
			ySpeed = -speed;
		}
		if(direction == Dir.UPRIGHT) {
			xSpeed = speed;
			ySpeed = -speed;
		}
		if(direction == Dir.DOWNRIGHT) {
			xSpeed = speed;
			ySpeed = speed;
		}
	}
	void Draw(Graphics g) {
		setBounds(x,y,width,height);
		g.setColor(color);
		g.fillOval(x, y, width, height);
		g.setColor(Color.black);
		g.drawOval(x, y, width, height);
	}
}
