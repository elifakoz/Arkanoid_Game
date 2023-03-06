import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class Block extends JLabel{
	public int x,y,width,height,health;
	public Color color;
	public boolean destroyed = false;

	public Block(int x,int y,int width,int height,int health) {
		super();
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.health=health;
		setColor();
		setBounds(x,y,width,height);
	}
	void GetHit() {
		health--;
		setColor();
	}
	
	void setColor() {
		if(health==3) {
			color = Color.black;
		}
		else if(health==2) {
			color = Color.orange;
		}
		else if(health==1) {
			color = Color.red;
		}
		else {
			color = Color.black;
		}
	}
	
	void Draw(Graphics g) {
		setBounds(x,y,width,height);
		g.setColor(color);
		g.fillRect(x, y, width, height);
		if(color == Color.black) {
			g.setColor(Color.white);
			g.drawRect(x, y, width, height);
		}
	}
	public boolean isDestroyed() {
		destroyed = health<=0;
		return destroyed;
	}
}
