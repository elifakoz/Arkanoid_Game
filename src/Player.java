import java.awt.Color;
import java.awt.Panel;

public class Player extends Block{
	public int direction,speed;
	public Player(int x, int y, int width, int height, int health,int speed) {
		super(x, y, width, height,  health);
		this.direction=0;
		this.speed=speed;
	}
	public void Move(int border) {
		if(direction==0) {
			if(x-speed>0)
				x-=speed;
		}
		else if(direction==1){
			if(x+speed+width<border)
				x+=speed;
		}
		this.setBounds(x,y,width,height);
	}
	
}
