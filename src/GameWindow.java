import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;




public class GameWindow extends JFrame{
	
	int destroyCount=0;
	static final int BWIDTH = 30,BHEIGHT=20;
	static final String background = "images/bg.png";
	JLabel lblScore,lblLevel,lblLives; 
	DrawPane panel;
	Player player;
	Level level;
	Ball ball;
	int lvl,score=0;
	Timer timer;
	public static int lives=3;
	boolean gameRuns=false;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow(1);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public class DrawPane extends JPanel {
		BufferedImage image;
		public DrawPane() {
			super();
			try {
				image = ImageIO.read(new File(background));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g) {
	        g.drawImage(image, 0,0, null);
        	for(Block b:level.blocks) {
    			if(!b.isDestroyed()) {
    				b.Draw(g);
    			}
    		}
        	player.Draw(g);
    		ball.Draw(g);
        }
   }
	class GameTask extends TimerTask{
    	public GameTask() {
    		super();
    	}
    	public void run() {
    		boolean hit = false;
    		if(gameRuns) {
    		ball.Move(panel.getWidth(), panel.getHeight());
    		if(ball.getBounds().intersects(player.getBounds())) {
    			if(ball.direction==Dir.DOWNLEFT) {
    				ball.direction = Dir.UPLEFT;
    			}
    			else if(ball.direction==Dir.DOWNRIGHT) {
    				ball.direction = Dir.UPRIGHT;
    			}
    		}
    		for(Block block:level.blocks) {
    			if(block.destroyed) {
    				panel.remove(block);
    			}
    			if(!hit&&block.destroyed==false&&ball.getBounds().intersects(block.getBounds())) {
    				hit = true;
    				block.GetHit();
    				if(block.isDestroyed()) {
    					destroyCount++;
    				}
    				break;
    			}
    		}
			if(hit) {
				score+=10;
				if(score%100==0 && score > 0) {
					ball.speed++;
				}
				if(ball.direction==Dir.UPLEFT) {
    				ball.direction = Dir.DOWNLEFT;
    			}
    			else if(ball.direction==Dir.UPRIGHT) {
    				ball.direction = Dir.DOWNRIGHT;
    			}
			}
    		if(lives==0) {
    			gameRuns=false;
    			String name = JOptionPane.showInputDialog(null, "Game Over. Enter your name:");
    			File f1;
    			try {
    				 f1 = new File("scores.txt");
           	         if(!f1.exists()) {
        	            f1.createNewFile();
            	     }
        	         FileWriter fileWritter = new FileWriter(f1.getName(),true);
        	         BufferedWriter bw = new BufferedWriter(fileWritter);
        	         bw.write(name+":"+String.valueOf(score)+"\n");
        	         bw.close();
    			}
    			catch(Exception ex) {
    			}
    			dispose();
    		}
    		else if(destroyCount >= level.blocks.size()) {
    			gameRuns = false;
    			JOptionPane.showMessageDialog(null, "Great! Prepare for next level!");
    			lvl++;
    			destroyCount = 0;
    			SetLevel();
    			gameRuns=true;
    		}
    		lblLives.setText("Lives: "+String.valueOf(lives));
    		lblLevel.setText("Level: "+String.valueOf(lvl));
    		lblScore.setText("Score: "+String.valueOf(score));
    		panel.repaint();
    		}
    		
    	}
    }
	void SetLevel() {
		int rows = 3;
		int cols = 11;
		int margin = 5;
		int x = (margin+BWIDTH);
		int y = margin+BHEIGHT;
		if(lvl==1) {
			level = new Level(10,10);
		}
		else if(lvl==2) {
			level = new Level(11,10);
		}
		else if(lvl==3) {
			level = new Level(12,10);
		}

		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				int blocklvl = 1;
				if(lvl>=2 && i == 1)blocklvl = 2;
				if(lvl==3 && i == 0) blocklvl = 3;
				Block block = new Block(x,y,BWIDTH,BHEIGHT,blocklvl);
				level.blocks.add(block);
    			panel.add(block);
				x+=margin+BWIDTH;
			}
			x= margin+BWIDTH;
			y+=margin+BHEIGHT;
		}
		player = new Player((int)(panel.getWidth()/2),panel.getHeight()-(BHEIGHT*(3)),(int)(BWIDTH*(5-lvl)),BHEIGHT,-1,level.padSpeed);
		ball = new Ball((int)(panel.getWidth()/2),(int)(panel.getHeight()/2),30,30,level.ballSpeed);
		panel.add(player);
	}
	public GameWindow(int level) {
		this.lvl = level;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 450, 568);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new DrawPane();
		panel.setBounds(0, 25, 434, 504);
		getContentPane().add(panel);
		
		lblScore = new JLabel("New label");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setBounds(10, 0, 120, 14);
		getContentPane().add(lblScore);
		
		lblLevel = new JLabel("New label");
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setBounds(140, -1, 139, 14);
		getContentPane().add(lblLevel);
		
		lblLives = new JLabel("New label");
		lblLives.setHorizontalAlignment(SwingConstants.CENTER);
		lblLives.setBounds(304, -1, 120, 14);
		getContentPane().add(lblLives);
		timer = new Timer();
		Background bg = new Background(panel.getWidth(),panel.getHeight());
		panel.add(bg);
		SetLevel();
		panel.repaint();
    	repaint();

		if(!gameRuns) {
			gameRuns=true;
			timer.schedule(new GameTask(), 0, 100);
		}
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
		    		int key = e.getKeyCode();
				    if(key==KeyEvent.VK_LEFT) {
				    	player.direction=0;
				    }
				    else if(key==KeyEvent.VK_RIGHT) {
				    	player.direction=1;
				    }
			    	player.Move(panel.getWidth());
			    	panel.repaint();
			    	repaint();
			}
		});
	}
}
