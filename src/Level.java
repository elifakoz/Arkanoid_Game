import java.util.ArrayList;
import java.util.List;

public class Level {
	
	
	public ArrayList<Block> blocks;
	public int ballSpeed;
	public int padSpeed;
	public Level(int ballSpeed,int padSpeed) {
		this.ballSpeed=ballSpeed;
		this.padSpeed = padSpeed;
		blocks = new ArrayList<Block>();
	}
}
